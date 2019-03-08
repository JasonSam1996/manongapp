package com.jason.manongapp.more.login.bean;

import java.io.Serializable;

public class UserInfo implements Serializable  {


    /**
     * createdAt : 2019-01-29 22:50:28
     * email : 1301954425@qq.com
     * emailVerified : false
     * mobilePhoneNumber : 15622299835
     * mobilePhoneNumberVerified : false
     * objectId : a84ef199a5
     * sessionToken : bd8bf8b540dcc6988094390ef9c308af
     * updatedAt : 2019-01-31 15:56:06
     * username : admin123
     */

    private String createdAt;
    private String email;
    private boolean emailVerified;
    private String mobilePhoneNumber;
    private boolean mobilePhoneNumberVerified;
    private String objectId;
    private String sessionToken;
    private String updatedAt;
    private String username;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public boolean isMobilePhoneNumberVerified() {
        return mobilePhoneNumberVerified;
    }

    public void setMobilePhoneNumberVerified(boolean mobilePhoneNumberVerified) {
        this.mobilePhoneNumberVerified = mobilePhoneNumberVerified;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "createdAt='" + createdAt + '\'' +
                ", email='" + email + '\'' +
                ", emailVerified=" + emailVerified +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", mobilePhoneNumberVerified=" + mobilePhoneNumberVerified +
                ", objectId='" + objectId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
