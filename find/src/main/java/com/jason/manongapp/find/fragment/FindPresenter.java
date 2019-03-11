package com.jason.manongapp.find.fragment;

import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.find.R;
import com.jason.manongapp.find.adapter.RecyclerViewAdapter;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FindPresenter extends BasePresenterImpl<FindContract.View> implements FindContract.Presenter{

    public void refresh(){
        FindModel.getInstance().getNewsItemRefresh(this);
    }

    public void getNewsItemP(){
        LoadingDialog dialog = new LoadingDialog(mView.getContext(),R.style.MyDialog);

        FindModel.getInstance().getNewsItem(this,dialog);
    }

    public void getNewsP(String newsId){
        FindModel.getInstance().getNews(newsId,this);
    }


    @Override
    public void getNewsItemSuccess(ZhiHuNewNewsBean zhiHuNewNewsBean,boolean isRefresh) {
        RecyclerViewAdapter adapter = mView.getAdapter(zhiHuNewNewsBean);
        if (isRefresh) {
            if (zhiHuNewNewsBean.getStories().size() == adapter.getItemCount()) {
                mView.showToast("没有数据刷新");
                mView.setRefresh();
            }else if (zhiHuNewNewsBean.getStories().size() > adapter.getItemCount()) {
                adapter = mView.getAdapter(zhiHuNewNewsBean);
                adapter.notifyDataSetChanged();
                mView.setRefresh();
            }
        }else {
            mView.setRecyclerView();
            mView.setRecyclerViewAdapter(adapter);
            List<String> topImageUrls = mView.getTopImageUrls(zhiHuNewNewsBean);
            List<String> titles = mView.getTopImageTitles(zhiHuNewNewsBean);
            mView.initBanner(zhiHuNewNewsBean,topImageUrls,titles);
        }
    }

    @Override
    public void getNewsItemError(String errorMsg) {
        mView.showToast(errorMsg);
    }

    @Override
    public void getNewsSuccess(NewsItemBean newsItemBean) {
        if (newsItemBean != null) {
            mView.openNewsDesc(newsItemBean);
        }
//        Logger.i("success："+newsItemBean.toString());
    }

    @Override
    public void getNewsError(String errorMsg) {
        mView.showToast(errorMsg);
    }
}
