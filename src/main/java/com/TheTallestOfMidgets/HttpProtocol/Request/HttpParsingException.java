package com.TheTallestOfMidgets.HttpProtocol.Request;

public class HttpParsingException extends Exception{
    public HttpParsingException(String errorMessage){
        super(errorMessage);
    }
}
