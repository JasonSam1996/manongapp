package com.jason.manongapp.diary.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jason.manongapp.base.BaseApplication;
import com.jason.manongapp.diary.bean.EmojiBean;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 *describe: 表情数据库操作
 *author: Went_Gone
 *create on: 2016/10/27
 */
public class EmojiDao {
    private static final String TAG = "EmojiDao";
    private String path;
    private static EmojiDao dao;
    public static EmojiDao getInstance(Context context){
        if (dao == null){
            synchronized (EmojiDao.class){
                if (dao == null){
                    dao = new EmojiDao(context);
                }
            }
        }
        return dao;
    }
    private EmojiDao(Context context){
        try {
            path = CopySqliteFileFromRawToDatabases(context,"emoji.db");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EmojiBean> getEmojiBean(){
        List<EmojiBean> emojiBeanList = new ArrayList<EmojiBean>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.query("emoji", new String[]{"unicodeInt"}, null, null, null, null, null);
        while (cursor.moveToNext()){
            EmojiBean bean = new EmojiBean();
            int unicodeInt = cursor.getInt(0);
            bean.setUnicodeInt(unicodeInt);
            emojiBeanList.add(bean);
        }
        return emojiBeanList;
    }


    /**
     * 将assets目录下的文件拷贝到database中
     * @return 存储数据库的地址
     */
    public static String CopySqliteFileFromRawToDatabases(Context context,String SqliteFileName) throws IOException {
        // 第一次运行应用程序时，加载数据库到data/data/当前包的名称/database/<db_name>
        File dir = new File("data/data/" + context.getPackageName() + "/databases");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        File file= new File(dir, SqliteFileName);
        InputStream inputStream = null;
        OutputStream outputStream =null;
        //通过IO流的方式，将assets目录下的数据库文件，写入到SD卡中。
        if (!file.exists()) {
            try {
                file.createNewFile();
                inputStream = context.getClass().getClassLoader().getResourceAsStream("assets/" + SqliteFileName);
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len ;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return file.getPath();
    }
}
