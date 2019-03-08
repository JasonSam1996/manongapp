package com.jason.manongapp.index.calendar;


import android.app.Dialog;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.find.fragment.FindContract;
import com.jason.manongapp.index.adapter.RecyclerViewAdapter;

import java.util.List;

/**
 * MVPPlugin
 */

public class CalendarContract {
    interface View extends BaseView {

        void showToast(String msg);

        void setRecyclerView();

        void setRecyclerViewAdapter(RecyclerViewAdapter adapter);

        void openNewsDesc(NewsItemBean newsItemBean);

        void setTitle();

        RecyclerViewAdapter getAdapter(ZhiHuNewNewsBean zhiHuNewNewsBean);

    }


    interface Model {
        void getNewsItem(Presenter presenter, Dialog dialog);
        void getNews(String newsId,Presenter presenter);
    }

    interface Presenter extends BasePresenter<View> {
        void getNewsItemSuccess(ZhiHuNewNewsBean zhiHuNewNewsBean);

        void getNewsItemError(String errorMsg);

        void getNewsSuccess(NewsItemBean newsItemBean);

        void getNewsError(String errorMsg);
    }

}
