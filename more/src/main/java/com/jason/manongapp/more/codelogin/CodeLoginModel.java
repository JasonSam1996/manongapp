package com.jason.manongapp.more.codelogin;

import android.app.Dialog;

import com.google.gson.Gson;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.more.codelogin.api.CodeLoginServer;
import com.jason.manongapp.more.codelogin.bean.RequestCodeLoginBean;
import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;
import com.jason.manongapp.more.bean.SendSMSCodeBean;
import com.orhanobut.logger.Logger;

import okhttp3.RequestBody;

public class CodeLoginModel implements CodeLoginContract.Model {

    private CodeLoginModel() {
    }

    @Override
    public void codeLogin(String phone, String smsCode, final CodeLoginContract.Presenter callback, Dialog dialog) {
        RequestCodeLoginBean requestCodeLoginBean = new RequestCodeLoginBean(phone,smsCode);
        Gson gson = new Gson();
        String jsonSendSMSCodeBean = gson.toJson(requestCodeLoginBean);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonSendSMSCodeBean);
        RxHttpUtils.createApi(CodeLoginServer.class)
                .codeLogin(body)
                .compose(Transformer.<SMSCodeCallBackBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<SMSCodeCallBackBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        callback.codeLoginError(errorMsg);
                        Logger.i("-1错误："+errorMsg);
                    }

                    @Override
                    protected void onSuccess(SMSCodeCallBackBean smsCodeCallBackBean) {
                        callback.codeLoginSuccess(smsCodeCallBackBean);
                        Logger.i("1成功："+smsCodeCallBackBean.toString());
                    }
                });
    }

    @Override
    public void getCode(String phone, String template, final CodeLoginContract.Presenter presenter, Dialog dialog) {
        SendSMSCodeBean sendSMSCodeBean = new SendSMSCodeBean(phone,template);
        Gson gson = new Gson();
        String jsonSendSMSCodeBean = gson.toJson(sendSMSCodeBean);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonSendSMSCodeBean);
        RxHttpUtils.createApi(CodeLoginServer.class)
                .getCode(body)
                .compose(Transformer.<SMSCodeBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<SMSCodeBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        presenter.getCodeError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SMSCodeBean smsCodeBean) {
                        if (smsCodeBean != null) {
                            presenter.getCodeSuccess(smsCodeBean);
                        }else {
                            Logger.i("出错啦");
                        }
                    }
                });

    }


    private static class SingletonLoader {
        private static final CodeLoginModel INSTANCE = new CodeLoginModel();
    }

    public static CodeLoginModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
