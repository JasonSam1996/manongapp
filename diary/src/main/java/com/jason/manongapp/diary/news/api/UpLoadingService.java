package com.jason.manongapp.diary.news.api;


import com.jason.manongapp.diary.bean.UpLoadingCallBack;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UpLoadingService {


    @POST("https://api2.bmob.cn/2/files/{filename}")
    Observable<UpLoadingCallBack> upLoadingImage(@Header("Content-Type") String contentType, @Path("filename") String fileName, @Body RequestBody body);

}
