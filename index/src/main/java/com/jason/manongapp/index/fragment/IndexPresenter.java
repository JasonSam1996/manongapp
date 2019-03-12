package com.jason.manongapp.index.fragment;

import android.content.Context;

import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.base.http.utils.NetUtils;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.index.fragment.bean.CityLocationBean;
import com.jason.manongapp.index.fragment.bean.IndexBean;
import com.jason.manongapp.index.fragment.bean.WeatherBean;
import com.jason.manongapp.tally.R;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class IndexPresenter extends BasePresenterImpl<IndexContract.View> implements IndexContract.Presenter {

    public void initTextView() {
        Calendar calendar = Calendar.getInstance();
        mView.setTextYear(mView.getYear(calendar));
        mView.setTextMonth(mView.getMonth(calendar));
        mView.setTextDay(mView.getDay(calendar));
        mView.setTextDayWeek(mView.getDayWeek(calendar));
    }

    public void getImage(){

        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);
        IndexModel.getInstance().getImageUrl(this,dialog);
//        mView.getLocation(mView.getContext());
    }

    public void getCity(){
//        Logger.i("isNet:"+NetUtils.isNet(mView.getContext()));
        if (!NetUtils.isNet()) {
            mView.showToast("网络错误，请检查网络");
            mView.setCityText("定位失败");
            return;
        }
        if (mView.getLocation(mView.getContext())!=null) {
            IndexModel.getInstance().getCity("json",mView.getLocation(mView.getContext()),"esNPFDwwsXWtsQfw4NMNmur1",this);
        }else {
            mView.setCityText("定位失败");

        }
    }

    public void getWeather(){
        Logger.i("city："+mView.getCity());
        String key = "HE1902281830091642";
        IndexModel.getInstance().getWeather(mView.getCity(),key,this);
    }

    @Override
    public void getImageUrlSuccess(IndexBean indexBean) {
        final String spDate = mView.getSPDate();
        Observable.fromIterable(indexBean.getResults())
                .subscribe(new CommonObserver<IndexBean.ResultsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        mView.showToast(errorMsg);
                    }

                    @Override
                    protected void onSuccess(IndexBean.ResultsBean resultsBean) {
                        String iso = resultsBean.getDate_string().getIso();
                        iso = iso.substring(0,iso.indexOf(" "));
//                        Logger.i("iso:"+iso);
                        if (spDate.equals(iso)) {
                            mView.setSimpleDraweeViewUri(resultsBean.getImageUrl());
                            mView.setQuotesText(resultsBean.getQuotes());
                        }
                    }
                });
    }

    @Override
    public void getImageUrlError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    @Override
    public void getWeatherSuccess(WeatherBean heWeather6Bean) {
//        mView.setWeatherText(heWeather6Bean.getNow().getTmp() + "℃");
        List<WeatherBean.HeWeather6Bean> heWeather6 = heWeather6Bean.getHeWeather6();
        if (heWeather6.size()!=0) {
            Observable.fromIterable(heWeather6)
                    .subscribe(new CommonObserver<WeatherBean.HeWeather6Bean>() {
                        @Override
                        protected void onError(String errorMsg) {
                            mView.showToast(errorMsg);
                        }

                        @Override
                        protected void onSuccess(WeatherBean.HeWeather6Bean heWeather6Bean) {
                            if (heWeather6Bean != null) {
                                Logger.i("heWeather6Bean："+heWeather6Bean.toString());
                                mView.setWeatherText(heWeather6Bean.getNow().getTmp()+"℃");
                                mView.setWeatherImage(heWeather6Bean.getNow().getCond_code());
                            }
                        }
                    });
        }
    }

    @Override
    public void getWeatherError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    @Override
    public void getCitySuccess(CityLocationBean cityLocationBean) {
        if (cityLocationBean != null) {
            mView.setCityText(cityLocationBean.getResult().getAddressComponent().getDistrict());
            getWeather();
        }
    }

    @Override
    public void getCityError(String errorMsg) {
        mView.showToast(errorMsg);
    }
}

