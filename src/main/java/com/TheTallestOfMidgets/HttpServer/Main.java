package com.TheTallestOfMidgets.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int port = 80;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        Socket client = serverSocket.accept();

        InputStream input = client.getInputStream();
        OutputStream output = client.getOutputStream();

        //TODO read browser request

        //TODO respond

        String html = "<!DOCTYPE html><html><body><h1>Heading</h1><p>Paragraph (oh also the webpage worked)</p></body></html>";


        String response;

        output.write(response);
    }
}
