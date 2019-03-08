package com.jason.manongapp.diary.news;


public class AddDirayModel implements AddDiaryContract.Model {

    private AddDirayModel() {
    }

    private static class SingletonLoader {
        private static final AddDirayModel INSTANCE = new AddDirayModel();
    }

    public static AddDirayModel getInstance() {
        return SingletonLoader.INSTANCE;
    }


    }

