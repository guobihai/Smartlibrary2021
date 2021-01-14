package com.smart.library.exception;

public class ApiException extends Exception {
    private String code;
    private String msg;

    public ApiException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}