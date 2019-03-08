package com.jason.manongapp.index.fragment.api;

import com.jason.manongapp.index.fragment.bean.CityLocationBean;
import com.jason.manongapp.index.fragment.bean.IndexBean;
import com.jason.manongapp.index.fragment.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IndexService {


    @GET("https://api2.bmob.cn/1/classes/Date_Image")
    Observable<IndexBean> getAllImage();

    @GET("https://free-api.heweather.net/s6/weather/now")
    Observable<WeatherBean> getWeather(@Query("location") String location, @Query("key") String key);


    @GET("http://api.map.baidu.com/geocoder")
    Observable<CityLocationBean> getCity(@Query("output") String output, @Query("location") String location,@Query("ak") String ak);


}
