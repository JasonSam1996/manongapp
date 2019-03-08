package com.jason.manongapp.more.mvpfragment;

import android.content.Context;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MoreContract {
    interface View extends BaseView {
        void setGridRecyclerView();

        void setLinearRecyclerView();

        void showToast(String msg);

    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
