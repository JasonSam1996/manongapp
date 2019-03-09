package com.jason.manongapp.index.fragment;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseFragment;
import com.jason.manongapp.diary.news.AddDiaryActivity;
import com.jason.manongapp.index.calendar.CalendarActivity;
import com.jason.manongapp.index.utils.LocationUtils;
import com.jason.manongapp.tally.R;
import com.jason.manongapp.tally.R2;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class IndexFragment extends MVPBaseFragment<IndexContract.View, IndexPresenter> implements IndexContract.View {



    @BindView(R2.id.index_year)
    TextView tvYear;

    @BindView(R2.id.index_month)
    TextView tvMonth;

    @BindView(R2.id.index_day)
    TextView tvDay;

    @BindView(R2.id.index_dayWeek)
    TextView tvDayWeek;

    @BindView(R2.id.index_image)
    SimpleDraweeView indexSimpleDraweeView;

    @BindView(R2.id.index_quotes)
    TextView tvIndexQuotes;

    @BindView(R2.id.index_city)
    TextView tvIndexCity;

    @BindView(R2.id.index_weather_text)
    TextView tvIndexWeather;

    @BindView(R2.id.index_weather_image)
    ImageView ivWeather;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        SPUtils.put("Date",format);
    }

    @Override
    public void initView() {
        mPresenter.initTextView();
    }

    @Override
    public void initData() {
        mPresenter.getCity();
        mPresenter.getImage();
//        DBHelper.getInstance().getSession().getDateDaoDao().insert()
    }

    @Override
    public int initLayout() {
        return R.layout.index_fragment;
    }


    @Override
    public String getYear(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public String getMonth(Calendar calendar) {
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

        return month;
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
                dayWeek = "星期日";
                break;
            case 2:
                dayWeek = "星期一";
                break;
            case 3:
                dayWeek = "星期二";
                break;
            case 4:
                dayWeek = "星期三";
                break;
            case 5:
                dayWeek = "星期四";
                break;
            case 6:
                dayWeek = "星期五";
                break;
            case 7:
                dayWeek = "星期六";
                break;
            default:
                dayWeek = "星期日";
                break;
        }
        return dayWeek;
    }

    @Override
    public String getSPDate() {
        return SPUtils.get("Date", "");
    }

    @Override
    public String getCity() {
        return tvIndexCity.getText().toString().trim();
    }

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
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTextYear(String year) {
        tvYear.setText(year);
    }

    @Override
    public void setTextMonth(String month) {
        tvMonth.setText(month);
    }

    @Override
    public void setTextDay(String day) {
        tvDay.setText(day);
    }

    @Override
    public void setTextDayWeek(String dayWeek) {
        tvDayWeek.setText(dayWeek);
    }

    @Override
    public void setSimpleDraweeViewUri(String url) {
        indexSimpleDraweeView.setImageURI(Uri.parse(url));
    }

    @Override
    public void setQuotesText(String msg) {
        tvIndexQuotes.setText(msg);
    }

    @Override
    public void setWeatherText(String msg) {
        tvIndexWeather.setText(msg);
    }

    @Override
    public void setWeatherImage(String code) {
        switch (code){
            case "100":
                ivWeather.setImageResource(R.drawable.sunny);
                break;
            case "101":
                ivWeather.setImageResource(R.drawable.cloudy);
                break;
            case "102":
                ivWeather.setImageResource(R.drawable.few_clouds);
                break;
            case "103":
                ivWeather.setImageResource(R.drawable.partly_cloudy);
                break;
            case "104":
                ivWeather.setImageResource(R.drawable.overcast);
                break;
            case "200":
                ivWeather.setImageResource(R.drawable.windy);
                break;
            case "201":
                ivWeather.setImageResource(R.drawable.calm);
                break;
            case "202":
                ivWeather.setImageResource(R.drawable.light_breeze);
                break;
            case "203":
                ivWeather.setImageResource(R.drawable.moderate);
                break;
            case "204":
                ivWeather.setImageResource(R.drawable.fresh_breeze);
                break;
            case "205":
                ivWeather.setImageResource(R.drawable.strong_breeze);
                break;
            case "206":
                ivWeather.setImageResource(R.drawable.high_wind);
                break;
            case "207":
                ivWeather.setImageResource(R.drawable.gale);
                break;
            case "208":
                ivWeather.setImageResource(R.drawable.strong_gale);
                break;
            case "209":
                ivWeather.setImageResource(R.drawable.storm);
                break;
            case "210":
                ivWeather.setImageResource(R.drawable.violent_storm);
                break;
            case "211":
                ivWeather.setImageResource(R.drawable.hurricane);
                break;
            case "212":
                ivWeather.setImageResource(R.drawable.tornado);
                break;
            case "213":
                ivWeather.setImageResource(R.drawable.tropical_storm);
                break;
            case "300":
                ivWeather.setImageResource(R.drawable.shower_rain);
                break;
            case "301":
                ivWeather.setImageResource(R.drawable.heavy_shower_rain);
                break;
            case "302":
                ivWeather.setImageResource(R.drawable.thundershower);
                break;
            case "303":
                ivWeather.setImageResource(R.drawable.heavy_thunderstorm);
                break;
            case "304":
                ivWeather.setImageResource(R.drawable.thundershower_with_hail);
                break;
            case "305":
                ivWeather.setImageResource(R.drawable.light_rain);
                break;
            case "306":
                ivWeather.setImageResource(R.drawable.moderate_rain);
                break;
            case "307":
                ivWeather.setImageResource(R.drawable.heavy_rain);
                break;
            case "309":
                ivWeather.setImageResource(R.drawable.drizzle_rain);
                break;
            case "310":
                ivWeather.setImageResource(R.drawable.storm_rain);
                break;
            case "311":
                ivWeather.setImageResource(R.drawable.heavy_storm);
                break;
            case "312":
                ivWeather.setImageResource(R.drawable.severe_storm);
                break;
            case "313":
                ivWeather.setImageResource(R.drawable.freezing_rain);
                break;
            case "314":
                ivWeather.setImageResource(R.drawable.light_to_moderate_rain);
                break;
            case "315":
                ivWeather.setImageResource(R.drawable.moderate_to_heavy_rain);
                break;
            case "316":
                ivWeather.setImageResource(R.drawable.heavy_rain_to_storm);
                break;
            case "317":
                ivWeather.setImageResource(R.drawable.storm_to_heavy_storm);
                break;
            case "318":
                ivWeather.setImageResource(R.drawable.heavy_to_severe_storm);
                break;
            case "399":
                ivWeather.setImageResource(R.drawable.rain);
                break;
            case "400":
                ivWeather.setImageResource(R.drawable.light_snow);
                break;
            case "401":
                ivWeather.setImageResource(R.drawable.moderate_snow);
                break;
            case "402":
                ivWeather.setImageResource(R.drawable.heavy_snow);
                break;
            case "403":
                ivWeather.setImageResource(R.drawable.snowstorm);
                break;
            case "404":
                ivWeather.setImageResource(R.drawable.sleet);
                break;
            case "405":
                ivWeather.setImageResource(R.drawable.rain_and_snow);
                break;
            case "406":
                ivWeather.setImageResource(R.drawable.shower_snow);
                break;
            case "407":
                ivWeather.setImageResource(R.drawable.snow_flurry);
                break;
            case "408":
                ivWeather.setImageResource(R.drawable.light_to_moderate_snow);
                break;
            case "409":
                ivWeather.setImageResource(R.drawable.moderate_to_heavy_snow);
                break;
            case "410":
                ivWeather.setImageResource(R.drawable.heavy_snow_to_snowstorm);
                break;
            case "499":
                ivWeather.setImageResource(R.drawable.snow);
                break;
            case "500":
                ivWeather.setImageResource(R.drawable.mist);
                break;
            case "501":
                ivWeather.setImageResource(R.drawable.foggy);
                break;
            case "502":
                ivWeather.setImageResource(R.drawable.haze);
                break;
            case "503":
                ivWeather.setImageResource(R.drawable.sand);
                break;
            case "504":
                ivWeather.setImageResource(R.drawable.dust);
                break;
            case "507":
                ivWeather.setImageResource(R.drawable.duststorm);
                break;
            case "508":
                ivWeather.setImageResource(R.drawable.sandstorm);
                break;
            case "509":
                ivWeather.setImageResource(R.drawable.dense_fog);
                break;
            case "510":
                ivWeather.setImageResource(R.drawable.strong_fog);
                break;
            case "511":
                ivWeather.setImageResource(R.drawable.moderate_haze);
                break;
            case "512":
                ivWeather.setImageResource(R.drawable.heavy_haze);
                break;
            case "513":
                ivWeather.setImageResource(R.drawable.severe_haze);
                break;
            case "514":
                ivWeather.setImageResource(R.drawable.heavy_fog);
                break;
            case "515":
                ivWeather.setImageResource(R.drawable.extra_heavy_fog);
                break;
            case "900":
                ivWeather.setImageResource(R.drawable.hot);
                break;
            case "901":
                ivWeather.setImageResource(R.drawable.cold);
                break;
            case "999":
                ivWeather.setImageResource(R.drawable.unknown);
                break;
        }
    }

    @Override
    public void setCityText(String city) {
        tvIndexCity.setText(city);
    }

    @OnClick(R2.id.index_calendar)
    public void openCalendarActivity(View view) {
        Intent intent = new Intent(getContext(),CalendarActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
    }

    @OnClick(R2.id.index_write_diary)
    public void openWriteDiray(View view){
        String auth_msg = SPUtils.get("auth_msg","");
        if ((!TextUtils.isEmpty(auth_msg) && auth_msg.equals("ok")) || !TextUtils.isEmpty(SPUtils.get("session_token",""))) {
            Intent intent = new Intent(getContext(),AddDiaryActivity.class);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        }else if (auth_msg.equals("fail")) {
            showToast("登录过期，请重新登录！");
            return;
        }else {
            showToast("请先登录");
            return;
        }


    }

}
