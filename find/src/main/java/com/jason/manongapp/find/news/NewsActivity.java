package com.jason.manongapp.find.news;



import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.base.utils.FinishUtils;
import com.jason.manongapp.find.R;
import com.jason.manongapp.find.R2;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class NewsActivity extends MVPBaseActivity<NewsContract.View, NewsPresenter> implements NewsContract.View {

    @BindView(R2.id.newsItemWebView)
    WebView newsWebView;

    @BindView(R2.id.newsItemImage)
    SimpleDraweeView titleDraweeView;

    @BindView(R2.id.new_scrollView)
    ScrollView newsScrollView;

    @BindView(R2.id.news_desc_title_text)
    TextView tvNewsTitile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(false);
        super.onCreate(savedInstanceState);
        mPresenter.initView(newsWebView,titleDraweeView,newsScrollView);
    }

    @Override
    public int initLayout() {
        return R.layout.activity_new_desc;
    }

    @Override
    public String getTitleText() {
        return getNewItemBean().getTitle();
    }

    @Override
    public void setTitleText(String msg) {
        /*if (msg.length() > 10) {
            msg = msg.substring(0,10) + "······";
        }*/
        tvNewsTitile.setText(msg);
    }

    @Override
    public void initWebView(WebView webView, final SimpleDraweeView simpleDraweeView, final ScrollView scrollView,NewsItemBean newsItemBean) {
        webView.addJavascriptInterface(new ImageJavascriptInterface(this),"imageListener");
        final WebSettings webSetting = webView.getSettings();
        webSetting.setDisplayZoomControls(false);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                if (s.startsWith("http://") || s.startsWith("https://")) {
                    simpleDraweeView.setVisibility(View.GONE);
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                    webView.loadUrl(s);
                    return true;
                }
                return false;
            }



            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "var imgUrl = \"\";"+
                        "var filter = [\"img//EventHead.png\",\"img//kong.png\",\"hdtz//button.png\"];"+
                        "var isShow = true;"+
                        "for(var i=0;i<objs.length;i++){" +
                        "for(var j=0;j<filter.length;j++){"+
                        "if(objs[i].src.indexOf(filter[j])>=0) {"+
                        "isShow = false; break;}}"+
                        "if(isShow && objs[i].width>80){"+
                        "imgUrl += objs[i].src + ',';isShow = true;"+
                        "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.imageListener.openImage(imgUrl,this.src);" +
                        "    }" +
                        "}" +
                        "}" +
                        "})()"
                );
            }
        });
        String body = newsItemBean.getBody();
        String css = newsItemBean.getCss().get(0);
        String html = "<html>" +
                "<head><link rel=\"stylesheet\" type=\"text/css\" href=\""+css+"\">" +
                "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" />"+
                "<head>" +
                "<body>"+body+"<body>" +
                "<html>";

        webView.loadData(html,"text/html;charset=UTF-8",null);
    }

    @Override
    public NewsItemBean getNewItemBean() {
        return (NewsItemBean) this.getIntent().getSerializableExtra("newsItem");
    }

    @Override
    public void initDraweeView(SimpleDraweeView simpleDraweeView,NewsItemBean newsItemBean) {
        String imageUrl = newsItemBean.getImage();
        Uri uri = Uri.parse(imageUrl);
        simpleDraweeView.setImageURI(uri);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroyWebView(newsWebView);
        super.onDestroy();
    }

    @OnClick(R2.id.news_desc_back)
    public void onBack(View view){
        FinishUtils.finishUtils(this);
    }

}
