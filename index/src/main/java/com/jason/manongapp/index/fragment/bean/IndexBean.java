package com.jason.manongapp.index.fragment.bean;

import java.util.List;

public class IndexBean {


    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * Quotes : 一个人炫耀什么，说明内心缺少什么。
         * createdAt : 2019-03-01 12:27:51
         * date_string : {"__type":"Date","iso":"2019-03-03 00:00:00"}
         * imageUrl : http://file06.16sucai.com/2016/0304/8ff9aea2faae1c99c8a97206ecfdcf3e.jpg
         * objectId : nvjBFFFm
         * updatedAt : 2019-03-02 17:22:38
         */

        private String Quotes;
        private String createdAt;
        private DateStringBean date_string;
        private String imageUrl;
        private String objectId;
        private String updatedAt;

        public String getQuotes() {
            return Quotes;
        }

        public void setQuotes(String Quotes) {
            this.Quotes = Quotes;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public DateStringBean getDate_string() {
            return date_string;
        }

        public void setDate_string(DateStringBean date_string) {
            this.date_string = date_string;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public static class DateStringBean {
            /**
             * __type : Date
             * iso : 2019-03-03 00:00:00
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }
        }
    }
}
