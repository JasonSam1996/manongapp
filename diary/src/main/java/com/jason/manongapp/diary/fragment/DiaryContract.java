package com.jason.manongapp.diary.fragment;

import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.diary.adapter.DiaryAdapter;
import com.jason.manongapp.diary.bean.DiaryBean;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DiaryContract {
    interface View extends BaseView {

        void showToast(String msg);

        void setRecyclerView();

        void setRecyclerViewAdapter(List<DiaryBean.ResultsBean> diaryBeans);

        String getUser();

    }

    interface Model{

        void getDiary(String userJson,Presenter presenter);

    }


    interface  Presenter extends BasePresenter<View> {

        void getDiarySuccess(DiaryBean diaryBean);

        void getDiaryError(String erroeMsg);

    }
}
