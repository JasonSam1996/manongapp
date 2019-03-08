package com.jason.manongapp.more.findpwd;

import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.more.bean.SMSCodeBean;

/**
 * MVPPlugin
 */

public class FindPwdoginContract {
    interface View extends BaseView {
        String getPhone();

        String getPassword();

        String getResPassword();

        String getCode();

        void goBackLogin();

        void showToast(String msg);


    }


    interface Model {

        void getCode(String phone, String template, Presenter presenter, Dialog dialog);

        void resetPwd(String code,String password,Presenter presenter,Dialog dialog);

    }

    interface Presenter extends BasePresenter<View> {

        void getCodeSuccess();

        void getCodeError(String errorMsg);

        void reSetPwdSuccess();

        void reSetPwdError(String errorMsg);

    }

}
