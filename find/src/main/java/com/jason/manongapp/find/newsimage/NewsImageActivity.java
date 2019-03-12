package com.jason.manongapp.find.newsimage;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.base.utils.DownLoadImageUtils;
import com.jason.manongapp.base.utils.FinishUtils;
import com.jason.manongapp.find.R;
import com.jason.manongapp.find.R2;
import com.jason.manongapp.find.adapter.ImagePagerAdapter;
import com.jason.manongapp.find.newsimage.ui.NewImageViewPager;
import com.jason.manongapp.find.ui.ViewPagerFixed;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.PhotoDraweeView;


/**
 * MVPPlugin
 */

public class NewsImageActivity extends MVPBaseActivity<NewsImageContract.View, NewsImagePresenter> implements NewsImageContract.View {

    @BindView(R2.id.news_image_viewPager)
    ViewPagerFixed vpNewsImage;

    @BindView(R2.id.news_image_page)
    TextView tvNewsImagePage;

    private String imageUrl;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTitle(false);
        setState(false);
        super.onCreate(savedInstanceState);
        imageUrl = getOpenImageList().get(getOpenImagePosition());
        mPresenter.initView();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_news_image;
    }

    @Override
    public int getOpemImageAllNum() {
        return this.getIntent().getIntExtra("openImageAllNum", 0);
    }

    @Override
    public int getOpenImagePosition() {
        return this.getIntent().getIntExtra("openImagePosition", 0);
    }

    @Override
    public List<String> getOpenImageList() {
        return this.getIntent().getStringArrayListExtra("openImageList");
    }

    @Override
    public List<SimpleDraweeView> getAndAddPhotoDraweeView() {
        /*DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse(imageUrl))
                .build();
        photoDraweeView.setController(controller);*/
        List<SimpleDraweeView> draweeViews = new ArrayList<>();
        Logger.i("getOpenImageList："+getOpenImageList().get(0));
        for (int i = 0; i < getOpenImageList().size(); i++) {
            Logger.i("getOpenImageList："+getOpenImageList().get(i));
            PhotoDraweeView photoDraweeView = new PhotoDraweeView(this);
            photoDraweeView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    FinishUtils.finishUtils(NewsImageActivity.this);
                }
            });
            String imageUrl = getOpenImageList().get(i);

            if (imageUrl.endsWith(".gif")) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse(imageUrl))
                        .build();
                simpleDraweeView.setController(controller);
                draweeViews.add(simpleDraweeView);
            }else if (imageUrl.endsWith("jpg")) {
                photoDraweeView.setPhotoUri(Uri.parse(imageUrl));
                draweeViews.add(photoDraweeView);
            }else if (imageUrl.endsWith("png")) {
                photoDraweeView.setPhotoUri(Uri.parse(imageUrl));
                draweeViews.add(photoDraweeView);
            }
        }
        return draweeViews;
    }

    @Override
    public void setTvPage(String page) {
        tvNewsImagePage.setText(page);
    }

    @Override
    public ImagePagerAdapter getAdapter(List<SimpleDraweeView> draweeViews) {
        return new ImagePagerAdapter(this, draweeViews);
    }

    @Override
    public void setViewPager(ImagePagerAdapter adapter, int nowPage) {
        vpNewsImage.setAdapter(adapter);
        vpNewsImage.setCurrentItem(nowPage);
        vpNewsImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Logger.i("onPageSelected：" + i);
                setTvPage(i + 1 + "/" + getOpemImageAllNum());
                imageUrl = getOpenImageList().get(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick(R2.id.news_image_download)
    public void downloadImage(View view){
//        if (getOpenImagePosition() == 0) {
//        }
        Logger.i("imageUrl："+imageUrl);
        DownLoadImageUtils.downloadPic(this,imageUrl);
    }






}
