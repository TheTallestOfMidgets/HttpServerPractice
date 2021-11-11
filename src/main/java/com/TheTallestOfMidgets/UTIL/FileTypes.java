package com.TheTallestOfMidgets.UTIL;

public enum FileTypes {

    /*--- Text ---*/
    plainText("txt", "text/plain"),
    htmlText("html", "text/html; charset=UTF-8"),
    cssText("css","text/css"),
    jsText("js", "text/javascript"),

    /*--- font ---*/
    ttfFont("ttf", "font/tiff"),
    tiffFont("tiff", "font/tiff"),

    /*--- Image ---*/
    iconImage("ico", "image/x-icon"),
    jpgImage("jpg", "image/jpeg"),
    jpegImage("jpeg", "image/jpeg"),
    pngImage("png", "image/png"),
    gifImage("gif", "image/gif"),
    bmpImage("bmp", "image/bmp"),

    /*--- Audio ---*/
    accAudio("acc", "audio/aac"),
    mp3Audio("mp3", "audio/mpeg"),
    wavAudio("wav", "audio/wav"),

    /*--- Application ---*/
    pdfApplication("pdf", "application/pdf"),
    unknownApplication("file", "application/octet-stream"),
    binApplication("bin", "application/octet-stream"),
    docApplication("doc", "application/msword"),
    docxApplication("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    jarApplication("jar", "application/java-archive"),
    jsonApplication("json", "application/json"),
    phpApplication("php", "application/x-httpd-php"),
    pptApplication("ppt", "application/vnd.ms-powerpoint"),
    pptxApplication("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    rarApplication("rar", "application/vnd.rar"),
    xmlApplication("xml", "application/xml"),
    zipApplication("zip", "application/zip"),
    sevenZApplication("7z", "application/x-7z-compressed"),

    /*--- Video ---*/
    aviVideo("avi", "video/x-msvideo"),
    mp4Video("mp4", "video/mp4"),
    mpegVideo("mpeg", "video/mpeg"),
    webmVideo("webm", "video/webm");

    private final String suffix;
    private final String type;

    FileTypes(String suffix, String type){
        this.suffix = suffix;
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getType() {
        return type;
    }

    public static String getContentType(String suffix){
            for(FileTypes file : FileTypes.values()){
                if(suffix.equals(file.getSuffix())){
                    return file.getType();
                }
            }
            return unknownApplication.getType();
    }
}
