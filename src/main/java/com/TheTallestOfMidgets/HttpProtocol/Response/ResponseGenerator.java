package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequest;

public class ResponseGenerator {
    private final HttpRequest request;
    private HttpResponse response;
    private String responseBody;
    private String webRoot;

    public ResponseGenerator(HttpRequest request){
        this.request = request;
        this.webRoot = "";
    }
    //TODO functional webroot reading
    //TODO generate proper responses
    //TODO figure out request uri structure and parsing
    public void generateResponse(){

    }
}
