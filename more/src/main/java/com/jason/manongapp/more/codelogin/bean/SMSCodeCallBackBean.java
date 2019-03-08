package com.jason.manongapp.more.codelogin.bean;

import java.io.Serializable;

public class SMSCodeCallBackBean implements Serializable {


    /**
     * createdAt : 2019-02-18 18:18:20
     * mobilePhoneNumber : 15622299835
     * objectId : 88a8e397c4
     * sessionToken : 415a29e840e8555c8013c4e176f41f61
     * username : 15622299835
     */

    private String createdAt;
    private String mobilePhoneNumber;
    private String objectId;
    private String sessionToken;
    private String username;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SMSCodeCallBackBean{" +
                "createdAt='" + createdAt + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", objectId='" + objectId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
