package com.TheTallestOfMidgets.HttpProtocol;

public class HttpStartLine {
    private final HttpMethod method;
    private final String requestURI;
    private final String version;
    public HttpStartLine(HttpMethod method, String requestURI, String version){
        this.method = method;
        this.requestURI = requestURI;
        this.version = version;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getVersion() {
        return version;
    }

}
