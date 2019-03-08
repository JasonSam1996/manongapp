package com.jason.manongapp.more.codelogin;


import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class CodeLoginActivity extends MVPBaseActivity<CodeLoginContract.View, CodeLoginPresenter> implements CodeLoginContract.View {


    @BindView(R2.id.more_codelogin_activity_etusername)
    EditText codeLoginPhone;

    @BindView(R2.id.more_codelogin_activity_etcode)
    EditText codeLoginCode;

    @BindView(R2.id.more_codelogin_activity_btgetcode)
    Button btGetCode;

//    @OnClick(R2.id.more_register_activity_btgetcode)
//    public void getCode(View view){
//        mPresenter.getCode();
//    }

    @Override
    public int initLayout() {
        return R.layout.more_codelogin_activity;
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
    public String getPhone() {
        return codeLoginPhone.getText().toString().trim();
    }


    @Override
    public String getCode() {
        return codeLoginCode.getText().toString().trim();
    }

    @Override
    public void onFinishCallBack(SMSCodeCallBackBean smsCodeCallBackBean) {
        Intent intent = new Intent();
        intent.putExtra("user",smsCodeCallBackBean);
        intent.putExtra("isLogin",true);
        setResult(CodeLoginActivity.RESULT_OK,intent);
        finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }

    @OnClick(R2.id.more_codelogin_activity_close)
    public void goBackLogin(View view){
        goBackLogin();
    }

    @OnClick(R2.id.more_codelogin_activity_btgetcode)
    public void onGetCode(View view){
        CountDownTime countDownTime = new CountDownTime(60000,1);
        mPresenter.getCode(countDownTime);
    }

    @OnClick(R2.id.more_codelogin_activity_btlogin)
    public void onCodeLOgin(View view){
        mPresenter.codeLogin();
    }

    class CountDownTime extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
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
            btGetCode.setText(R.string.more_codelogin_btgetcode_text);

        }
    }

}
