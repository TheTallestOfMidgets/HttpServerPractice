package com.TheTallestOfMidgets.HttpServer;

import com.TheTallestOfMidgets.UTIL.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpListenerThread extends Thread{

    private static final Logger LOGGER = new Logger(HttpListenerThread.class);

    private final int port;
    private final ServerSocket serverSocket;

    public HttpListenerThread(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        LOGGER.info("Done!");
        Socket client = null;
        while(serverSocket.isBound() && !serverSocket.isClosed()){
            try {
                client = serverSocket.accept();
                LOGGER.info("Incoming Connection from " + client.getInetAddress());
                HttpConnectionHandler httpConnectionHandler = new HttpConnectionHandler(client);
                httpConnectionHandler.run();
            } catch (IOException ignored) {}

        }
    }
}
