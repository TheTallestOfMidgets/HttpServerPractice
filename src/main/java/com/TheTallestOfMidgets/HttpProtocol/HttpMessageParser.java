package com.TheTallestOfMidgets.HttpProtocol;


import com.TheTallestOfMidgets.UTIL.ArrayList2String;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class HttpMessageParser{

    public HttpMessageParser(InputStream inputStream) throws IOException {
        ArrayList<Integer> statusLine = new ArrayList<Integer>();
        ArrayList<Integer> headers = new ArrayList<Integer>();
        ArrayList<Integer> messageBody = new ArrayList<Integer>();

        boolean statusLineRead = false;
        boolean headersRead = false;
        boolean messageBodyRead = false;

        int _byte;
        while((_byte = inputStream.read()) >= 0){
            if(!statusLineRead){
                //parsing and stuff
                if(_byte == 13){
                    _byte = inputStream.read();
                    if(_byte == 10){
                        statusLineRead = true;
                    }
                }else{
                    statusLine.add(_byte);
                }

            } else if(!headersRead){
                //parsing and stuff
                if(_byte == 13) {
                    _byte = inputStream.read();
                    if (_byte == 10) {
                        _byte = inputStream.read();
                        if (_byte == 13) {
                            _byte = inputStream.read();
                            if (_byte == 10) {
                                headersRead = true;
                            }
                        }
                    }
                }else{
                    headers.add(_byte);
                }
            }
//            else if(!messageBodyRead){
//                //parsing and stuff
//
//                messageBodyRead = true;
//            }
            else{
                break;
            }
        }
        System.out.println("Status Line: " + ArrayList2String.IntArrayList2String(statusLine));
        System.out.println("Headers: " + ArrayList2String.IntArrayList2String(headers));
        System.out.println("Message Body: " + ArrayList2String.IntArrayList2String(messageBody));
    }


}
