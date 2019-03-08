package com.jason.manongapp.more.findpwd;

import android.app.Dialog;

import com.google.gson.Gson;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.bean.SendSMSCodeBean;
import com.jason.manongapp.more.codelogin.api.CodeLoginServer;
import com.jason.manongapp.more.findpwd.api.FindServer;
import com.jason.manongapp.more.findpwd.bean.FindPwdCallBackBean;
import com.jason.manongapp.more.findpwd.bean.SendFindPwdBodyBean;
import com.orhanobut.logger.Logger;

import okhttp3.RequestBody;

public class FindPwdModel implements FindPwdoginContract.Model {

    private FindPwdModel() {
    }

    @Override
    public void getCode(String phone, String template, final FindPwdoginContract.Presenter presenter, Dialog dialog) {
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
                            presenter.getCodeSuccess();
                        }else {
                            Logger.i("出错啦");
                        }
                    }
                });
    }

    @Override
    public void resetPwd(String code, final String password, final FindPwdoginContract.Presenter presenter, Dialog dialog) {
        SendFindPwdBodyBean sendFindPwdBodyBean = new SendFindPwdBodyBean(password);
        Gson gson = new Gson();
        String jsonBody = gson.toJson(sendFindPwdBodyBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonBody);
        RxHttpUtils.createApi(FindServer.class)
                .reSetPwd(code,body)
                .compose(Transformer.<FindPwdCallBackBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<FindPwdCallBackBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        if (errorMsg != null) {
                            presenter.reSetPwdError(errorMsg);
                        }
                    }

                    @Override
                    protected void onSuccess(FindPwdCallBackBean findPwdCallBackBean) {
                        if (findPwdCallBackBean != null) {
                            presenter.reSetPwdSuccess();
                        }
                    }
                });
    }


    private static class SingletonLoader {
        private static final FindPwdModel INSTANCE = new FindPwdModel();
    }

    public static FindPwdModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
