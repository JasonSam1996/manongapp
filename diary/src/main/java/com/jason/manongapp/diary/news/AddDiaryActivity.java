package com.jason.manongapp.diary.news;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseActivity;
import com.jason.manongapp.base.utils.FinishUtils;
import com.jason.manongapp.base.utils.LocationUtils;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.jason.manongapp.diary.adapter.ExpressionAdapter;
import com.jason.manongapp.diary.bean.EmojiBean;
import com.jason.manongapp.diary.dao.EmojiDao;
import com.jason.manongapp.diary.ui.DiaryDialog;
import com.jason.manongapp.diary.utils.AnimatorUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
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

    @BindView(R2.id.diary_city)
    TextView tvCity;

    @BindView(R2.id.diary_date_image_mood)
    ImageButton ibDiaryMood;

    @BindView(R2.id.diary_date_image_weather)
    ImageButton ibDiaryWeather;

    @BindView(R2.id.diary_day_month)
    TextView tvDayMonth;

    @BindView(R2.id.diary_day_week)
    TextView tvDayWeek;

    @BindView(R2.id.diary_add_date)
    TextView tvAddDate;

    private ExpressionAdapter adapter;

    private List<EmojiBean> emojiBeans;

    private String mood = "无奈";
    private String weather = "晴";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emojiBeans = new ArrayList<>();
        emojiBeans = EmojiDao.getInstance(this).getEmojiBean();
        mPresenter.initView();
        mPresenter.getCity();
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

    @OnClick({R2.id.diary_however,R2.id.diary_tired,R2.id.diary_crying,
            R2.id.diary_sleepy,R2.id.diary_relieved,R2.id.diary_fearful,
            R2.id.diary_smirking,R2.id.diary_pouting,R2.id.diary_weather_sun,
            R2.id.diary_weather_cloudy,R2.id.diary_weather_clounds,R2.id.diary_weather_sun_rain,
            R2.id.diary_weather_rain,R2.id.diary_weather_raiin2,R2.id.diary_weather_storm,
            R2.id.diary_weather_morestorm})
    public void onMood(View view){
        Animation animation = new ScaleAnimation(0, 1.0f, 0f, 1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(200);//动画时间
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        int i = view.getId();
        if (i == R.id.diary_however) {
            ibDiaryMood.setImageResource(R.drawable.diary_mood);
            ibDiaryMood.startAnimation(animation);
            mood = "无奈";
        }else if (i == R.id.diary_tired) {
            ibDiaryMood.setImageResource(R.drawable.diary_tired);
            ibDiaryMood.startAnimation(animation);
            mood = "烦躁";
        }else if (i == R.id.diary_crying) {
            ibDiaryMood.setImageResource(R.drawable.diary_crying);
            ibDiaryMood.startAnimation(animation);
            mood = "伤心";
        }else if (i == R.id.diary_sleepy) {
            ibDiaryMood.setImageResource(R.drawable.diary_sleepy);
            ibDiaryMood.startAnimation(animation);
            mood = "无聊";
        }else if (i == R.id.diary_relieved) {
            ibDiaryMood.setImageResource(R.drawable.diary_relieved);
            ibDiaryMood.startAnimation(animation);
            mood = "开心";
        }else if (i == R.id.diary_fearful) {
            ibDiaryMood.setImageResource(R.drawable.diary_fearful);
            ibDiaryMood.startAnimation(animation);
            mood = "恐惧";
        }else if (i == R.id.diary_smirking) {
            ibDiaryMood.setImageResource(R.drawable.diary_smirking);
            ibDiaryMood.startAnimation(animation);
            mood = "傻笑";
        }else if (i == R.id.diary_pouting) {
            ibDiaryMood.setImageResource(R.drawable.diary_pouting);
            ibDiaryMood.startAnimation(animation);
            mood = "生气";
        }else if (i == R.id.diary_weather_sun) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_sun);
            ibDiaryWeather.startAnimation(animation);
            weather = "晴";
        }else if (i == R.id.diary_weather_cloudy) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_cloudy);
            ibDiaryWeather.startAnimation(animation);
            weather = "晴间多云";
        }else if (i == R.id.diary_weather_clounds) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_clounds);
            ibDiaryWeather.startAnimation(animation);
            weather = "多云";
        }else if (i == R.id.diary_weather_sun_rain) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_sun_rain);
            ibDiaryWeather.startAnimation(animation);
            weather = "晴间多云转小雨";
        }else if (i == R.id.diary_weather_rain) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_rain);
            ibDiaryWeather.startAnimation(animation);
            weather = "大雨";
        }else if (i == R.id.diary_weather_raiin2) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_raiin2);
            ibDiaryWeather.startAnimation(animation);
            weather = "暴雨";
        }else if (i == R.id.diary_weather_storm) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_storm);
            ibDiaryWeather.startAnimation(animation);
            weather = "雷暴";
        }else if (i == R.id.diary_weather_morestorm) {
            ibDiaryWeather.setImageResource(R.drawable.diary_weather_morestorm);
            ibDiaryWeather.startAnimation(animation);
            weather = "雷暴大雨";
        }
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

    @OnClick(R2.id.diaay_bar_rblink)
    public void openLink(View view){
        final DiaryDialog diaryDialog = new DiaryDialog(this);
        diaryDialog.setCanceloOnclickListener(new DiaryDialog.OnCancelOnclickListener() {
            @Override
            public void onNoClick() {
                diaryDialog.dismiss();
            }
        });
        diaryDialog.setConfirmOnclickListener(new DiaryDialog.OnConfirmOnclickListener() {
            @Override
            public void onYesClick() {
//                SpannableString spannableString = new SpannableString(diaryDialog.getEdLinkText());
//                spannableString.setSpan(new URLSpan(diaryDialog.getEdLinkText()),0,100,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                etContent.append(diaryDialog.getEdLinkText());
                diaryDialog.dismiss();
            }
        });
        diaryDialog.show();
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

    @Override
    public void setCityText(String cityText) {
        tvCity.setText(cityText);
    }

    @Override
    public void setDayWeek(String dayWeek) {
        tvDayWeek.setText(dayWeek);
    }

    @Override
    public void setDay(String day) {
        tvDayMonth.setText(day);
    }

    @Override
    public void setYearAndMonth(String yearAndMonth) {
        tvAddDate.setText(yearAndMonth);
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

    @Override
    public String getLocation(Context context) {
        Location location = LocationUtils.getInstance(context).showLocation();
        Logger.i("location："+location);
        if (location != null) {
            String address = location.getLatitude()+","+location.getLongitude();
            Logger.i("address："+address);
            return address;
        }
        return null;
    }

    @Override
    public String getMood() {
        return mood;
    }

    @Override
    public String getWeather() {
        return weather;
    }

    @Override
    public String getYear(Calendar calendar) {
        String month = "";
        switch (calendar.get(Calendar.MONTH) + 1) {
            case 1:
                month = "一月";
                break;
            case 2:
                month = "二月";
                break;
            case 3:
                month = "三月";
                break;
            case 4:
                month = "四月";
                break;
            case 5:
                month = "五月";
                break;
            case 6:
                month = "六月";
                break;
            case 7:
                month = "七月";
                break;
            case 8:
                month = "八月";
                break;
            case 9:
                month = "九月";
                break;
            case 10:
                month = "十月";
                break;
            case 11:
                month = "十一月";
                break;
            case 12:
                month = "十二月";
                break;
            default:
                month = "一月";
                break;
        }
        return String.valueOf(calendar.get(Calendar.YEAR)) + "年" + month;
    }


    @Override
    public String getDay(Calendar calendar) {
        String day = "";
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        }
        return day;
    }

    @Override
    public String getDayWeek(Calendar calendar) {
        String dayWeek = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                dayWeek = "周日";
                break;
            case 2:
                dayWeek = "周一";
                break;
            case 3:
                dayWeek = "周二";
                break;
            case 4:
                dayWeek = "周三";
                break;
            case 5:
                dayWeek = "周四";
                break;
            case 6:
                dayWeek = "周五";
                break;
            case 7:
                dayWeek = "周六";
                break;
            default:
                dayWeek = "周日";
                break;
        }
        return dayWeek;
    }

    @Override
    public String getCity() {
        return tvCity.getText().toString();
    }

    @Override
    public String getBiaryTitle() {
        return etTitle.getText().toString();
    }

    @Override
    public String getObjectId() {
        return SPUtils.get("objectid","");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
