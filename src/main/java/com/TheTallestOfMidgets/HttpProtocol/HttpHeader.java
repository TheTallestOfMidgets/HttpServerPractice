package com.TheTallestOfMidgets.HttpProtocol;

public class HttpHeader {
    private final String field;
    private final String value;

    public HttpHeader(String field, String value){
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
