package com.jason.manongapp.more.register;

import android.app.Dialog;

import com.google.gson.Gson;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.more.login.api.LoginService;
import com.jason.manongapp.more.login.bean.UserInfo;
import com.jason.manongapp.more.register.api.RegisterService;
import com.jason.manongapp.more.register.bean.RegisterBean;
import com.jason.manongapp.more.register.bean.RegisterCallBackBean;
import com.orhanobut.logger.Logger;

import okhttp3.RequestBody;

public class RegisterModel implements RegisterContract.Model {

    private RegisterModel() {
    }


    @Override
    public void register(String username, String password, final RegisterContract.Presenter callback, Dialog dialog) {
        RegisterBean registerBean = new RegisterBean(username,password);
        Gson gson = new Gson();
        String obj = gson.toJson(registerBean);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        RxHttpUtils.createApi(RegisterService.class)
                .register(body)
                .compose(Transformer.<RegisterCallBackBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<RegisterCallBackBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        callback.registerError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(RegisterCallBackBean registerCallBackBean) {
                        callback.registerSuccess();
                    }
                });
    }

    private static class SingletonLoader {
        private static final RegisterModel INSTANCE = new RegisterModel();
    }

    public static RegisterModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
