package com.TheTallestOfMidgets.HttpProtocol.Request;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpVersion;

public class HttpRequestLine {
    private final String method;
    private final String requestURI;
    private final HttpVersion version;
    public HttpRequestLine(String method, String requestURI, HttpVersion version){
        this.method = method;
        this.requestURI = requestURI;
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public HttpVersion getVersion() {
        return version;
    }

}
