package com.jason.manongapp.more.login.bean;

public class QQLoginCallBack {


    /**
     * authData : {"qq":{"access_token":"A8A37891F3CD13865BB7F3A9EF91C936","expires_in":1560176643758,"openid":"7DA7F85E96080DFBFBB73256768B2008"}}
     * createdAt : 2019-03-12 22:27:28
     * objectId : dc59b5f3c1
     * sessionToken : eee386c44076cd3e805168e267736359
     * username : 35f3e99e4eaaae18
     */

    private AuthDataBean authData;
    private String createdAt;
    private String objectId;
    private String sessionToken;
    private String username;

    public AuthDataBean getAuthData() {
        return authData;
    }

    public void setAuthData(AuthDataBean authData) {
        this.authData = authData;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class AuthDataBean {
        /**
         * qq : {"access_token":"A8A37891F3CD13865BB7F3A9EF91C936","expires_in":1560176643758,"openid":"7DA7F85E96080DFBFBB73256768B2008"}
         */

        private QqBean qq;

        public QqBean getQq() {
            return qq;
        }

        public void setQq(QqBean qq) {
            this.qq = qq;
        }

        public static class QqBean {
            /**
             * access_token : A8A37891F3CD13865BB7F3A9EF91C936
             * expires_in : 1560176643758
             * openid : 7DA7F85E96080DFBFBB73256768B2008
             */

            private String access_token;
            private long expires_in;
            private String openid;

            public String getAccess_token() {
                return access_token;
            }

            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }

            public long getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(long expires_in) {
                this.expires_in = expires_in;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }
        }
    }
}
