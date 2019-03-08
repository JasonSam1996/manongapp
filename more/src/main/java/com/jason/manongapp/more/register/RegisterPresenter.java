package com.jason.manongapp.more.register;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.HttpErrorCode;
import com.jason.manongapp.base.http.HttpErrorInfo;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.register.bean.RegisterCallBackBean;

/**
 * MVPPlugin
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{


    public void register(){
        if (TextUtils.isEmpty(mView.getUserName())) {
            mView.showToast("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(mView.getPassWord())) {
            mView.showToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(mView.getVerifyPassword())) {
            mView.showToast("请输入确认密码");
            return;
        }
        if (!mView.getPassWord().equals(mView.getVerifyPassword())) {
            mView.showToast("密码和确认密码不一致");
            return;
        }
        if (mView.getUserName().equals(mView.getPassWord())) {
            mView.showToast("用户名和密码一致，请重新填写");
            return;
        }
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        RegisterModel.getInstance().register(mView.getUserName(),mView.getPassWord(),this,dialog);
    }

    @Override
    public void registerSuccess() {
        mView.showToast("注册成功");
        mView.onFinishCallBack();
    }

    @Override
    public void registerError(String errorMsg) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(errorMsg, HttpErrorInfo.class);
        if (httpErrorInfo.getCode()==HttpErrorCode.USERALREADYEXISTS) {
            mView.showToast("用户名已经存在");
//            callback.loginFailed(errorMsg);
        }
    }
}
