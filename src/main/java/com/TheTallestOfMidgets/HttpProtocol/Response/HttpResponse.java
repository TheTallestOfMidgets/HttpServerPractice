package com.TheTallestOfMidgets.HttpProtocol.Response;

import com.TheTallestOfMidgets.HttpProtocol.General.HttpHeader;

import java.util.ArrayList;

public class HttpResponse {
    private HTTPStatusLine statusLine;
    private ArrayList<HttpHeader> headers;
}
