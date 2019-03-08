package com.jason.manongapp.find.newsimage;


import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.find.adapter.ImagePagerAdapter;

import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * MVPPlugin
 */

public class NewsImagePresenter extends BasePresenterImpl<NewsImageContract.View> implements NewsImageContract.Presenter {

    public void initView() {
        mView.setTvPage(mView.getOpenImagePosition() + 1 + "/" + mView.getOpemImageAllNum());
        List<SimpleDraweeView> photoDraweeViews = mView.getAndAddPhotoDraweeView();
        ImagePagerAdapter adapter = mView.getAdapter(photoDraweeViews);
        mView.setViewPager(adapter,mView.getOpenImagePosition());
    }

}
