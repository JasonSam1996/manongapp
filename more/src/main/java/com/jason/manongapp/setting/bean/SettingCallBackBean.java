package com.jason.manongapp.setting.bean;

public class SettingCallBackBean {

    /**
     * msg : ok
     */

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SettingBean{" +
                "msg='" + msg + '\'' +
                '}';
    }

}
