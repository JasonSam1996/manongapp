package com.jason.manongapp.more.findpwd;


import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class FindPwdActivity extends MVPBaseActivity<FindPwdoginContract.View, FindPwdPresenter> implements FindPwdoginContract.View {

    @BindView(R2.id.more_findpwd_activity_etusername)
    EditText edFindPwdUser;

    @BindView(R2.id.more_findpwd_activity_etcode)
    EditText etFindPwdCode;

    @BindView(R2.id.more_findpwd_activity_etpwd)
    EditText edFindPwdPwd;

    @BindView(R2.id.more_findpwd_activity_etrespwd)
    EditText etFindPwdResPwd;

    @BindView(R2.id.more_findpwd_activity_btgetcode)
    Button btGetCode;

    @Override
    public int initLayout() {
        return R.layout.more_findpwd_activity;
    }

    @Override
    public String getPhone() {
        return edFindPwdUser.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return edFindPwdPwd.getText().toString().trim();
    }

    @Override
    public String getResPassword() {
        return etFindPwdResPwd.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return etFindPwdCode.getText().toString().trim();
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


    @OnClick(R2.id.more_findpwd_activity_close)
    public void goBack(View view){
        goBackLogin();
    }

    @OnClick(R2.id.more_findpwd_activity_btgetcode)
    public void getCode(View view){
        CountDownTime countDownTime = new CountDownTime(60000,1);
        mPresenter.getCode(countDownTime);
    }

    @OnClick(R2.id.more_findpwd_activity_btreset)
    public void reSetPwd(View view) {
        mPresenter.reSetPwd();
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
