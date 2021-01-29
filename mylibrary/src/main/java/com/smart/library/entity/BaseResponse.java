package com.smart.library.entity;


/**
 * @author guobihai
 * 创建日期：2021/1/29
 * desc：
 *
 */
public class BaseResponse<T> {

    private String status;
    private String msg;
    private T data;
    private boolean succeed;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
