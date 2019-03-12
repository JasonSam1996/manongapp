package com.jason.manongapp.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.base.utils.DataCleanManager;
import com.jason.manongapp.base.utils.FinishUtils;
import com.jason.manongapp.base.utils.VersionUtils;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;
import com.jason.manongapp.more.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View {

    @BindView(R2.id.more_get_cache)
    TextView tvCache;

    @BindView(R2.id.more_clean_cache_loading)
    ProgressBar progressBar;

    @BindView(R2.id.more_now_versionnames)
    TextView tvVersionName;

    @BindView(R2.id.more_setting_password)
    LinearLayout llSetPwdLayout;

    @BindView(R2.id.more_old_password)
    EditText etOldPwd;

    @BindView(R2.id.more_new_password)
    EditText etNewPwd;

    @BindView(R2.id.more_confirm_password)
    EditText etConfimPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.initView();
    }

    @Override
    public int initLayout() {
        return R.layout.more_personal_center_activity;
    }

    @Override
    public void setCacheText(String cacheText) {
        tvCache.setText(cacheText);
    }

    @Override
    public String getCacheText() {
        try {
            return DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean cleanCache() {
        return DataCleanManager.clearAllCache(this);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvCache.setVisibility(View.INVISIBLE);
    }

    @Override
    public void dismisLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        tvCache.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getVersionName() {
        return VersionUtils.getLocalVersionName(this);
    }

    @Override
    public void setVersionNameText(String versionNameText) {
        tvVersionName.setText(versionNameText);
    }

    @Override
    public String getOldPwd() {
        return etOldPwd.getText().toString().trim();
    }

    @Override
    public String getNewPwd() {
        return etNewPwd.getText().toString().trim();
    }

    @Override
    public String getConfirmPwd() {
        return etConfimPwd.getText().toString().trim();
    }

    @Override
    public void reSetOpenLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("methods","reset");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        finish();
    }

    @Override
    public String getObjectId() {
        return SPUtils.get("objectid","");
    }

    @OnClick(R2.id.more_clean_cache)
    public void cleanCache(View view){
        mPresenter.cleanCache();
    }

    @OnClick(R2.id.more_setting_back)
    public void onBack(View view){
        FinishUtils.finishUtils(this);
    }

    @OnClick(R2.id.more_user_security)
    public void openReSetPwd(View view){
        if (llSetPwdLayout.getVisibility() == View.GONE) {
            llSetPwdLayout.setVisibility(View.VISIBLE);
        }else {
            llSetPwdLayout.setVisibility(View.GONE);
            etOldPwd.setText("");
            etNewPwd.setText("");
            etConfimPwd.setText("");
        }
    }


    @OnClick(R2.id.more_reset_password)
    public void reSetPwd(View view){
        mPresenter.reSetPwd();
    }

    @OnClick(R2.id.more_logout)
    public void logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("methods","logout");
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        SPUtils.clearAll();
        finish();
    }

}
