package com.jason.manongapp.diary.news;


import com.jason.manongapp.base.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 */

public class AddDiaryPresenter extends BasePresenterImpl<AddDiaryContract.View> implements AddDiaryContract.Presenter {

    public void initView(){
        mView.initRecyclerView();
        mView.initKeyboard();
    }

}
