package com.jason.manongapp.diary.news.api;


import com.jason.manongapp.diary.bean.CityLocationBean;
import com.jason.manongapp.diary.bean.SaveCallBack;
import com.jason.manongapp.diary.bean.UpLoadingCallBack;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UpLoadingService {


    @POST("https://api2.bmob.cn/2/files/{filename}")
    Observable<UpLoadingCallBack> upLoadingImage(@Header("Content-Type") String contentType, @Path("filename") String fileName, @Body RequestBody body);

    @GET("http://api.map.baidu.com/geocoder")
    Observable<CityLocationBean> getCity(@Query("output") String output, @Query("location") String location, @Query("ak") String ak);

    @POST("https://api2.bmob.cn/1/classes/diary")
    Observable<SaveCallBack> save(@Body RequestBody body);

}
