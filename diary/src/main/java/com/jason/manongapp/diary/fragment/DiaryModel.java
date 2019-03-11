package com.jason.manongapp.diary.fragment;


import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.diary.bean.DiaryBean;

public class DiaryModel implements DiaryContract.Model {

    private DiaryModel() {
    }

    @Override
    public void getDiary(String userJson, final DiaryContract.Presenter presenter) {
        RxHttpUtils.createApi(ApiService.class)
                .getDiary(userJson)
                .compose(Transformer.<DiaryBean>switchSchedulers())
                .subscribe(new CommonObserver<DiaryBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        presenter.getDiaryError(errorMsg);
                    }

                    @Override
                    protected void onSuccess(DiaryBean diaryBean) {
                        if (diaryBean != null) {
                            presenter.getDiarySuccess(diaryBean);
                        }
                    }
                });
    }


    private static class SingletonLoader {
        private static final DiaryModel INSTANCE = new DiaryModel();
    }

    public static DiaryModel getInstance() {
        return SingletonLoader.INSTANCE;
    }

}
