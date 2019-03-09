package com.jason.manongapp.diary.news;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.base.utils.FinishUtils;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.jason.manongapp.diary.adapter.ExpressionAdapter;
import com.jason.manongapp.diary.bean.EmojiBean;
import com.jason.manongapp.diary.dao.EmojiDao;
import com.jason.manongapp.diary.ui.ImageEditTextView;
import com.jason.manongapp.diary.utils.AnimatorUtils;
import com.jason.manongapp.diary.utils.ImageUtils;
import com.jason.manongapp.diary.utils.ScreenUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


/**
 * MVPPlugin
 */

public class AddDiaryActivity extends MVPBaseActivity<AddDiaryContract.View, AddDiaryPresenter> implements AddDiaryContract.View {

    @BindView(R2.id.diary_weather_mood_layout)
    View selectWeatherLayout;

    @BindView(R2.id.diary_select_weather_and_mood)
    LinearLayout linearLayout;

    @BindView(R2.id.diary_et_title)
    EditText etTitle;

    @BindView(R2.id.diary_et_content)
    EditText etContent;

    @BindView(R2.id.diary_emoji_item)
    RecyclerView ryeomjiItem;

    @BindView(R2.id.diary_bar_rbemoji)
    CheckBox radioButton;




    private ExpressionAdapter adapter;

    private List<EmojiBean> emojiBeans;


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emojiBeans = new ArrayList<>();
        emojiBeans = EmojiDao.getInstance(this).getEmojiBean();
        mPresenter.initView();
    }

    @Override
    public int initLayout() {
        return R.layout.add_diary_activity;
    }

    @OnClick({R2.id.diary_date_image_mood, R2.id.diary_date_image_weather})
    public void openSelectWeatherLayout(View view) {
        float mDensity = getResources().getDisplayMetrics().density;
        int mHiddenViewMeasuredHeight = (int) (mDensity * 200 + 0.5);
        if (selectWeatherLayout.getVisibility() == View.GONE) {
            AnimatorUtils.animateOpen(selectWeatherLayout, mHiddenViewMeasuredHeight);
            etTitle.setEnabled(false);
            etContent.setEnabled(false);
//            selectWeatherLayout.setVisibility(View.VISIBLE);
        } else {
            AnimatorUtils.animateClose(selectWeatherLayout);
            etTitle.setEnabled(true);
            etContent.setEnabled(true);
        }
    }

    @OnClick(R2.id.diary_back)
    public void onBack(View view) {
        FinishUtils.finishUtils(this);
    }

    @OnCheckedChanged(R2.id.diary_bar_rbemoji)
    public void openEomji(CompoundButton view, boolean ischanged) {
        Logger.i("ischanged："+ischanged);
        if (ischanged) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

            ryeomjiItem.setVisibility(View.VISIBLE);
        }else {
            ryeomjiItem.setVisibility(View.GONE);

        }
        /*if (ryeomjiItem.getVisibility() == View.VISIBLE) {
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }*/
    }

    @OnClick(R2.id.diary_bar_rbpicture)
    public void openPicture(View view){

        Intent getAlbum = new Intent(Intent.ACTION_PICK);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum,500);
    }

    @Override
    public void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        ryeomjiItem.setLayoutManager(manager);
        Logger.i("emoji：" + emojiBeans);
        adapter = new ExpressionAdapter(emojiBeans, this);
        ryeomjiItem.setAdapter(adapter);
        adapter.setItemChildClickListener(new ExpressionAdapter.ItemChildClickListener() {
            @Override
            public void onChildClick(final int position) {
                if (etTitle.hasFocus()) {
                    etTitle.append(adapter.getItem(position).getEmojiString());
                }
                if (etContent.hasFocus()) {
                    etContent.append(adapter.getItem(position).getEmojiString());

                }
            }
        });
    }

//    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initKeyboard() {

        etTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ryeomjiItem.getVisibility()==View.VISIBLE && hasFocus) {
                    ryeomjiItem.setVisibility(View.GONE);
                }
            }
        });

        etContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (ryeomjiItem.getVisibility()==View.VISIBLE && hasFocus) {
                    ryeomjiItem.setVisibility(View.GONE);
                }
            }
        });



    }

    @OnClick({R2.id.diary_et_title,R2.id.diary_et_content})
    public void titleAndContent(View view){
        if (ryeomjiItem.getVisibility() == View.GONE) {
            Logger.i("隐藏的");
        }else {
            ryeomjiItem.setVisibility(View.GONE);
            Logger.i("显示的");
        }
    }

    @OnClick(R2.id.diary_save)
    public void save(View view){
        mPresenter.saveDiary();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.i("requestCode："+requestCode+"，resultCode："+resultCode);
        Bitmap bm = null;
        ContentResolver resolver = getContentResolver();
        if (requestCode == 500) {
            if (data == null) {
                showToast("取消插入图片");
                return;
            }
            mPresenter.selectPhotoCallback(this,data,resolver);
//            selectPhotoCallback(data, resolver);
        }
    }


//    @OnClick(R2.id)


    @Override
    public void etContentAppendEnter() {
        etContent.append("\n");
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }
    //endregion

    //region 将图片插入到EditText中
    @Override
    public void insertPhotoToEditText(SpannableString ss){
        Editable et = etContent.getText();
        int start = etContent.getSelectionStart();
        et.insert(start,ss);
        etContent.setText(et);
        etContent.setSelection(start+ss.length());
        etContent.setFocusableInTouchMode(true);
        etContent.setFocusable(true);
    }
    //endregion


}
