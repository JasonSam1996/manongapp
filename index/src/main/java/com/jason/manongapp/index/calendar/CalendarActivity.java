package com.jason.manongapp.index.calendar;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.CalendarView;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.find.fragment.FindFragment;
import com.jason.manongapp.find.fragment.FindPresenter;
import com.jason.manongapp.find.news.NewsActivity;
import com.jason.manongapp.index.adapter.RecyclerViewAdapter;
import com.jason.manongapp.index.adapter.SpaceItemDecoration;
import com.jason.manongapp.tally.R;
import com.jason.manongapp.tally.R2;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class CalendarActivity extends MVPBaseActivity<CalendarContract.View, CalendarPresenter> implements CalendarContract.View {

    @BindView(R2.id.recyclerView)
    RecyclerView indexrRecyclerView;

    @BindView(R2.id.calendarView)
    CalendarView calendarView;

    @BindView(R2.id.index_calendar_title)
    TextView tvIndexCalendarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.initTitle();
        mPresenter.getNewsItemP();
    }

    @Override
    public int initLayout() {
        return R.layout.index_calendar;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecyclerView() {
        indexrRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        indexrRecyclerView.addItemDecoration(new SpaceItemDecoration(20));
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerViewAdapter adapter) {
        indexrRecyclerView.setAdapter(adapter);
        adapter.getNewsIDCallBack(new RecyclerViewAdapter.AdapterCallBack() {
            @Override
            public void getIdCallBack(int newsId) {
                mPresenter.getNewsP(String.valueOf(newsId));
            }
        });
    }

    @Override
    public void openNewsDesc(NewsItemBean newsItemBean) {
        Intent intent = new Intent(this,NewsActivity.class);
        intent.putExtra("newsItem",newsItemBean);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void setTitle() {
        tvIndexCalendarTitle.setText(String.valueOf(calendarView.getCurYear()));
    }

    @Override
    public RecyclerViewAdapter getAdapter(ZhiHuNewNewsBean zhiHuNewNewsBean) {
        return new RecyclerViewAdapter(getContext(),zhiHuNewNewsBean.getStories());
    }

    @OnClick(R2.id.index_calendar_image)
    public void finishActivity(View view){
        finish();
        overridePendingTransition(R.anim.finish_in,R.anim.finish_to);
    }



}
