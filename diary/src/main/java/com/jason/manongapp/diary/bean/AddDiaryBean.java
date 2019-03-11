package com.jason.manongapp.diary.bean;

public class AddDiaryBean {


    /**
     * city : 天河区
     * diary_content : http://www.baidu.com你好<img src="http://bmob-cdn-24020.b0.upaiyun.com/2019/03/10/5d2746ea40a3cfc580c8d2c933154abd.jpg"/>
     * diary_title : text3
     * mood : 傻笑
     * user : {"__type":"Pointer","className":"_User","objectId":"88a8e397c4"}
     * weather : 暴雨
     * week : 周三
     */

    private String city;
    private String diary_content;
    private String diary_title;
    private String mood;
    private UserBean user;
    private String weather;
    private String week;

    public AddDiaryBean(String city, String diary_content, String diary_title, String mood, UserBean user, String weather, String week) {
        this.city = city;
        this.diary_content = diary_content;
        this.diary_title = diary_title;
        this.mood = mood;
        this.user = user;
        this.weather = weather;
        this.week = week;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDiary_content() {
        return diary_content;
    }

    public void setDiary_content(String diary_content) {
        this.diary_content = diary_content;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public static class UserBean {
        /**
         * __type : Pointer
         * className : _User
         * objectId : 88a8e397c4
         */

        private String __type;
        private String className;
        private String objectId;

        public UserBean(String __type, String className, String objectId) {
            this.__type = __type;
            this.className = className;
            this.objectId = objectId;
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }
    }
}
