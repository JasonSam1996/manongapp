package com.jason.manongapp.diary.news;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
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
    ImageEditTextView etContent;

    @BindView(R2.id.diary_emoji_item)
    RecyclerView ryeomjiItem;

    @BindView(R2.id.diary_bar_rbemoji)
    CheckBox radioButton;

    private ExpressionAdapter adapter;

    private List<EmojiBean> emojiBeans;

    private int type = 0;

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
                Logger.i("Type："+type);
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

        etTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ryeomjiItem.getVisibility() == View.GONE) {
                    Logger.i("隐藏的");
                }else {
                    /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);*/
                    ryeomjiItem.setVisibility(View.GONE);
                    Logger.i("显示的");
                }
            }
        });
        etContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ryeomjiItem.getVisibility() == View.GONE) {
                    Logger.i("隐藏的");
                }else {
                    /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);*/
                    ryeomjiItem.setVisibility(View.GONE);
                    Logger.i("显示的");
                }
            }
        });


    }



    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
