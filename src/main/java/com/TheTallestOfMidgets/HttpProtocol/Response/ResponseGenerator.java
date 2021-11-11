package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpVersion;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpMethod;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequest;
import com.TheTallestOfMidgets.UTIL.Array2ArrayList;
import com.TheTallestOfMidgets.UTIL.FileTypes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

public class ResponseGenerator {
    private final HttpRequest request;
    private HttpResponse response;
    private ArrayList<Byte> responseBody;
    private final String webRoot;

    private final ArrayList<Byte> CRLF;

    public ResponseGenerator(HttpRequest request) {
        CRLF = new ArrayList<>(); CRLF.add((byte) 13); CRLF.add((byte) 10);
        response = new HttpResponse();
        this.request = request;
        this.webRoot = "src\\main\\WebRoot\\";
    }

    public ArrayList<Byte> generateResponse() {

        response.setStatusLine(new HTTPStatusLine(new HttpVersion(1,1), HttpStatusCode.SUCCESS_200));
        if(HttpMethod.getMethod(request.getRequestLine().getMethod()) == null){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_501_METHOD_NOT_IMPLEMENTED);
            return sendStatusLineError();
        }
        if(!request.getRequestLine().getVersion().equals(new HttpVersion(1,1))){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_UNSUPPORTED);
            return sendStatusLineError();
        }


        response.addHeader(new HttpHeader("Accept-Ranges", "bytes"));

        response.setMessageBody(getResource());
        //do whatever needs to be done to the resource
        //generate response itself

        if(response.getStatusLine().getStatusCode() != HttpStatusCode.SUCCESS_200){
            return sendStatusLineError();
        }

        ArrayList<Byte> reply = new ArrayList<>();

        //status line
        reply.addAll(Array2ArrayList.byteArray("HTTP/1.1 200 OK".getBytes()));
        reply.addAll(CRLF);

        //headers
        for(HttpHeader header : response.getHeaders()){
            reply.addAll(Array2ArrayList.byteArray((header.getField() + ": " + header.getValue()).getBytes()));
            reply.addAll(CRLF);
        }

        //messageBody
        reply.addAll(CRLF);
        reply.addAll(response.getMessageBody());

        return reply;

    }

    private ArrayList<Byte> getResource(){

        ArrayList<Byte> data = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            if (request.getRequestLine().getRequestURI().equals("/")) {
                File requestedFile = new File(webRoot + "index.html");
                fileInputStream = new FileInputStream(requestedFile);
                byte[] fileOutPut = new byte[ (int) requestedFile.length()];
                fileInputStream.read(fileOutPut);
                data.addAll(Array2ArrayList.byteArray(fileOutPut));
                fileInputStream.close();

                response.addHeader(new HttpHeader("Content-Type", FileTypes.htmlText.getType()));
            } else {

                File requestedFile = new File(webRoot + request.getRequestLine().getRequestURI());
                fileInputStream = new FileInputStream(requestedFile);
                byte[] fileOutPut = new byte[ (int) requestedFile.length()];
                fileInputStream.read(fileOutPut);

                data.addAll(Array2ArrayList.byteArray(fileOutPut));

                fileInputStream.close();

                String fileSuffix = (request.getRequestLine().getRequestURI().split("\\."))[1];
                response.addHeader(new HttpHeader("Content-Type", FileTypes.getContentType(fileSuffix)));
                System.out.println("File Suffix: " + fileSuffix);
            }
            response.addHeader(new HttpHeader("Content-Length", String.valueOf(data.size())));

            return data;
        } catch (FileNotFoundException e) {
            response.setStatusCode(HttpStatusCode.CLIENT_ERROR_404_RESOURCE_NOT_FOUND);
            return null;
        }catch (Exception e){
            response.setStatusCode(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return null;
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                }catch (Exception ignored){}
            }
        }
    }

    private ArrayList<Byte> sendStatusLineError(){
        return Array2ArrayList.byteArray(("HTTP/" +
                response.getStatusLine().getVersion().getMajor() + "." +
                response.getStatusLine().getVersion().getMinor() + " " +
                response.getStatusLine().getStatusCode().getCode() + " " +
                response.getStatusLine().getStatusCode().getMessage() +
                "\n\r").getBytes());
    }
}
