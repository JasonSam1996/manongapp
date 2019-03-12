package com.jason.manongapp.diary.news;


import android.app.Dialog;
import android.content.Context;
import android.text.SpannableString;
import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.diary.bean.AddDiaryBean;
import com.jason.manongapp.diary.bean.CityLocationBean;
import com.jason.manongapp.diary.bean.SaveCallBack;
import com.jason.manongapp.diary.bean.UpLoadingCallBack;

import java.util.Calendar;

/**
 * MVPPlugin
 */

public class AddDiaryContract {
    interface View extends BaseView {

        void initRecyclerView();

        void initKeyboard();

        void showToast(String msg);

        void insertPhotoToEditText(SpannableString ss);

        void etContentAppendEnter();

        void setCityText(String cityText);

        void setDayWeek(String dayWeek);

        void setDay(String day);

        void setYearAndMonth(String yearAndMonth);

        void finishBack();

        String getContent();

        String getLocation(Context context);

        String getMood();

        String getWeather();

        String getYear(Calendar calendar);


        String getDay(Calendar calendar);

        String getDayWeek(Calendar calendar);

        String getCity();

        String getBiaryTitle();

        String getObjectId();

    }


    interface Model {
        void uploading(String path, Presenter presenter, Dialog dialog);

        void getCity(String outPut,String location,String ak,Presenter presenter);

        void save(AddDiaryBean addDiaryBean,Presenter presenter,Dialog dialog);
    }

    interface Presenter extends BasePresenter<View> {
        void upLoadingSuccess(UpLoadingCallBack upLoadingCallBack);
        void upLoadingError(String errorMsg);

        void getCitySuccess(CityLocationBean cityLocationBean);

        void getCityError(String errorMsg);

        void saveSuccess(SaveCallBack saveCallBack);

        void saveError(String errorMsg);

    }

}
