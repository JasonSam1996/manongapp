package com.jason.manongapp.api;

import com.jason.manongapp.bean.AuthCallBack;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AuthService {

    @GET("https://api2.bmob.cn/1/checkSession/{objectid}")
    Observable<AuthCallBack> isAuthPastDue(@Path("objectid") String objectid);

}
