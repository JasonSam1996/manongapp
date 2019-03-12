package com.jason.manongapp.setting;


import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.HttpErrorCode;
import com.jason.manongapp.base.http.HttpErrorInfo;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.more.R;
import com.jason.manongapp.setting.bean.SettingCallBackBean;

import java.util.Timer;
import java.util.TimerTask;

/**
 * MVPPlugin
 */

public class SettingPresenter extends BasePresenterImpl<SettingContract.View> implements SettingContract.Presenter{

    public void initView(){
        if (mView.getCacheText() != null || !TextUtils.isEmpty(mView.getCacheText())) {
            mView.setCacheText(mView.getCacheText());
        }
        if (mView.getVersionName() != null || !TextUtils.isEmpty(mView.getVersionName())) {
            mView.setVersionNameText(mView.getVersionName());
        }
    }

    public void reSetPwd(){
        if (TextUtils.isEmpty(mView.getOldPwd())) {
            mView.showToast("请输入原密码");
            return;
        }
        if (TextUtils.isEmpty(mView.getNewPwd())) {
            mView.showToast("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(mView.getConfirmPwd())) {
            mView.showToast("请输入确认密码");
            return;
        }
        if (!mView.getNewPwd().equals(mView.getConfirmPwd())) {
            mView.showToast("新密码和确认密码不一致");
            return;
        }
        if (mView.getOldPwd().equals(mView.getNewPwd())) {
            mView.showToast("旧密码和新密码不能一致");
            return;
        }
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        SettingModel.getInstance().reSetPwd(mView.getObjectId(),mView.getOldPwd(),mView.getNewPwd(),this,dialog);
    }

    public void cleanCache(){
        mView.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mView.cleanCache()) {
                    mView.dismisLoading();
                    mView.setCacheText(mView.getCacheText());
                    mView.showToast("清理完成");
                }
            }
        },1000);

    }


    @Override
    public void reSetPwdSuccess(SettingCallBackBean settingCallBackBean) {
        if (settingCallBackBean.getMsg().equals("ok")) {
            mView.showToast("修改成功，请重新登录");
            mView.reSetOpenLogin();
        }
    }

    @Override
    public void reSetPwdError(String errorMsg) {
        Gson gson = new Gson();
        HttpErrorInfo httpErrorInfo = gson.fromJson(errorMsg, HttpErrorInfo.class);
        if (httpErrorInfo.getCode()==HttpErrorCode.USER_SETTING_NEWPWD) {
            mView.showToast("旧密码不正确");
//            callback.loginFailed(errorMsg);
        }
    }
}
