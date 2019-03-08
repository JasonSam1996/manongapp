package com.jason.manongapp.more.bean;

public class SMSCodeBean {

    /**
     * 共同getCode的bean
     * smsId : smsId（可用于后面使用查询短信状态接口来查询该条短信是否发送成功）
     */

    private String smsId;

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    @Override
    public String toString() {
        return "SMSCodeCallBack{" +
                "smsId='" + smsId + '\'' +
                '}';
    }
}
