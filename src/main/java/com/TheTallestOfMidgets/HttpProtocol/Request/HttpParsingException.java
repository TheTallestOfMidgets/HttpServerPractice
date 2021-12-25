package com.TheTallestOfMidgets.HttpProtocol.Request;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;

public class HttpParsingException extends Exception{
    private final HttpStatusCode errorCode;

    public HttpParsingException(HttpStatusCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}
