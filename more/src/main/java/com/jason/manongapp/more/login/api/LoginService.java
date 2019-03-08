package com.jason.manongapp.more.login.api;


import com.jason.manongapp.more.login.bean.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {


    @GET("https://api2.bmob.cn/1/login")
    Observable<UserInfo> login(@Query("username") String username, @Query("password") String password);

}
