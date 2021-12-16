package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpVersion;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpMethod;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequest;
import com.TheTallestOfMidgets.UTIL.FileTypes;

import java.io.*;
import java.util.ArrayList;

/*
TODO   GZIP compression
 */


public class ResponseGenerator {
    private final HttpRequest request;
    private HttpResponse response;
    private ArrayList<Byte> responseBody;
    private final String webRoot;
    private final OutputStream outputStream;

    private final byte[] CRLF;
    private final int packetSizeAllocation = 500000000;

    public ResponseGenerator(HttpRequest request, OutputStream outputStream) {
        CRLF = new byte[]{(byte) 13, (byte) 10};
        response = new HttpResponse();
        this.request = request;
        this.webRoot = "src\\main\\WebRoot\\";
        this.outputStream = outputStream;
    }

    public void generateResponse() throws IOException {
        System.out.println("GENERATE RESPONSE");

        response.setStatusLine(new HTTPStatusLine(new HttpVersion(1,1), HttpStatusCode.SUCCESS_200));
        if(HttpMethod.getMethod(request.getRequestLine().getMethod()) == null){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_501_METHOD_NOT_IMPLEMENTED);
            outputStream.write(sendStatusLineError());
            return;
        }
        if(!request.getRequestLine().getVersion().equals(new HttpVersion(1,1))){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_UNSUPPORTED);
            outputStream.write(sendStatusLineError());
            return;
        }


        response.addHeader(new HttpHeader("Accept-Ranges", "bytes"));
        addRequestURIHeaders();
        if(response.getStatusLine().getStatusCode() != HttpStatusCode.SUCCESS_200){
            outputStream.write(sendStatusLineError());
            return;
        }
        if(!new File(webRoot + request.getRequestLine().getRequestURI()).exists()){
            response.setStatusCode(HttpStatusCode.CLIENT_ERROR_404_RESOURCE_NOT_FOUND);
            outputStream.write(sendStatusLineError());
            return;
        }

        //status line
        outputStream.write("HTTP/1.1 200 OK".getBytes());
        outputStream.write(CRLF);


        //headers
        for(HttpHeader header : response.getHeaders()){
            outputStream.write((header.getField() + ": " + header.getValue()).getBytes());
            outputStream.write(CRLF);
        }

        //messageBody
        outputStream.write(CRLF);
        getAndSendResource();
    }

    private void addRequestURIHeaders() {
        if(!request.getRequestLine().getRequestURI().equalsIgnoreCase("/")) {
            String fileSuffix = (request.getRequestLine().getRequestURI().split("\\."))[1];
            response.addHeader(new HttpHeader("Content-Type", FileTypes.getContentType(fileSuffix)));
        }else {
            response.addHeader(new HttpHeader("Content-Type", FileTypes.htmlText.getType()));
        }
        try {
            if(request.getRequestLine().getRequestURI().equals("/")) {
                response.addHeader(new HttpHeader("Content-Length", String.valueOf(new File(webRoot + "index.html").length())));
            }else {
                response.addHeader(new HttpHeader("Content-Length", String.valueOf(new File(webRoot + request.getRequestLine().getRequestURI()).length())));
            }
        }catch (Exception e) {
            response.setStatusCode(HttpStatusCode.CLIENT_ERROR_404_RESOURCE_NOT_FOUND);
        }
    }

    private void getAndSendResource(){
        FileInputStream fileInputStream = null;
        try {
            if (request.getRequestLine().getRequestURI().equals("/")) {
                File requestedFile = new File(webRoot + "index.html");
                fileInputStream = new FileInputStream(requestedFile);
                byte[] packet;
                for(int i = 0; i < requestedFile.length(); i++){
                    packet = new byte[packetSizeAllocation / (Thread.activeCount() - 2 )];
                    fileInputStream.read(packet);
                    outputStream.write(packet);

                }
                fileInputStream.close();
            } else {
                File requestedFile = new File(webRoot + request.getRequestLine().getRequestURI());
                fileInputStream = new FileInputStream(requestedFile);

                byte[] packet;
                for(int i = 0; i < requestedFile.length(); i++){
                    packet = new byte[packetSizeAllocation / (Thread.activeCount() - 2 )];
                    fileInputStream.read(packet);
                    outputStream.write(packet);

                }

                fileInputStream.close();
            }


        } catch (FileNotFoundException e) {
            response.setStatusCode(HttpStatusCode.CLIENT_ERROR_404_RESOURCE_NOT_FOUND); //Should never be thrown, Redundancy
        }catch (Exception e){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (Exception ignored){}
            }
        }
    }

    private byte[] sendStatusLineError(){
        return ("HTTP/" +
                response.getStatusLine().getVersion().getMajor() + "." +
                response.getStatusLine().getVersion().getMinor() + " " +
                response.getStatusLine().getStatusCode().getCode() + " " +
                response.getStatusLine().getStatusCode().getMessage() +
                "\n\r").getBytes();
    }
}
