package com.TheTallestOfMidgets.HttpServer;

import com.TheTallestOfMidgets.UTIL.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpListenerThread extends Thread{

    private static final Logger LOGGER = new Logger(HttpListenerThread.class);

    private final ServerSocket serverSocket;

    public HttpListenerThread(int port) throws IOException {

//        System.setProperty("javax.net.ssl.keyStoreType", "jks");
//        System.setProperty("javax.net.ssl.trustStoreType", "jks");
//        System.setProperty("javax.net.ssl.keyStore", "za.store");
//        System.setProperty("javax.net.ssl.trustStore", "cacerts");
//        System.setProperty("javax.net.ssl.keyStorePassword", "|86iJ<=@9fD^@C,!<m$-");
//        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        //System.setProperty("javax.net.debug", "ssl");

        serverSocket = new ServerSocket(port);
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
                httpConnectionHandler.start();
            } catch (IOException ignored) {}
        }
    }
}
