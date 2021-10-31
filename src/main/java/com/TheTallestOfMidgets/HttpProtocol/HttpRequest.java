package com.TheTallestOfMidgets.HttpProtocol;

public class HttpRequest { //what the client sends to the server, packed into one neat object
    private HttpStartLine startLine;
    private HttpHeader[] headers;
    private String messageBody;

    public HttpRequest(){}

    public HttpStartLine getStartLine() {
        return startLine;
    }

    public void setStartLine(HttpStartLine startLine) {
        this.startLine = startLine;
    }

    public HttpHeader[] getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeader[] headers) {
        this.headers = headers;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
