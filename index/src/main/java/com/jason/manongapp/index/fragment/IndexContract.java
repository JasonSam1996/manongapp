package com.jason.manongapp.index.fragment;

import android.app.Dialog;
import android.content.Context;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.index.fragment.bean.CityLocationBean;
import com.jason.manongapp.index.fragment.bean.IndexBean;
import com.jason.manongapp.index.fragment.bean.WeatherBean;

import java.util.Calendar;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class IndexContract {
    interface View extends BaseView {
        String getYear(Calendar calendar);

        String getMonth(Calendar calendar);

        String getDay(Calendar calendar);

        String getDayWeek(Calendar calendar);

        String getSPDate();

        String getCity();

        String getLocation(Context context);

        void showToast(String msg);

        void setTextYear(String year);

        void setTextMonth(String month);

        void setTextDay(String day);

        void setTextDayWeek(String dayWeek);

        void setSimpleDraweeViewUri(String url);

        void setQuotesText(String msg);

        void setWeatherText(String msg);

        void setWeatherImage(String code);

        void setCityText(String citu);
    }

    interface Model{
        void getImageUrl(Presenter presenter, Dialog dialog);

        void getWeather(String city,String key,Presenter presenter);

        void getCity(String outPut,String location,String ak,Presenter presenter);
    }

    interface  Presenter extends BasePresenter<View> {
        void getImageUrlSuccess(IndexBean indexBean);

        void getImageUrlError(String errorMsg);

        void getWeatherSuccess(WeatherBean heWeather6Bean);

        void getWeatherError(String errorMsg);

        void getCitySuccess(CityLocationBean cityLocationBean);

        void getCityError(String errorMsg);
    }
}
