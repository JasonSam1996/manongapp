package com.jason.manongapp.diary.news;


import android.app.Dialog;
import android.text.SpannableString;
import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.diary.bean.UpLoadingCallBack;

/**
 * MVPPlugin
 */

public class AddDiaryContract {
    interface View extends BaseView {

        void initRecyclerView();

        void initKeyboard();

        void showToast(String msg);

        void insertPhotoToEditText(SpannableString ss);

        void etContentAppendEnter();

        String getContent();

    }


    interface Model {
        void uploading(String path, Presenter presenter, Dialog dialog);
    }

    interface Presenter extends BasePresenter<View> {
        void upLoadingSuccess(UpLoadingCallBack upLoadingCallBack);
        void upLoadingError(String errorMsg);
    }

}
