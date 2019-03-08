package com.jason.manongapp.more.register.api;


import com.jason.manongapp.more.register.bean.RegisterCallBackBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {


    @POST("https://api2.bmob.cn/1/users")
    Observable<RegisterCallBackBean> register(@Body RequestBody responseBody);

}
