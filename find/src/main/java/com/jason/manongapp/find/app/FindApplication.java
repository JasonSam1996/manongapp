package com.jason.manongapp.find.app;

import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jason.manongapp.base.BaseApplication;
import com.jason.manongapp.base.IComponentApplication;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.config.OkHttpConfig;
import com.jason.manongapp.base.http.cookie.store.SPCookieStore;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;

import okhttp3.OkHttpClient;

public class FindApplication implements IComponentApplication {
    @Override
    public void onCreate(BaseApplication application) {
        initTbs(application);
    }

    private void initTbs(BaseApplication application) {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.i("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(application,  cb);
    }



}
