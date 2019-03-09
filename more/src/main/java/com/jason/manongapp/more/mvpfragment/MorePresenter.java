package com.jason.manongapp.more.mvpfragment;

import com.jason.manongapp.base.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MorePresenter extends BasePresenterImpl<MoreContract.View> implements MoreContract.Presenter{


    public void initView(){
        mView.setGridRecyclerView();
        mView.setLinearRecyclerView();
    }

}
