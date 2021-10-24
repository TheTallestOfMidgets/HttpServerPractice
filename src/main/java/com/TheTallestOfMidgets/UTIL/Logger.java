package com.TheTallestOfMidgets.UTIL;

public class Logger {
    private final Class clazz;

    public Logger(Class clazz) {
        this.clazz = clazz;
    }
    public void info(String msg){
        String terminalString = java.time.LocalTime.now() +" " +
                "[" + clazz.getSimpleName() + "] " +"INFO " +
                clazz.getPackage().toString().replace("package ","") +
                " - "  + msg;
        System.out.println(terminalString);
    }
    public void debug(String msg){
        String terminalString = java.time.LocalTime.now() +" " +
                "[" + clazz.getSimpleName() + "] " +"DEBUG " +
                clazz.getPackage().toString().replace("package ","") +
                " - "  + msg;
        System.out.println(terminalString);
    }
    public void error(String msg, Exception e){
        String terminalString = java.time.LocalTime.now() +" " +
                "[" + clazz.getSimpleName() + "] " +"ERROR " +
                clazz.getPackage().toString().replace("package ","") +
                " - "  + msg;
        System.out.println(terminalString);
        e.printStackTrace();
    }
}
