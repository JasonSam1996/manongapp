package com.jason.manongapp.diary.news;


import android.app.Dialog;
import android.text.TextUtils;

import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.diary.bean.UpLoadingCallBack;
import com.jason.manongapp.diary.news.api.UpLoadingService;
import com.orhanobut.logger.Logger;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddDirayModel implements AddDiaryContract.Model {

    private AddDirayModel() {
    }

    @Override
    public void uploading(String path, final AddDiaryContract.Presenter presenter, Dialog dialog) {
        String contentType = "";
        String fileName = "";

        if (path.endsWith(".jpg")) {
            contentType = "application/x-jpg";
            fileName = System.currentTimeMillis() + ".jpg";
        }else if (path.endsWith(".png")) {
            contentType = "application/x-png";
            fileName = System.currentTimeMillis() + ".png";
        }

        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RxHttpUtils.createApi(UpLoadingService.class)
                .upLoadingImage(contentType, fileName, imageBody)
                .compose(Transformer.<UpLoadingCallBack>switchSchedulers(dialog))
                .subscribe(new CommonObserver<UpLoadingCallBack>() {
                    @Override
                    protected void onError(String errorMsg) {
                        if (!TextUtils.isEmpty(errorMsg) || errorMsg != null) {
                            presenter.upLoadingError(errorMsg);
                        }
//                        Logger.i("errorMsg：" + errorMsg);
                    }

                    @Override
                    protected void onSuccess(UpLoadingCallBack upLoadingCallBack) {
                        if (upLoadingCallBack != null) {
                            presenter.upLoadingSuccess(upLoadingCallBack);
                        }
//                        Logger.i("onSuccess：" + upLoadingCallBack.toString());
                    }
                });
    }

    private static class SingletonLoader {
        private static final AddDirayModel INSTANCE = new AddDirayModel();
    }

    public static AddDirayModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


}

