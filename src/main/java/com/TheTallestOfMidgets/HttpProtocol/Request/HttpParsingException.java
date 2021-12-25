package com.TheTallestOfMidgets.HttpProtocol.Request;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;

public class HttpParsingException extends Exception{
    private final String errorCode;

    public HttpParsingException(String errorCode){
        super(errorCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
