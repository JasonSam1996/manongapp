package com.jason.manongapp.find.news;


import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.tencent.smtt.sdk.WebView;

/**
 * MVPPlugin
 */

public class NewsPresenter extends BasePresenterImpl<NewsContract.View> implements NewsContract.Presenter {

    public void initView(WebView webView, SimpleDraweeView simpleDraweeView, ScrollView scrollView) {
        NewsItemBean newsItemBean = mView.getNewItemBean();
        mView.initWebView(webView, simpleDraweeView, scrollView, newsItemBean);
        mView.initDraweeView(simpleDraweeView, newsItemBean);
        mView.setTitleText(mView.getTitleText());
    }

    public void onDestroyWebView(WebView webView) {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
    }

}
