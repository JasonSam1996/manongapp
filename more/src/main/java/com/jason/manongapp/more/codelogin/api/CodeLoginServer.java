package com.jason.manongapp.more.codelogin.api;

import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CodeLoginServer {

    @POST("https://api2.bmob.cn/1/requestSmsCode")
    Observable<SMSCodeBean> getCode(@Body RequestBody requestBody);

    @POST("https://api2.bmob.cn/1/users")
    Observable<SMSCodeCallBackBean> codeLogin(@Body RequestBody responseBody);

}
