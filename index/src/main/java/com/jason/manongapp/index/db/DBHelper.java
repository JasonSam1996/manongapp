package com.jason.manongapp.index.db;

import android.content.Context;

import butterknife.internal.Utils;

/**
 * DB操作
 */
public class DBHelper {

    private DaoMaster.DevOpenHelper mOpenHelper;

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    private static final String DBName = "index_date";

//    private

    private DBHelper() {
    }

    public void init(Context context){
        mOpenHelper = new DaoMaster.DevOpenHelper(context, DBName, null);
        mDaoMaster = new DaoMaster(mOpenHelper.getWritableDb());
        mDaoSession = mDaoMaster.newSession();
    }

    private static class SingletonLoader {
        private static final DBHelper INSTANCE = new DBHelper();
    }

    public static DBHelper getInstance() {
        return SingletonLoader.INSTANCE;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

}
