package com.TheTallestOfMidgets.HttpProtocol;


import com.TheTallestOfMidgets.UTIL.ArrayList2String;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class HttpMessageParser{
    private static final int CR = 13;
    private static final int LF = 10;
    private static final int SP = 20;

    public HttpMessageParser(InputStream inputStream) throws IOException {
        ArrayList<Integer> request = new ArrayList<Integer>();

        int _byte;
        while((_byte = inputStream.read()) >= 0) {
            if(_byte == CR){
                request.add(_byte);
                _byte = inputStream.read();
                if(_byte == LF){
                    request.add(_byte);
                    _byte = inputStream.read();
                    if(_byte == CR){
                        request.add(_byte);
                        _byte = inputStream.read();
                        if(_byte == LF){
                            request.add(_byte);
                            break;
                        }
                    }
                }
            }
            request.add(_byte);
        }
    }
}
