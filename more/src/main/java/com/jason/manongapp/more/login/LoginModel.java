package com.jason.manongapp.more.login;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.google.gson.Gson;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.login.api.LoginService;
import com.jason.manongapp.more.login.bean.QQLoginBody;
import com.jason.manongapp.more.login.bean.QQLoginCallBack;
import com.jason.manongapp.more.login.bean.UserInfo;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import okhttp3.RequestBody;


public class LoginModel implements LoginContract.Model {

    private LoginModel() {
    }

    private static class SingletonLoader {
        private static final LoginModel INSTANCE = new LoginModel();
    }

    public static LoginModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

    @Override
    public void login(String username, String password, final LoginContract.Presenter callback, Dialog dialog) {
        RxHttpUtils.createApi(LoginService.class)
                .login(username, password)
                .compose(Transformer.<UserInfo>switchSchedulers(dialog))
                .subscribe(new CommonObserver<UserInfo>() {
                    @Override
                    protected void onError(String errorMsg) {
                        callback.loginFailed(errorMsg);
                    }

                    @Override
                    protected void onSuccess(UserInfo userInfo) {
                        callback.loginSuccess(userInfo);
                        Logger.i(userInfo.toString());
                    }
                });

    }

    @Override
    public void qqLogin(final Activity activity, final LoginContract.Presenter callback, final Dialog dialog) {

        UMShareConfig config = new UMShareConfig();

        config.isNeedAuthOnGetUserInfo(true);

        UMShareAPI.get(activity).setShareConfig(config);

        UMShareAPI.get(activity).getPlatformInfo(activity, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.i("TAG", "onStart " + "授权开始");
                dialog.show();
//                LoadingDialog dialog = new LoadingDialog(activity,R.style.MyDialog);
//                dialog.show();
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map) {

                Log.i("TAG", "onStart " + "授权完成");
//                Log.i("TAG", "onComplete: " + map.toString());
//                Log.i("TAG", "onComplete: " + map.get("accessToken"));
                Logger.i("map："+map.toString());
                Gson gson = new Gson();
                QQLoginBody.AuthDataBean.QqBean qqBean = new QQLoginBody.AuthDataBean.QqBean(map.get("openid"),map.get("access_token"),Long.parseLong(map.get("expires_in")));
                QQLoginBody.AuthDataBean authDataBean = new QQLoginBody.AuthDataBean(qqBean);
                QQLoginBody qqLoginBody = new QQLoginBody(authDataBean);
                String bodySyr = gson.toJson(qqLoginBody);
                RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),bodySyr);
                RxHttpUtils.createApi(LoginService.class)
                        .qqLogin(body)
                        .compose(Transformer.<QQLoginCallBack>switchSchedulers())
                        .subscribe(new CommonObserver<QQLoginCallBack>() {
                            @Override
                            protected void onError(String errorMsg) {
                                callback.qqLoginFail(errorMsg);
                            }

                            @Override
                            protected void onSuccess(QQLoginCallBack qqLoginCallBack) {
                                if (qqLoginCallBack != null) {
                                    map.put("objectId",qqLoginCallBack.getObjectId());
                                    map.put("sessionToken",qqLoginCallBack.getSessionToken());
                                    callback.qqLoginSuccess(map,dialog);
                                }
                            }
                        });
//                callback.qqLoginSuccess(map,dialog);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.i("TAG", "onStart " + "授权失败"+throwable.getMessage());
                callback.qqLoginFail(throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.i("TAG", "onStart " + "授权取消");
                dialog.dismiss();
                callback.qqLoginFail("取消授权");
            }
        });
    }
}
