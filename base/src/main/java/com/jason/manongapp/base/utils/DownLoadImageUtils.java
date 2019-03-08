package com.jason.manongapp.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownLoadImageUtils {

    private static String HEAD_PICTURE_PATH;

    private static final String HEAD_PICTURE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download_img/";


    public static void downloadPic(final Context context, final String url){

        Fresco.getImagePipeline().fetchDecodedImage(getImageRequest(url),context).subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                boolean isSuccess = savaBitmapToSdCard(url,bitmap);
                if (isSuccess) {
                    Toast.makeText(context, "图片保存到"+HEAD_PICTURE_DIR+HEAD_PICTURE_PATH, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "图片保存失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

            }
        },CallerThreadExecutor.getInstance());
    }
    /**
     * 保存到sd卡
     * @param bitmap 图片
     * @return true 保存成功，false保存失败
     */
    private static boolean savaBitmapToSdCard(String url,Bitmap bitmap){
        if (url.endsWith(".gif")) {
            HEAD_PICTURE_PATH = getNowTime() + ".gif";
        }else if (url.endsWith(".jpg")){
            HEAD_PICTURE_PATH = getNowTime() + ".jpg";
        }else if (url.endsWith(".png")) {
            HEAD_PICTURE_PATH = getNowTime() + ".png";
        }
        if (bitmap == null) {
            return false;
        }
        File appDir = new File(HEAD_PICTURE_DIR);
        if (!appDir.exists()) {
            boolean isSuccessMkdir = appDir.mkdir();
            if (!isSuccessMkdir) {
                Logger.e("创建文件夹出错。");
            }
        }
        File file = new File(appDir,HEAD_PICTURE_PATH);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            if (HEAD_PICTURE_PATH.endsWith(".jpg")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            }else if (HEAD_PICTURE_PATH.endsWith(".png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            }else if (HEAD_PICTURE_PATH.endsWith(".gif")) {
//                getImage(url,HEAD_PICTURE_DIR+getNowTime()+".gif");
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            }
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Logger.e("文件不存在");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Logger.e("保存文件出错");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static ImageRequest getImageRequest(String url){
        return ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();
    }

    private static String getNowTime(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static void getImage(String path, String saveName) throws Exception {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000 * 6);
        if (con.getResponseCode() == 200) {
            InputStream inputStream = con.getInputStream();
            byte[] b = getByte(inputStream);
            File file = new File(saveName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(b);
            fileOutputStream.close();

        }
    }

    private static byte[] getByte(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

}
