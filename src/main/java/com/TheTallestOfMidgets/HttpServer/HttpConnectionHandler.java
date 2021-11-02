package com.TheTallestOfMidgets.HttpServer;

import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequestParser;
import com.TheTallestOfMidgets.UTIL.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

        try {
            outputStream = client.getOutputStream();
            inputStream = client.getInputStream();
            LOGGER.info("Thread " + this.getId() + " established connection with " + this.client.getInetAddress());

            LOGGER.info("Thread " + this.getId() + " reading request...");

            //TODO read browser request
                HttpRequestParser httpRequestParser = new HttpRequestParser(inputStream);
                httpRequestParser.parseRequest();

            LOGGER.info("Thread " + this.getId() + " Done!");

            //TODO respond
            LOGGER.info("Thread " + this.getId() + " generating response");
            sleep((long) (Math.random() * 800));
            String html = "<html><head><title>YOOO I'm In a tab</title><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"></head><body><div><h1>This is a website (and sorry matt, p5 does not like http protocol)</h1></div></body></html>";


            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length +
                    CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            outputStream.write(response.getBytes());
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
