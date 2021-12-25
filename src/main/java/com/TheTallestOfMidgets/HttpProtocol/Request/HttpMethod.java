package com.TheTallestOfMidgets.HttpProtocol.Request;

public enum HttpMethod {
    GET, POST;

    public static HttpMethod getMethod(String name){
        for(HttpMethod method : HttpMethod.values()){
            if(name.equals(method.name())){
                return method;
            }
        }
        return null;
    }
}
