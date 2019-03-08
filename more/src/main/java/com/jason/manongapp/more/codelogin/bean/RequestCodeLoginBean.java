package com.jason.manongapp.more.codelogin.bean;

public class RequestCodeLoginBean {


    public RequestCodeLoginBean(String mobilePhoneNumber, String smsCode) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.smsCode = smsCode;
    }

    /**
     * mobilePhoneNumber : 15622299835
     * smsCode : 929921
     */



    private String mobilePhoneNumber;
    private String smsCode;

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "RequestCodeLoginBean{" +
                "mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
