package com.jason.manongapp.diary.bean;

import java.util.List;

public class DiaryBean {


    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * addDate : {"__type":"Date","iso":"2019-03-09 23:00:00"}
         * createdAt : 2019-03-09 22:16:42
         * diary_content : 你好
         * <img src="http://bmob-cdn-24020.b0.upaiyun.com/2019/03/09/5b1db6fe403f7c8380568d6afd840e6a.jpg"/>
         * diary_title : test2
         * mood : 伤心
         * objectId : Sg5h0006
         * update_date : {"__type":"Date","iso":"2019-03-09 00:00:00"}
         * updatedAt : 2019-03-09 22:19:11
         * user : {"__type":"Pointer","className":"_User","objectId":"6d33de57b1"}
         * weather : 多云
         * week : 周三
         */

        private String city;
        private String createdAt;
        private String diary_content;
        private String diary_title;
        private String mood;
        private String objectId;
        private String updatedAt;
        private UserBean user;
        private String weather;
        private String week;


        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }


        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
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

    }


    public static class UserBean {
        /**
         * __type : Pointer
         * className : _User
         * objectId : 6d33de57b1
         */

        private String __type;
        private String className;
        private String objectId;

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


