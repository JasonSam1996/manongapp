package com.jason.manongapp.find.newsimage;


import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.find.adapter.ImagePagerAdapter;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.tencent.smtt.sdk.WebView;

import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * MVPPlugin
 */

public class NewsImageContract {
    interface View extends BaseView {

        int getOpemImageAllNum();

        int getOpenImagePosition();

        List<String> getOpenImageList();

        List<SimpleDraweeView> getAndAddPhotoDraweeView();

        void setTvPage(String page);

        ImagePagerAdapter getAdapter(List<SimpleDraweeView> draweeViews);

        void setViewPager(ImagePagerAdapter adapter,int nowPage);

    }


    interface Model {
    }

    interface Presenter extends BasePresenter<View> {

    }

}
