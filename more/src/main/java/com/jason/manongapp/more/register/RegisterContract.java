package com.jason.manongapp.more.register;

import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.more.login.bean.UserInfo;
import com.jason.manongapp.more.register.bean.RegisterCallBackBean;

/**
 * MVPPlugin
 */

public class RegisterContract {
    interface View extends BaseView {

        void goBackLogin();

        void showToast(String msg);

        String getUserName();

        String getPassWord();

        String getVerifyPassword();

        void onFinishCallBack();
    }


    interface Model {
        void register(String username, String password, Presenter callback, Dialog dialog);

    }

    interface Presenter extends BasePresenter<View> {
        void registerSuccess();

        void registerError(String errorMsg);

    }

}
