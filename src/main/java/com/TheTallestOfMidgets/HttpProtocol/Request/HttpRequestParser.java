package com.TheTallestOfMidgets.HttpProtocol.Request;


import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpVersion;
import com.TheTallestOfMidgets.UTIL.ArrayList2String;
import com.TheTallestOfMidgets.UTIL.Logger;
import jdk.nashorn.internal.runtime.ParserException;

import java.io.InputStream;
import java.util.ArrayList;


public class HttpRequestParser{

    private static final Logger LOGGER = new Logger(HttpRequestParser.class);

    private static final int CR = 13;
    private static final int LF = 10;
    private static final int SP = 20;
    private final InputStream inputStream;

    private boolean requestLineRead;
    private boolean headersRead;
    private boolean hasMessageBody;
    private boolean messageBodyRead;

    private final HttpRequest request;


    public HttpRequestParser(InputStream inputStream){
        this.requestLineRead = false;
        this.headersRead = false;
        this.hasMessageBody = false;
        this.messageBodyRead = false;
        this.request = new HttpRequest();
        this.inputStream = inputStream;
        LOGGER.info("Initialized parser");
    }

    public HttpRequest parseRequest() {
        ArrayList<Integer> requestBuffer;
        try {
            LOGGER.info("Running Parser");
            int _byte = 0;
            requestBuffer = new ArrayList<>(); //Used to store the current block of the request we are on
            while (_byte >= 0) {
                _byte = inputStream.read();
                if (!requestLineRead) {
                    if (_byte == CR) {
                        _byte = inputStream.read();
                        if (_byte == LF) {
                            requestLineRead = true;
                            request.setRequestLine(parseRequestLine(requestBuffer));
                            requestBuffer.clear();
                        }
                    }
                    requestBuffer.add(_byte);
                } else if (!headersRead) {
                    if (_byte == CR) {
                        _byte = inputStream.read();
                        if (_byte == LF) {
                            _byte = inputStream.read();
                            request.addHeader(parseHeader(requestBuffer));
                            requestBuffer.clear();
                            if (_byte == CR) {
                                _byte = inputStream.read();
                                if (_byte == LF) {
                                    headersRead = true;
                                    for (HttpHeader header : request.getHeaders()) {
                                        if (header.getField().equalsIgnoreCase("Content-Length")) {
                                            if(Integer.parseInt(header.getValue()) > 0) {
                                                hasMessageBody = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!hasMessageBody) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    requestBuffer.add(_byte);


                } else if (hasMessageBody && !messageBodyRead) {
                    requestBuffer.add(_byte);
                    for (int i = 0; i < Integer.parseInt(request.getHeaderValue("content-length")) - 1; i++) {
                        _byte = inputStream.read();
                        requestBuffer.add(_byte);
                    }
                    request.setMessageBody(ArrayList2String.IntArray(requestBuffer));
                    requestBuffer.clear();
                    messageBodyRead = true;
                    break;
                } else {
                    break;
                }
            }
            return request;
        } catch (Exception e) {
            LOGGER.error("Parsing Failed", e);
        }
        return null;
    }
    private HttpRequestLine parseRequestLine(ArrayList<Integer> rawRequest) throws ParserException{
        try {
            String requestLine = ArrayList2String.IntArray(rawRequest);
            String[] pieces = requestLine.split(" ");
            String method = pieces[0];
            String requestURI = pieces[1];
            String[] rawVersion = pieces[2].split("/")[1].split("\\.");
            int major = Integer.parseInt(rawVersion[0]);
            int minor = Integer.parseInt(rawVersion[1]);
            HttpVersion version = new HttpVersion(major, minor);

            return new HttpRequestLine(method, requestURI, version);
        }catch (Exception e){
            throw new ParserException("Request Line Parsing failed");
        }
    }
    private HttpHeader parseHeader(ArrayList<Integer> rawRequest) throws ParserException{
        try {
            String header = ArrayList2String.IntArray(rawRequest);
            String[] pieces = header.split(":");
            String field = pieces[0].toLowerCase();
            String value = pieces[1].replace(" ", "");

            return new HttpHeader(field, value);
        }catch (Exception e){
            throw new ParserException("Header Parsing Failed");
        }
    }
    private String parseMessageBody(ArrayList<Integer> rawRequest) throws ParserException{
        try {
            return ArrayList2String.IntArray(rawRequest);
        }catch (Exception e){
            throw new ParserException("Header Parsing Failed");
        }
    }
}
