package com.TheTallestOfMidgets.HttpProtocol;


import com.TheTallestOfMidgets.UTIL.ArrayList2String;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class HttpMessageParser{
    private static final int CR = 13;
    private static final int LF = 10;
    private static final int SP = 20;
    private InputStream inputStream;

    private boolean startLineRead = false;
    private boolean headersRead = false;
    private boolean hasMessageBody = false;
    private boolean messageBodyRead = false;

    private HttpRequest request;


    public HttpMessageParser(InputStream inputStream) throws IOException {
        this.startLineRead = false;
        this.headersRead = false;
        this.hasMessageBody = false;
        this.messageBodyRead = false;
        this.request = new HttpRequest();
    }
    /*
    TODO:
        this :)
     */
    public HttpRequest parseMessage(){}
    private HttpStartLine parseStartLine(){}
    private HttpHeader[] parseHeaders(){}
    private String parseMessageBody(){}
}
