package com.jason.manongapp.find.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.find.adapter.RecyclerViewAdapter;
import com.jason.manongapp.find.adapter.SpaceItemDecoration;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.orhanobut.logger.Logger;

public class FindModel implements FindContract.Model {

    private FindModel() {
    }

    @Override
    public void getNewsItem(final FindContract.Presenter presenter, Dialog dialog) {
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
    public void getNews(String newsId, final FindContract.Presenter presenter) {
        RxHttpUtils.createApi(ApiService.class)
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
        private static final FindModel INSTANCE = new FindModel();
    }

    public static FindModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
