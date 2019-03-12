package com.jason.manongapp.index.fragment;


import android.app.Dialog;

import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.index.fragment.api.IndexService;
import com.jason.manongapp.index.fragment.bean.CityLocationBean;
import com.jason.manongapp.index.fragment.bean.IndexBean;
import com.jason.manongapp.index.fragment.bean.WeatherBean;
import com.orhanobut.logger.Logger;


public class IndexModel implements IndexContract.Model {

    private IndexModel() {

    }

    @Override
    public void getImageUrl(final IndexContract.Presenter presenter, Dialog dialog) {
        RxHttpUtils.createApi(IndexService.class)
                .getAllImage()
                .compose(Transformer.<IndexBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<IndexBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.i("error："+errorMsg);
                        presenter.getImageUrlError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(IndexBean indexBean) {
                        presenter.getImageUrlSuccess(indexBean);
                    }
                });
    }

    @Override
    public void getWeather(String city, String key, final IndexContract.Presenter presenter) {
        RxHttpUtils.createApi(IndexService.class)
                .getWeather(city,key)
                .compose(Transformer.<WeatherBean>switchSchedulers())
                .subscribe(new CommonObserver<WeatherBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.i("error："+errorMsg);
                        presenter.getWeatherError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(WeatherBean heWeather6Bean) {

                        presenter.getWeatherSuccess(heWeather6Bean);
                    }
                });
    }

    @Override
    public void getCity(String outPut, String location, String ak, final IndexContract.Presenter presenter) {
        RxHttpUtils.createApi(IndexService.class)
                .getCity(outPut, location, ak)
                .compose(Transformer.<CityLocationBean>switchSchedulers())
                .subscribe(new CommonObserver<CityLocationBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.i("error："+errorMsg);
                        presenter.getCityError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(CityLocationBean cityLocationBean) {
                        presenter.getCitySuccess(cityLocationBean);
                    }
                });
    }

    private static class SingletonLoader {
        private static final IndexModel INSTANCE = new IndexModel();
    }

    public static IndexModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


}
