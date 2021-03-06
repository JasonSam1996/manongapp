package com.jason.manongapp.index.calendar.api;


import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

//    @GET("news/latest")
//    Observable<ZhiHuNewNewsBean.StoriesBean> getStories();

    @GET("https://news-at.zhihu.com/api/4/news/latest")
    Observable<ZhiHuNewNewsBean> getNewsItem();

    @GET("https://news-at.zhihu.com/api/4/news/{newsId}")
    Observable<NewsItemBean> getNews(@Path("newsId") String newsId);

}
