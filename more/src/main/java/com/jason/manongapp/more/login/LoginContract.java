package com.jason.manongapp.more.login;


import android.app.Activity;
import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.more.login.bean.UserInfo;

import java.util.Map;

/**
 * MVPPlugin
 */

public class LoginContract {
    interface View extends BaseView {

        void showToast(String msg);

        String getUserName();

        String getPassWord();

        void onFinishCallBack(UserInfo info);

        void onQQFinishCallBack(Map<String,String> qqMap,Dialog dialog);

    }


    interface Model {
        void login(String username, String password, Presenter callback, Dialog dialog);
        void qqLogin(Activity activity,Presenter callback,Dialog dialog);
    }

    interface Presenter extends BasePresenter<View> {
        void loginSuccess(UserInfo info);

        void loginFailed(String errorMsg);

        void qqLoginSuccess(Map<String,String> qqMap,Dialog dialog);

        void qqLoginFail(String errorMsg);

    }

}
