package com.jason.manongapp.more.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;
import com.jason.manongapp.more.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class RegisterActivity extends MVPBaseActivity<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    @BindView(R2.id.more_register_activity_etusername)
    EditText etRegisterUsername;

    @BindView(R2.id.more_register_activity_etpassword)
    EditText etRegisterPassword;

    @BindView(R2.id.more_register_activity_etverifypassword)
    EditText etRegisterVerifyPassword;

    @BindView(R2.id.more_register_activity_eteyespwdigb)
    ImageButton imgHideOrShowPwd;

    @BindView(R2.id.more_register_activity_eteyesverifypwdigb)
    ImageButton imgHideOrShowVerifyPwd;

    private boolean isShowPassword = true;
    private boolean isShowVerifyPassword = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(false);
        super.onCreate(savedInstanceState);
    }


    @Override
    public int initLayout() {
        return R.layout.more_register_activity;
    }

    @Override
    public void goBackLogin() {
        finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        return etRegisterUsername.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return etRegisterPassword.getText().toString().trim();
    }

    @Override
    public String getVerifyPassword() {
        return etRegisterVerifyPassword.getText().toString().trim();
    }

    @Override
    public void onFinishCallBack() {
        Intent intent = new Intent();
        intent.putExtra("rgusername",getUserName());
        intent.putExtra("rgpassword",getPassWord());
        setResult(LoginActivity.RESULT_OK,intent);
        finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    @OnClick(R2.id.more_register_activity_close)
    public void backLogin(View view){
        goBackLogin();
    }

    @OnClick(R2.id.more_register_activity_eteyespwdigb)
    public void hideOrShowPwd(View view){
        if (isShowPassword) {
            imgHideOrShowPwd.setImageDrawable(getResources().getDrawable(R.drawable.eyesshow));
            etRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etRegisterPassword.setSelection(etRegisterPassword.getText().toString().length());
            isShowPassword = !isShowPassword;
        }else {
            imgHideOrShowPwd.setImageDrawable(getResources().getDrawable(R.drawable.eyeshide));
            etRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etRegisterPassword.setSelection(etRegisterPassword.getText().toString().length());
            isShowPassword = !isShowPassword;
        }
    }

    @OnClick(R2.id.more_register_activity_eteyesverifypwdigb)
    public void hideOrShowVerifyPwd(View view){
        if (isShowVerifyPassword) {
            imgHideOrShowVerifyPwd.setImageDrawable(getResources().getDrawable(R.drawable.eyesshow));
            etRegisterVerifyPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etRegisterVerifyPassword.setSelection(etRegisterVerifyPassword.getText().toString().length());
            isShowVerifyPassword = !isShowVerifyPassword;
        }else {
            imgHideOrShowVerifyPwd.setImageDrawable(getResources().getDrawable(R.drawable.eyeshide));
            etRegisterVerifyPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etRegisterVerifyPassword.setSelection(etRegisterVerifyPassword.getText().toString().length());
            isShowVerifyPassword = !isShowVerifyPassword;
        }
    }

    @OnClick(R2.id.more_register_activity_btregister)
    public void register(View view){
        mPresenter.register();
    }

    /*@Override
    public CountDownTimer getCountDownTimer() {
        return new CountDownTime(6000, 1000);
    }

    @Override
    public void startCountDownTime(CountDownTimer countDownTimer) {
        countDownTimer.start();
    }

    @OnClick(R2.id.more_register_activity_btgetcode)
    public void getCode(View view){
        mPresenter.getCode();
    }

    class CountDownTime extends CountDownTimer {

        *//**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         *//*
        public CountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btGetCode.setClickable(false);
            btGetCode.setBackgroundResource(R.drawable.more_bt_false);
            btGetCode.setText(millisUntilFinished / 1000+"s后重新获取");
        }

        @Override
        public void onFinish() {
            btGetCode.setClickable(true);
            btGetCode.setBackgroundResource(R.drawable.more_bt);
            btGetCode.setText(R.string.more_register_btgetcode_text);
        }
    }*/

}
