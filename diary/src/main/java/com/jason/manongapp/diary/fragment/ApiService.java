package com.jason.manongapp.diary.fragment;


import com.jason.manongapp.diary.bean.DiaryBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("https://api2.bmob.cn/1/classes/diary")
    Observable<DiaryBean> getDiary(@Query("where") String userJson);

}
