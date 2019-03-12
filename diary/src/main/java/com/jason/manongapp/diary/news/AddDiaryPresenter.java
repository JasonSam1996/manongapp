package com.jason.manongapp.diary.news;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.bean.AddDiaryBean;
import com.jason.manongapp.diary.bean.CityLocationBean;
import com.jason.manongapp.diary.bean.SaveCallBack;
import com.jason.manongapp.diary.bean.UpLoadingCallBack;
import com.jason.manongapp.diary.utils.ImageUtils;
import com.jason.manongapp.diary.utils.ScreenUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MVPPlugin
 */

public class AddDiaryPresenter extends BasePresenterImpl<AddDiaryContract.View> implements AddDiaryContract.Presenter {

    public void saveDiary(){
        Calendar calendar = Calendar.getInstance();
        String city = mView.getCity();
        String content = mView.getContent();
        String title = mView.getBiaryTitle();
        String mood = mView.getMood();
        String user_type = "Pointer";
        String user_className = "_User";
        String user_objectid = mView.getObjectId();
        String weather = mView.getWeather();
        String week = mView.getDayWeek(calendar);
        AddDiaryBean.UserBean userBean = new AddDiaryBean.UserBean(user_type,user_className,user_objectid);
        AddDiaryBean addDiaryBean = new AddDiaryBean(city,content,title,mood,userBean,weather,week);
        mView.showToast("保存中，请稍后······");
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        AddDirayModel.getInstance().save(addDiaryBean,this,dialog);
    }

    public void getCity(){
        if (mView.getLocation(mView.getContext())!=null) {
            AddDirayModel.getInstance().getCity("json",mView.getLocation(mView.getContext()),"esNPFDwwsXWtsQfw4NMNmur1",this);
        }else {
            mView.setCityText("定位失败");

        }
    }

    public void initView(){
        Calendar calendar = Calendar.getInstance();
        mView.initRecyclerView();
        mView.initKeyboard();
        mView.setDay(mView.getDay(calendar));
        mView.setDayWeek(mView.getDayWeek(calendar));
        mView.setYearAndMonth(mView.getYear(calendar));
    }

    public void selectPhotoCallback(Activity activity, @Nullable Intent data, ContentResolver resolver) {
        Bitmap bm;
        try {
            Uri originalUri = data.getData();
            Logger.i("originalUri："+originalUri.toString());

            bm = MediaStore.Images.Media.getBitmap(resolver,originalUri);
            Logger.i("bm："+bm.toString());
            String[] proj = {MediaStore.Images.Media.DATA};
            Logger.i("proj："+ Arrays.toString(proj));

            Cursor cursor = activity.managedQuery(originalUri,proj,null,null,null);
            Logger.i("cursor："+cursor);

            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            Logger.i("column_index："+column_index);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            Logger.i("path："+path);

            // 先上传图片，后拿到图片地址
            requestPath(path);

//            insertImg(activity,path);
//            insertImg(path);
        } catch (IOException e) {
            e.printStackTrace();
            mView.showToast("图片插入失败");
        }
    }

    private void requestPath(String path) {
        mView.showToast("图片上传中，请稍后······");
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        AddDirayModel.getInstance().uploading(path,this,dialog);
    }

    private void insertImg(Context context,String path){
        String tagPath = "<img src=\""+path+"\"/>";//为图片路径加上<img>标签
        Logger.i("tagPath："+tagPath);

//        Bitmap bitmap = BitmapFactory.decodeFile(path);
        rxJavaInsertImg(context,path,tagPath);
//        Bitmap bitmapUrl = getBitmapUrl(path);
//        Logger.i("bitmap："+tagPath);

    }

    private void rxJavaInsertImg(final Context context, final String path, final String tagPath) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                emitter.onNext(getBitmapUrl(path,null));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                if(bitmap != null){
                    setBitmapMime(context, path, tagPath);
                }else {
                    mView.showToast("插入失败，无读写存储权限，请到权限中心开启");
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Bitmap getBitmapUrl(String path,BitmapFactory.Options options) {
        try {
            URL aryURI = new URL(path);
            URLConnection conn = aryURI.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            Bitmap bm;
            if (options == null) {
                bm = BitmapFactory.decodeStream(is);
            }else {
                bm = BitmapFactory.decodeStream(is,null,options);
            }
            is.close();
            return bm;
        } catch (IOException e) {
            e.printStackTrace();
            mView.showToast("插入图片失败");
        }
        return null;
    }



    private void setBitmapMime(Context context,String path, String tagPath){
        SpannableString ss = new SpannableString(tagPath);//这里使用加了<img>标签的图片路径


        BitmapFactory.Options options = new BitmapFactory.Options();

        rxJavaGetBitmapMime(context,ss,path,tagPath,options);

//        Bitmap bitmap = BitmapFactory.decodeFile(path,options);

    }

    private void rxJavaGetBitmapMime(final Context context, final SpannableString ss, final String path, final String tagPath, final BitmapFactory.Options options) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                emitter.onNext(getBitmapUrl(path,options));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                int width = ScreenUtils.getScreenWidth(context);
                bitmap = ImageUtils.zoomImage(bitmap,(width-32)*0.8,bitmap.getHeight()/(bitmap.getWidth()/((width-32)*0.8)));
                ImageSpan imageSpan = new ImageSpan(context, bitmap);
                ss.setSpan(imageSpan, 0, tagPath.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                mView.insertPhotoToEditText(ss);
                mView.etContentAppendEnter();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void upLoadingSuccess(UpLoadingCallBack upLoadingCallBack) {
        Logger.i("???："+upLoadingCallBack.toString());
        if (upLoadingCallBack.getUrl() != null && !TextUtils.isEmpty(upLoadingCallBack.getUrl())) {
            insertImg(mView.getContext(),upLoadingCallBack.getUrl());
        }else {
            mView.showToast("插入图片失败");
        }
    }

    @Override
    public void upLoadingError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    @Override
    public void getCitySuccess(CityLocationBean cityLocationBean) {
        if (cityLocationBean != null) {
            mView.setCityText(cityLocationBean.getResult().getAddressComponent().getDistrict());
//            getWeather();
        }
    }

    @Override
    public void getCityError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    @Override
    public void saveSuccess(SaveCallBack saveCallBack) {
        if (!TextUtils.isEmpty(saveCallBack.getObjectId())) {
            mView.showToast("保存成功！");
            mView.finishBack();
        }
    }

    @Override
    public void saveError(String errorMsg) {
        mView.showToast(errorMsg);
    }


}
