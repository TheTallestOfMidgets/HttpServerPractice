package com.TheTallestOfMidgets.HttpProtocol.Request;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;

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

    public void setRequestLine(HttpRequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public ArrayList<HttpHeader> getHeaders() {
        return headers;
    }

    public String getHeaderValue(String headerName){
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
        System.out.println("     Request Line: " + requestLine.getMethod() + " " + requestLine.getRequestURI() + " HTTP/" + requestLine.getVersion().getMajor() + "." + requestLine.getVersion().getMinor());
        System.out.println("Headers: ");
        for(HttpHeader header : headers){
            System.out.println("     " + header.getField() + ": " + header.getValue());
        }
        if(messageBody != null) {
            System.out.println("Message Body: ");
            System.out.println("     " + messageBody + "\n ");
        }
    }
    public boolean hasHeader(String headerName){
        for(HttpHeader header : this.headers){
            if(header.getField().equalsIgnoreCase(headerName)){
                return true;
            }
        }
        return false;
    }
}
