package com.jason.manongapp.index.calendar;


import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.find.fragment.FindModel;
import com.jason.manongapp.tally.R;

import java.util.List;

/**
 * MVPPlugin
 */

public class CalendarPresenter extends BasePresenterImpl<CalendarContract.View> implements CalendarContract.Presenter {

    public void initTitle(){
        mView.setTitle();
    }

    public void getNewsItemP(){
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);

        CalendarModel.getInstance().getNewsItem(this,dialog);
    }

    @Override
    public void getNewsItemSuccess(ZhiHuNewNewsBean zhiHuNewNewsBean) {
        mView.setRecyclerView();
        mView.setRecyclerViewAdapter(mView.getAdapter(zhiHuNewNewsBean));
    }

    @Override
    public void getNewsItemError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    public void getNewsP(String newsId){
        CalendarModel.getInstance().getNews(newsId,this);
    }


    @Override
    public void getNewsSuccess(NewsItemBean newsItemBean) {
        if (newsItemBean != null) {
            mView.openNewsDesc(newsItemBean);
        }
    }

    @Override
    public void getNewsError(String errorMsg) {
        mView.showToast(errorMsg);
    }
}
