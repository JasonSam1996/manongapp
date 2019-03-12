package com.jason.manongapp.setting.api;


import com.jason.manongapp.setting.bean.SettingCallBackBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SettingService {

    @PUT("https://api2.bmob.cn/1/updateUserPassword/{objectId}")
    Observable<SettingCallBackBean> reSetPwd(@Path("objectId") String objectId, @Body RequestBody body);

}
