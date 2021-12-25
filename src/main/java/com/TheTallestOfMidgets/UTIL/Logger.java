package com.TheTallestOfMidgets.UTIL;

public class Logger {
    private final Class clazz;
    private boolean debugMode;
    private boolean writeToFile;
    private String logFilePath;
    //TODO add file writing support
    public Logger(Class clazz) {
        this.clazz = clazz;
        debugMode = false;
    }

    public void setDebugMode(boolean mode){
        debugMode = mode;
    }



    public void info(String msg){
        String terminalString = "[" + new StringBuilder(java.time.LocalTime.now().toString()).delete(12,18) +"]" +
                "[" + clazz.getSimpleName() + "/INFO" +"]" +
                " - "  + msg;
        System.out.println(terminalString);
    }

    public void debug(String msg){
        if(debugMode) {
            String terminalString = "[" + new StringBuilder(java.time.LocalTime.now().toString()).delete(12,18) +"]" +
                    "[" + clazz.getSimpleName() + "/DEBUG" +"]" +
                    " - "  + msg;
            System.out.println(terminalString);
        }
    }

    public void error(String msg, Exception e){
        String terminalString = "[" + new StringBuilder(java.time.LocalTime.now().toString()).delete(12,18) +"]" +
                "[" + clazz.getSimpleName() + "/ERROR" +"]" +
                " - "  + msg;
        System.out.println(terminalString);
        e.printStackTrace();
    }
}
