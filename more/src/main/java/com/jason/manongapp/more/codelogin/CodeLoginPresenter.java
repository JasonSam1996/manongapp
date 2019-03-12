package com.jason.manongapp.more.codelogin;


import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.HttpErrorCode;
import com.jason.manongapp.base.http.HttpErrorInfo;
import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.base.utils.PatternUtils;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;

/**
 * MVPPlugin
 */

public class CodeLoginPresenter extends BasePresenterImpl<CodeLoginContract.View> implements CodeLoginContract.Presenter {

    public void getCode(CountDownTimer countDownTimer) {
        if (TextUtils.isEmpty(mView.getPhone())) {
            mView.showToast("请输入手机号");
            return;
        }
        if (!PatternUtils.phonePattern(mView.getPhone())) {
            mView.showToast("手机号格式有误，请重新输入！");
            return;
        }
        PatternUtils.phonePattern(mView.getPhone());
        countDownTimer.start();
        LoadingDialog dialog = new LoadingDialog(mView.getContext(), R.style.MyDialog);
        CodeLoginModel.getInstance().getCode(mView.getPhone(), "登录验证码", this, dialog);
    }

    public void codeLogin() {
        if (TextUtils.isEmpty(mView.getPhone())) {
            mView.showToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(mView.getCode())) {
            mView.showToast("请输入验证码");
            return;
        }
        if (!PatternUtils.phonePattern(mView.getPhone())) {
            mView.showToast("手机号格式有误，请重新输入！");
            return;
        }
        LoadingDialog dialog = new LoadingDialog(mView.getContext(), R.style.MyDialog);
        CodeLoginModel.getInstance().codeLogin(mView.getPhone(), mView.getCode(), this, dialog);
    }

    @Override
    public void getCodeSuccess(SMSCodeBean smsCodeBean) {
        if (smsCodeBean != null) {
            mView.showToast("验证码发送成功");
//            Logger.i("验证码发送成功");
        }
    }

    @Override
    public void getCodeError(String errorMsg) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(errorMsg, HttpErrorInfo.class);
        if (httpErrorInfo.getCode() == HttpErrorCode.PHONE_REQUEST_ERROR) {
            mView.showToast("您的手机号码请求验证码超过次数，请稍后再试！");
        }
    }

    @Override
    public void codeLoginSuccess(SMSCodeCallBackBean smsCodeCallBackBean) {
        if (smsCodeCallBackBean != null) {
            mView.onFinishCallBack(smsCodeCallBackBean);
        }
    }

    @Override
    public void codeLoginError(String errorMsg) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(errorMsg, HttpErrorInfo.class);
        if (httpErrorInfo.getCode() == HttpErrorCode.CODE_ERROR) {
            mView.showToast("验证码错误，请检查验证码！");
        }
    }
}
