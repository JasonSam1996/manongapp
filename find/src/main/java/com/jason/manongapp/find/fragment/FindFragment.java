package com.jason.manongapp.find.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.base.mvp.MVPBaseFragment;
import com.jason.manongapp.find.R;
import com.jason.manongapp.find.R2;
import com.jason.manongapp.find.adapter.RecyclerViewAdapter;
import com.jason.manongapp.find.adapter.SpaceItemDecoration;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.find.imageloader.FrescoImageLoader;
import com.jason.manongapp.find.news.NewsActivity;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;


public class FindFragment extends MVPBaseFragment<FindContract.View,FindPresenter> implements FindContract.View {

    @BindView(R2.id.find_rv)
    RecyclerView findrRecyclerView;

    @BindView(R2.id.find_banner)
    Banner findBanner;

    @BindView(R2.id.find_swipe)
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Override
    public void initView() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });
//        mPresenter.initTitle();
    }


    @Override
    public void initData() {
        mPresenter.getNewsItemP();
    }

    @Override
    public int initLayout() {
        return R.layout.find_fragment;
    }



    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecyclerView() {
        findrRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        findrRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerViewAdapter adapter) {
        findrRecyclerView.setAdapter(adapter);
        adapter.getNewsIDCallBack(new RecyclerViewAdapter.AdapterCallBack() {
            @Override
            public void getIdCallBack(int newsId) {
                Logger.i("newsId："+newsId);
                mPresenter.getNewsP(String.valueOf(newsId));
            }
        });
    }

    @Override
    public void openNewsDesc(NewsItemBean newsItemBean) {
        Intent intent = new Intent(getActivity(),NewsActivity.class);
        intent.putExtra("newsItem",newsItemBean);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);

    }

    @Override
    public void initBanner(final ZhiHuNewNewsBean zhiHuNewNewsBean, List<String> imageUrls, List<String> titles) {
        findBanner.setImageLoader(new FrescoImageLoader());
        findBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        findBanner.setImages(imageUrls);
        findBanner.setBannerTitles(titles);
        findBanner.setDelayTime(3000);
        findBanner.start();
        findBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                mPresenter.getNewsP(String.valueOf(zhiHuNewNewsBean.getTop_stories().get(position).getId()));
            }
        });
    }

    @Override
    public void setRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public List<String> getTopImageUrls(ZhiHuNewNewsBean zhiHuNewNewsBean) {
        final List<String> imageUrls = new ArrayList<>();
        Observable.fromIterable(zhiHuNewNewsBean.getTop_stories())
                .subscribe(new CommonObserver<ZhiHuNewNewsBean.TopStoriesBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.e("error："+errorMsg);
                    }

                    @Override
                    protected void onSuccess(ZhiHuNewNewsBean.TopStoriesBean topStoriesBean) {
                        Logger.i("success："+topStoriesBean.toString());
                        imageUrls.add(topStoriesBean.getImage());
                    }
                });
//        zhiHuNewNewsBean.getTop_stories()
        return imageUrls;
    }

    @Override
    public List<String> getTopImageTitles(ZhiHuNewNewsBean zhiHuNewNewsBean) {
        final List<String> titles = new ArrayList<>();
        Observable.fromIterable(zhiHuNewNewsBean.getTop_stories())
                .subscribe(new CommonObserver<ZhiHuNewNewsBean.TopStoriesBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.e("error："+errorMsg);
                    }

                    @Override
                    protected void onSuccess(ZhiHuNewNewsBean.TopStoriesBean topStoriesBean) {
                        Logger.i("success："+topStoriesBean.toString());
                        titles.add(topStoriesBean.getTitle());
                    }
                });
//        zhiHuNewNewsBean.getTop_stories()
        return titles;
    }

    @Override
    public RecyclerViewAdapter getAdapter(ZhiHuNewNewsBean zhiHuNewNewsBean) {
        return new RecyclerViewAdapter(getContext(),zhiHuNewNewsBean.getStories());
    }

    @Override
    public void onStart() {
        super.onStart();
        findBanner.startAutoPlay();
    }

    /*@Override
    public void onStop() {
        super.onStop();
        findBanner.stopAutoPlay();
    }*/



}
