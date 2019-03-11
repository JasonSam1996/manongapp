package com.jason.manongapp.find.fragment;

import com.jason.manongapp.find.bean.CommentBean;
import com.jason.manongapp.find.bean.NewsItemBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {


    @GET("https://news-at.zhihu.com/api/4/news/latest")
    Observable<ZhiHuNewNewsBean> getNewsItem();

    @GET("https://news-at.zhihu.com/api/4/story-extra/{newsId}")
    Observable<CommentBean> getComments(@Path("newsId") String newsId);

    @GET("https://news-at.zhihu.com/api/4/news/{newsId}")
    Observable<NewsItemBean> getNews(@Path("newsId") String newsId);

}
