package com.jason.manongapp.more.login.api;


import com.jason.manongapp.more.login.bean.UserInfo;
import com.jason.manongapp.more.register.bean.RegisterCallBackBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {


    @GET("https://api2.bmob.cn/1/login")
    Observable<UserInfo> login(@Query("username") String username, @Query("password") String password);

    @POST("https://api2.bmob.cn/1/users")
    Observable<RegisterCallBackBean> register(@Body RequestBody responseBody);

}
