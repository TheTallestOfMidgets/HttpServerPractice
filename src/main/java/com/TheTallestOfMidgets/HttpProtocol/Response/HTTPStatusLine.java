package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpVersion;

public class HTTPStatusLine {
    private HttpVersion version;
    private HttpStatusCode statusCode;

    public HTTPStatusLine(){
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public HttpVersion getVersion() {
        return version;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }
}
