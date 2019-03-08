package com.jason.manongapp.diary.news;


import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;

/**
 * MVPPlugin
 */

public class AddDiaryContract {
    interface View extends BaseView {

        void initRecyclerView();

        void initKeyboard();

        void showToast(String msg);

    }


    interface Model {
    }

    interface Presenter extends BasePresenter<View> {

    }

}
