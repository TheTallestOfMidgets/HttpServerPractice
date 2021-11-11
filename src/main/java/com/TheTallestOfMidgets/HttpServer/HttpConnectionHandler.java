package com.TheTallestOfMidgets.HttpServer;

import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequest;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequestParser;
import com.TheTallestOfMidgets.HttpProtocol.Response.ResponseGenerator;
import com.TheTallestOfMidgets.UTIL.Array2ArrayList;
import com.TheTallestOfMidgets.UTIL.ArrayList2Array;
import com.TheTallestOfMidgets.UTIL.ArrayList2String;
import com.TheTallestOfMidgets.UTIL.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class HttpConnectionHandler extends Thread{

    private static final Logger LOGGER = new Logger(HttpConnectionHandler.class);

    private static final byte CR = 0x0D;     // CR                    = <US-ASCII CR, carriage return (13)>
    private static final byte LF = 0x0A;     // LF             = <US-ASCII LF, linefeed (10)>
    private static final int SP = 0x20;     // SP             = <US-ASCII SP, space (32)>
    private static final String CRLF = "\n\r";

    private final Socket client;

    public HttpConnectionHandler(Socket socket){
        this.client = socket;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        InputStream inputStream = null;

        masterBlock: try {
            outputStream = client.getOutputStream();
            inputStream = client.getInputStream();
            LOGGER.info("Thread " + this.getId() + " established connection with " + this.client.getInetAddress());

            LOGGER.info("Thread " + this.getId() + " reading request...");

            //TODO read browser request
            HttpRequest request;
                if(inputStream.available() > 0) {
                    HttpRequestParser httpRequestParser = new HttpRequestParser(inputStream);
                    request = httpRequestParser.parseRequest();
                } else{
                    break masterBlock;
                }

            LOGGER.info("Thread " + this.getId() + " Done!");

            //TODO respond

            ArrayList<Byte> response;
            ResponseGenerator responseGenerator = new ResponseGenerator(request);
            response = responseGenerator.generateResponse();

            LOGGER.info("Thread " + this.getId() + " generating response");
            String html = "<html><head><title>YOOO I'm In a tab</title><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></head><body><div><h1>This is a website (and sorry matt, p5 does not like http protocol)</h1></div></body></html>";

            outputStream.write(ArrayList2Array.byteArray(response));

            LOGGER.info("Thread " + this.getId() + " Response Sent!");


        } catch (Exception e){
            LOGGER.error("Thread " + this.getId() +" failed!", e);
        }finally{
            LOGGER.info("Thread " + this.getId() + " closing...");
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {}
            }
            if(client != null) {
                try {
                    client.close();
                } catch (IOException ignored) {}
            }
        }
    }
}
