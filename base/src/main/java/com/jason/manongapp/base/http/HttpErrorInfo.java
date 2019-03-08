package com.jason.manongapp.base.http;

public class HttpErrorInfo {


    /**
     *
     * code : 101
     * error : username or password incorrect.
     */

    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



}
