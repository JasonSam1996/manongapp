package com.jason.manongapp.setting;

import android.app.Dialog;

import com.google.gson.Gson;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.setting.api.SettingService;
import com.jason.manongapp.setting.bean.SettingBean;
import com.jason.manongapp.setting.bean.SettingCallBackBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class SettingModel implements SettingContract.Model {

    private SettingModel() {
    }

    @Override
    public void reSetPwd(String objectId, String oldPassword, String newPassword, final SettingContract.Presenter presenter, Dialog dialog) {
        Map<String,Object> headers = new HashMap<>();
        headers.put("X-Bmob-Application-Id","3e2c91cb95ceefc5b4ad4da9916d1888");
        headers.put("X-Bmob-REST-API-Key","437139b0987ff441647f635727c93fbf");
        headers.put("X-Bmob-Session-Token",SPUtils.get("session_token",""));
        headers.put("Content-Type","application/json");
        SettingBean settingBean = new SettingBean(oldPassword,newPassword);
        Gson gson = new Gson();
        String obj = gson.toJson(settingBean);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);
        RxHttpUtils.getSInstance()
                .addHeaders (headers)
                .createSApi(SettingService.class)
                .reSetPwd(objectId,body)
                .compose(Transformer.<SettingCallBackBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<SettingCallBackBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        presenter.reSetPwdError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(SettingCallBackBean settingCallBackBean) {
                        if (settingCallBackBean != null) {
                            presenter.reSetPwdSuccess(settingCallBackBean);
                        }
                    }
                });

    }


    private static class SingletonLoader {
        private static final SettingModel INSTANCE = new SettingModel();
    }

    public static SettingModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
