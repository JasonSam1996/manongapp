package com.jason.manongapp.index.calendar;


import android.app.Dialog;

import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.index.calendar.api.ApiService;
import com.orhanobut.logger.Logger;

public class CalendarModel implements CalendarContract.Model {

    private CalendarModel() {
    }

    @Override
    public void getNewsItem(final CalendarContract.Presenter presenter, Dialog dialog) {
        RxHttpUtils.createApi(ApiService.class)
                .getNewsItem()
                .compose(Transformer.<ZhiHuNewNewsBean>switchSchedulers(dialog))
                .subscribe(new CommonObserver<ZhiHuNewNewsBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.e("onError: "+errorMsg);
                        presenter.getNewsItemError(errorMsg);
//                        Log.i("TAG", "onError: "+errorMsg);
                    }

                    @Override
                    protected void onSuccess(ZhiHuNewNewsBean zhiHuNewNewsBean) {
                        if (zhiHuNewNewsBean != null) {
                            presenter.getNewsItemSuccess(zhiHuNewNewsBean);
                        }
                    }
                });
    }

    @Override
    public void getNews(String newsId, final CalendarContract.Presenter presenter) {
        RxHttpUtils.createApi(com.jason.manongapp.find.fragment.ApiService.class)
                .getNews(String.valueOf(newsId))
                .compose(Transformer.<NewsItemBean>switchSchedulers())
                .subscribe(new CommonObserver<NewsItemBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.e("onError: "+errorMsg);
                        presenter.getNewsError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(NewsItemBean newsItemBean) {
                        Logger.i("newsItemBean："+newsItemBean);
                        if (newsItemBean != null) {
                            presenter.getNewsSuccess(newsItemBean);
                        }
                    }
                });
    }

    private static class SingletonLoader {
        private static final CalendarModel INSTANCE = new CalendarModel();
    }

    public static CalendarModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


    }

