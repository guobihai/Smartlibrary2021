package com.smart.library.network.execption;


/**
 * 异常处理
 * @author  guobihai
 * @date 2019/03/14
 */
public class ApiException extends Exception {
    private int code;
    private String msg;

    public ApiException(int code, String displayMessage) {
        this.code = code;
        this.msg = displayMessage;
    }

    public ApiException(int code, String message, String displayMessage) {
        super(message);
        this.code = code;
        this.msg = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return msg;
    }

    public void setDisplayMessage(String displayMessage) {
        this.msg = displayMessage;
    }


}
