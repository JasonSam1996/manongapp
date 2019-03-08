package com.jason.manongapp.more.findpwd;


import android.os.CountDownTimer;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.HttpErrorCode;
import com.jason.manongapp.base.http.HttpErrorInfo;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.base.utils.PatternUtils;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.bean.SMSCodeBean;

/**
 * MVPPlugin
 */

public class FindPwdPresenter extends BasePresenterImpl<FindPwdoginContract.View> implements FindPwdoginContract.Presenter {

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
        FindPwdModel.getInstance().getCode(mView.getPhone(), "重置密码验证码", this, dialog);
    }

    public void reSetPwd(){
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
        if (TextUtils.isEmpty(mView.getPassword())) {
            mView.showToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(mView.getResPassword())) {
            mView.showToast("请输入确认密码");
            return;
        }
        if (!mView.getPassword().equals(mView.getResPassword())) {
            mView.showToast("密码和确认密码不一致，请重新输入！");
            return;
        }
        LoadingDialog dialog = new LoadingDialog(mView.getContext(), R.style.MyDialog);
        FindPwdModel.getInstance().resetPwd(mView.getCode(),mView.getPassword(),this,dialog);
    }

    @Override
    public void getCodeSuccess() {
        mView.showToast("验证码发送成功");
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
    public void reSetPwdSuccess() {
        mView.showToast("重置密码成功");
        mView.goBackLogin();
    }

    @Override
    public void reSetPwdError(String errorMsg) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(errorMsg, HttpErrorInfo.class);
        if (httpErrorInfo.getCode() == HttpErrorCode.CODE_ERROR) {
            mView.showToast("验证码错误，请检查验证码！");
        }
    }
}
