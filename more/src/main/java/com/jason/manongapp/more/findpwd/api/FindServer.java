package com.jason.manongapp.more.findpwd.api;



import com.jason.manongapp.more.bean.SMSCodeBean;
import com.jason.manongapp.more.findpwd.bean.FindPwdCallBackBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FindServer {

    @POST("https://api2.bmob.cn/1/requestSmsCode")
    Observable<SMSCodeBean> getCode(@Body RequestBody requestBody);

    @PUT("https://api2.bmob.cn/1/resetPasswordBySmsCode/{code}")
    Observable<FindPwdCallBackBean> reSetPwd(@Path("code") String code,@Body RequestBody body);

}
