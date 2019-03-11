package com.jason.manongapp.diary.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseFragment;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.jason.manongapp.diary.adapter.DiaryAdapter;
import com.jason.manongapp.diary.bean.DiaryBean;
import com.jason.manongapp.diary.news.AddDiaryActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DiaryFragment extends MVPBaseFragment<DiaryContract.View,DiaryPresenter> implements DiaryContract.View {

    @BindView(R2.id.diary_recyclerView)
    RecyclerView diaryRecyclerView;


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.initData();
    }

    @Override
    public int initLayout() {
        return R.layout.diary_fragment;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecyclerView() {
        diaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setRecyclerViewAdapter(List<DiaryBean.ResultsBean> diaryBeans) {
        DiaryAdapter adapter = new DiaryAdapter(diaryBeans,getContext());
        diaryRecyclerView.setAdapter(adapter);
    }

    @Override
    public String getUser() {
        return SPUtils.get("objectid","");
    }

    @OnClick(R2.id.diary_add)
    public void openAddDiary(View view){
        Intent intent = new Intent(getContext(),AddDiaryActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
