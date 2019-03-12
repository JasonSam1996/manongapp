package com.jason.manongapp.more.login.bean;

public class QQLoginBody {


    /**
     * authData : {"qq":{"openid":"7DA7F85E96080DFBFBB73256768B2008","access_token":"A8A37891F3CD13865BB7F3A9EF91C936","expires_in":1560176643758}}
     */

    private AuthDataBean authData;

    public AuthDataBean getAuthData() {
        return authData;
    }

    public void setAuthData(AuthDataBean authData) {
        this.authData = authData;
    }

    public QQLoginBody(AuthDataBean authData) {
        this.authData = authData;
    }

    public static class AuthDataBean {
        public AuthDataBean(QqBean qq) {
            this.qq = qq;
        }

        /**
         * qq : {"openid":"7DA7F85E96080DFBFBB73256768B2008","access_token":"A8A37891F3CD13865BB7F3A9EF91C936","expires_in":1560176643758}
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
             * openid : 7DA7F85E96080DFBFBB73256768B2008
             * access_token : A8A37891F3CD13865BB7F3A9EF91C936
             * expires_in : 1560176643758
             */

            private String openid;
            private String access_token;
            private long expires_in;

            public QqBean(String openid, String access_token, long expires_in) {
                this.openid = openid;
                this.access_token = access_token;
                this.expires_in = expires_in;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

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
        }
    }
}
