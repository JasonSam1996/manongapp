package com.jason.manongapp.find.news;


public class NewsModel implements NewsContract.Model {

    private NewsModel() {
    }

    private static class SingletonLoader {
        private static final NewsModel INSTANCE = new NewsModel();
    }

    public static NewsModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


    }

