package com.jason.manongapp.more.register.bean;

public class RegisterCallBackBean {


    /**
     * createdAt : 2011-11-07 20:58:34
     * objectId : Kc3M222J
     * sessionToken : pnktnjyb996sj4p156gjtp4im
     */

    private String createdAt;
    private String objectId;
    private String sessionToken;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "RegisterBean{" +
                "createdAt='" + createdAt + '\'' +
                ", objectId='" + objectId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
