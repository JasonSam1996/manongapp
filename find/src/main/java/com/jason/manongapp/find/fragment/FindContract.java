package com.jason.manongapp.find.fragment;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.find.adapter.RecyclerViewAdapter;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindContract {
    interface View extends BaseView {


        void showToast(String msg);

        void setRecyclerView();

        void setRecyclerViewAdapter(RecyclerViewAdapter adapter);

        void openNewsDesc(NewsItemBean newsItemBean);

        void initBanner(ZhiHuNewNewsBean zhiHuNewNewsBean,List<String> imageUrls,List<String> titles);

        void setRefresh();

        List<String> getTopImageUrls(ZhiHuNewNewsBean zhiHuNewNewsBean);

        List<String> getTopImageTitles(ZhiHuNewNewsBean zhiHuNewNewsBean);

        RecyclerViewAdapter getAdapter(ZhiHuNewNewsBean zhiHuNewNewsBean);

    }

    interface Model{
        void getNewsItem(Presenter presenter, Dialog dialog);
        void getNewsItemRefresh(Presenter presenter);
        void getNews(String newsId,Presenter presenter);
    }


    interface  Presenter extends BasePresenter<View> {
        void getNewsItemSuccess(ZhiHuNewNewsBean zhiHuNewNewsBean,boolean isRefresh);

        void getNewsItemError(String errorMsg);

        void getNewsSuccess(NewsItemBean newsItemBean);

        void getNewsError(String errorMsg);
    }
}
