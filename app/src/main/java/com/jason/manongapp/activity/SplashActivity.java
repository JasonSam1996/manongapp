package com.jason.manongapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.jason.manongapp.R;
import com.jason.manongapp.api.AuthService;
import com.jason.manongapp.base.BaseActivity;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.bean.AuthCallBack;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.activity_splash)
    View layout_splash;

    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData() {
        if (SPUtils.get("is_over",false)) {
            startMain();
        }
    }

    private void startMain() {
        Timer timer = new Timer();
        timer.schedule(new MyTask(),3000);
    }

    @Override
    public void initView() {
        Logger.i("session_token："+SPUtils.get("session_token",""));
        if (!TextUtils.isEmpty(SPUtils.get("session_token","")) && !TextUtils.isEmpty(SPUtils.get("objectid",""))) {
            Map<String,Object> headers = new HashMap<>();
            headers.put("X-Bmob-Application-Id","3e2c91cb95ceefc5b4ad4da9916d1888");
            headers.put("X-Bmob-REST-API-Key","437139b0987ff441647f635727c93fbf");
            headers.put("X-Bmob-Session-Token",SPUtils.get("session_token",""));
            headers.put("Content-Type","application/json");
            RxHttpUtils.getSInstance()
                    .addHeaders(headers)
                    .createSApi(AuthService.class)
                    .isAuthPastDue(SPUtils.get("objectid",""))
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<AuthCallBack>() {
                        @Override
                        protected void onError(String errorMsg) {
                            Logger.i("出错了！"+errorMsg);
                        }

                        @Override
                        protected void onSuccess(AuthCallBack authCallBack) {
                            Logger.i("成功！"+authCallBack);
                            if (authCallBack != null) {
                                SPUtils.put("auth_msg",authCallBack.getMsg());
                            }
                        }
                    });

        }
    }


    class MyTask extends TimerTask{

        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
            }
            SPUtils.put("is_over",true);
            startMain();
        }else {
            Toast.makeText(this, "申请权限失败，请进入设置——应用管理——码农生活打开权限", Toast.LENGTH_LONG).show();
        }

    }

}
