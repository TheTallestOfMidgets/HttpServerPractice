package com.TheTallestOfMidgets.HttpServer;

import com.TheTallestOfMidgets.UTIL.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    private static final Logger LOGGER = new Logger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.info("Server Starting");
        HttpListenerThread httpListenerThread = new HttpListenerThread(8080);
        httpListenerThread.start();
        LOGGER.info("Listener Thread Booting...");
    }
}
