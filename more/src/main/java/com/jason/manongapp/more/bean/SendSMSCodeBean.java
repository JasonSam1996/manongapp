package com.jason.manongapp.more.bean;

public class SendSMSCodeBean {

    /**
     * mobilePhoneNumber : phoneNum
     * template : templateName(选填，需先在管理后台创建)
     */

    private String mobilePhoneNumber;
    private String template;

    public SendSMSCodeBean(String mobilePhoneNumber, String template) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.template = template;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "SendSMSCodeBean{" +
                "mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", template='" + template + '\'' +
                '}';
    }
}
