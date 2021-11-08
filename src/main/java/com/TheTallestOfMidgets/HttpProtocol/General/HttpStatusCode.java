package com.TheTallestOfMidgets.HttpProtocol.General;

public enum HttpStatusCode {


    /*--- Informational ---*/
    INFO_100_CONTINUTE(100,"Continute"),

    /*--- Success ---*/
    SUCCESS_200(200, "Ok"),
    SUCCESS_201_CREATED(201, "Created"),
    SUCCESS_202_ACCEPTED(202, "Accepted"),

    /*--- Redirection ---*/


    /*--- Client Errors ---*/
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_403_FORBIDDEN(403, "Forbidden"),
    CLIENT_ERROR_404_RESOURCE_NOT_FOUND(404, "Not Found"),
    CLIENT_ERROR_408_REQUEST_TIMEOUT(408, "Request Timeout"),

    /*--- Server Errors ---*/
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_METHOD_NOT_IMPLEMENTED(501, "Requested Method is not Implemented"),
    SERVER_ERROR_505_HTTP_VERSION_UNSUPPORTED(505, "HTTP Version Not Supported");




    private final int code;
    private final String message;

    HttpStatusCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
