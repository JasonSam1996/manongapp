package com.jason.manongapp.more.codelogin;

import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;

/**
 * MVPPlugin
 */

public class CodeLoginContract {
    interface View extends BaseView {

        void goBackLogin();

        void showToast(String msg);

        String getPhone();


        String getCode();

        void onFinishCallBack(SMSCodeCallBackBean smsCodeCallBackBean);
    }


    interface Model {
        void codeLogin(String phone, String smsCode, Presenter callback, Dialog dialog);
        void getCode(String phone,String template,Presenter presenter,Dialog dialog);
    }

    interface Presenter extends BasePresenter<View> {

        void getCodeSuccess(SMSCodeBean smsCodeBean);

        void getCodeError(String errorMsg);

        void codeLoginSuccess(SMSCodeCallBackBean smsCodeCallBackBean);

        void codeLoginError(String errorMsg);

    }

}
