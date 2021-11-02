package com.TheTallestOfMidgets.HttpProtocol.Request;

import com.TheTallestOfMidgets.HttpProtocol.HttpHeader;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequestLine;

import java.util.ArrayList;

public class HttpRequest { //what the client sends to the server, packed into one neat object
    private HttpRequestLine requestLine;
    private ArrayList<HttpHeader> headers;
    private String messageBody;

    public HttpRequest(){
        headers = new ArrayList<HttpHeader>(){};
        messageBody = null;
    }
    public HttpRequestLine getRequestLine() {
        return requestLine;
    }

    public void setRequestLine(HttpRequestLine startLine) {
        this.requestLine = startLine;
    }

    public ArrayList<HttpHeader> getHeaders() {
        return headers;
    }

    public String getValue(String headerName){
        for(HttpHeader header : headers){
            if(headerName.equals(header.getField())){
                return header.getValue();
            }
        }
        return null;
    }

    public void addHeader(HttpHeader header) {
        this.headers.add(header);
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void print(){
        System.out.println("Request Line: ");
        System.out.println("     Request Line: " + requestLine.getMethod().name() + " " + requestLine.getRequestURI() + " " + requestLine.getVersion());
        System.out.println("Headers: ");
        for(HttpHeader header : headers){
            System.out.println("     " + header.getField() + ": " + header.getValue());
        }
        if(messageBody != null) {
            System.out.println("Message Body: ");
            System.out.println("     " + messageBody + "\n ");
        }
    }
}
