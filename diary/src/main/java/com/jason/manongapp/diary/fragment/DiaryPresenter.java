package com.jason.manongapp.diary.fragment;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jason.manongapp.base.dialog.LoadingDialog;
import com.jason.manongapp.base.mvp.BasePresenterImpl;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.bean.DiaryBean;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DiaryPresenter extends BasePresenterImpl<DiaryContract.View> implements DiaryContract.Presenter {

    public void initData() {
        try {

            mView.setRecyclerView();
            JSONObject jsonObject = new JSONObject();
            if (TextUtils.isEmpty(mView.getUser())) {
                mView.showToast("请重新登录");
            }
            jsonObject.put("user", mView.getUser());
            Logger.i("user："+jsonObject.toString());
            DiaryModel.getInstance().getDiary(jsonObject.toString()/*URLEncoder.encode(jsonObject.toString(),"utf-8")*/,this);

        } catch (JSONException e) {
            e.printStackTrace();
        } /*catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

//        Gson gson = new Gson();
//        gson.
    }

    @Override
    public void getDiarySuccess(DiaryBean diaryBean) {
        Logger.i("diaryBean："+diaryBean);
        if (diaryBean.getResults().size() > 0) {
            mView.setRecyclerViewAdapter(diaryBean.getResults());
        }
    }

    @Override
    public void getDiaryError(String erroeMsg) {
        Logger.i("errorMsg："+erroeMsg);
        mView.showToast(erroeMsg);
    }
}
