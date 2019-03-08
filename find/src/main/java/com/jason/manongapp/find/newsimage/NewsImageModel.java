package com.jason.manongapp.find.newsimage;


public class NewsImageModel implements NewsImageContract.Model {

    private NewsImageModel() {
    }

    private static class SingletonLoader {
        private static final NewsImageModel INSTANCE = new NewsImageModel();
    }

    public static NewsImageModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


    }

