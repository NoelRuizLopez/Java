package com.avg.model;

public class Response {
    private int responseCode;
    private String message;

    public Response() {
        super();
    }


    public Response(int responseCode, String message) {
        super();
        this.responseCode = responseCode;
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}