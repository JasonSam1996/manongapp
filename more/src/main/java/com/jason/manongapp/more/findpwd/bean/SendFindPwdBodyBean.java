package com.jason.manongapp.more.findpwd.bean;

public class SendFindPwdBodyBean {


    public SendFindPwdBodyBean(String password) {
        this.password = password;
    }

    /**
     * password : 123123
     */


    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
