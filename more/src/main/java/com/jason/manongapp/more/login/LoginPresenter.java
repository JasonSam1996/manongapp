package com.jason.manongapp.more.login;


import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.HttpErrorCode;
import com.jason.manongapp.base.http.HttpErrorInfo;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.login.bean.UserInfo;

import java.util.Map;

/**
 * MVPPlugin
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    public void login(){
        if (TextUtils.isEmpty(mView.getUserName())) {
            mView.showToast("请输入用户名！");
            return;
        }
        if (TextUtils.isEmpty(mView.getPassWord())) {
            mView.showToast("请输入密码!");
            return;
        }
        if (mView.getUserName().equals(mView.getPassWord())) {
            mView.showToast("用户名和密码一致");
            return;
        }
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        LoginModel.getInstance().login(mView.getUserName(),mView.getPassWord(),this,dialog);
    }

    public void qqLogin(Activity activity){
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        LoginModel.getInstance().qqLogin(activity,this,dialog);
    }

    @Override
    public void loginSuccess(UserInfo info) {
        mView.onFinishCallBack(info);
    }

    @Override
    public void loginFailed(String error) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(error, HttpErrorInfo.class);
        if (httpErrorInfo.getCode()==HttpErrorCode.QUERY_OR_LOGINFAIL) {
            mView.showToast("用户名或者密码不正确");
//            callback.loginFailed(errorMsg);
        }
    }

    @Override
    public void qqLoginSuccess(Map<String, String> qqMap, Dialog dialog) {
        mView.onQQFinishCallBack(qqMap,dialog);
    }


    @Override
    public void qqLoginFail(String errorMsg) {
        if (errorMsg.contains("2008")) {
            mView.showToast("错误信息：没有安装应用，请您下载最新的QQ应用！");
        }else {
            mView.showToast(errorMsg);
        }
    }
}
