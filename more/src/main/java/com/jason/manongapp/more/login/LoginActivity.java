package com.jason.manongapp.more.login;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;
import com.jason.manongapp.more.codelogin.CodeLoginActivity;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;
import com.jason.manongapp.more.findpwd.FindPwdActivity;
import com.jason.manongapp.more.login.bean.SerializableMap;
import com.jason.manongapp.more.login.bean.UserInfo;
import com.jason.manongapp.more.register.RegisterActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {


    @BindView(R2.id.more_login_activity_etusername)
    EditText etUsername;

    @BindView(R2.id.more_login_activity_etpassword)
    EditText etPassword;

    @BindView(R2.id.more_login_activity_eteyespwdigb)
    ImageButton imgbtShowPassword;

    private boolean isShowPassword = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(false);
        super.onCreate(savedInstanceState);

    }

    @OnClick(R2.id.more_login_activity_close)
    public void loginClose(View view){
        if (getIntent().getStringExtra("methods") == null) {
            finish();
            overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
        }else {
            if (getIntent().getStringExtra("methods").equals("reset")) {
                return;
            }
            if (getIntent().getStringExtra("methods").equals("logout")) {
                return;
//            Intent intent = new Intent(this,MainActivity.class)
            }
        }


    }

    @OnClick(R2.id.more_login_activity_btcodelogin)
    public void openCodeLogin(View view){
        Intent intent = new Intent(this,CodeLoginActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, 300);
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
//        finish();
    }

    @OnClick(R2.id.more_login_activity_weibo)
    public void weibo(View view){
        showToast("此功能暂时未开通");
    }

    @OnClick(R2.id.more_login_activity_weixin)
    public void weixin(View view){
        showToast("此功能暂时未开通");
    }

    @OnClick(R2.id.more_login_activity_qq)
    public void qq(View view){
        mPresenter.qqLogin(this);
//        showToast("稍后开通");
    }

    @OnClick(R2.id.more_login_activity_btlogin)
    public void login(View view){
        mPresenter.login();
    }

    @OnClick(R2.id.more_login_activity_tvregister)
    public void openRegister(View view){
        Intent intent = new Intent(getContext(),RegisterActivity.class);
        startActivityForResult(intent, 200);
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    @OnClick(R2.id.more_login_activity_findpwd)
    public void openFindPwd(View view){
        Intent intent = new Intent(this,FindPwdActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    @OnClick(R2.id.more_login_activity_eteyespwdigb)
    public void showOrHidePwd(View view){
        if (isShowPassword) {
            imgbtShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.eyesshow));
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setSelection(etPassword.getText().toString().length());
            isShowPassword = !isShowPassword;
        }else {
            imgbtShowPassword.setImageDrawable(getResources().getDrawable(R.drawable.eyeshide));
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setSelection(etPassword.getText().toString().length());
            isShowPassword = !isShowPassword;
        }
    }

    @Override
    public int initLayout() {
        return R.layout.more_login_activity;
    }



    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void onFinishCallBack(UserInfo info) {
//        Logger.i("是这里回调的吗?");
        SPUtils.put("session_token",info.getSessionToken());
        SPUtils.put("objectid",info.getObjectId());
        SPUtils.put("username",info.getUsername());
        SPUtils.put("isLogin",true);
        EventBus.getDefault().post(info);
        finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onQQFinishCallBack(Map<String, String> qqMap, Dialog dialog) {
        SPUtils.put("session_token",qqMap.get("sessionToken"));
        SPUtils.put("objectid",qqMap.get("objectId"));
        SPUtils.put("isLogin",true);
        dialog.dismiss();
        showToast("登录成功");
        EventBus.getDefault().post(qqMap);
        finish();
//        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK && requestCode == 200) {
            String rguser = data.getStringExtra("rgusername");
            String rgpwd = data.getStringExtra("rgpassword");
            etUsername.setText(rguser);
            etPassword.setText(rgpwd);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 300) {
            SMSCodeCallBackBean smsCodeCallBackBean = (SMSCodeCallBackBean) data.getSerializableExtra("user");
            EventBus.getDefault().post(smsCodeCallBackBean);
            finish();
            overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

}
