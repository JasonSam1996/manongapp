package com.jason.manongapp.find.news;


import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.tencent.smtt.sdk.WebView;

/**
 * MVPPlugin
 */

public class NewsContract {
    interface View extends BaseView {

        String getTitleText();

        void setTitleText(String msg);

        void initWebView(WebView webView, SimpleDraweeView simpleDraweeView, ScrollView scrollView, NewsItemBean newsItemBean);

        NewsItemBean getNewItemBean();

        void initDraweeView(SimpleDraweeView simpleDraweeView, NewsItemBean newsItemBean);
    }


    interface Model {
    }

    interface Presenter extends BasePresenter<View> {

    }

}
