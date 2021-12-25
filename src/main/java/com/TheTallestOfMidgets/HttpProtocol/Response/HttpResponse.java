package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;
import com.TheTallestOfMidgets.HttpProtocol.General.HttpStatusCode;
import com.TheTallestOfMidgets.HttpProtocol.Request.HttpRequestLine;

import java.util.ArrayList;

public class HttpResponse {
    private HTTPStatusLine statusLine;
    private ArrayList<HttpHeader> headers;
    private ArrayList<Byte> messageBody;

    public HttpResponse(){
        headers = new ArrayList<HttpHeader>(){};
    }

    public void setStatusLine(HTTPStatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public HTTPStatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusCode(HttpStatusCode statusCode){
        this.statusLine.setStatusCode(statusCode);
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

    public ArrayList<Byte> getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(ArrayList<Byte> messageBody) {
        this.messageBody = messageBody;
    }
}
