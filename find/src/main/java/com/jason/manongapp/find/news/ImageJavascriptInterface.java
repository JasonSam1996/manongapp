package com.jason.manongapp.find.news;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.jason.manongapp.find.newsimage.NewsImageActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

public class ImageJavascriptInterface {

    private Activity context;

    public ImageJavascriptInterface(Activity context) {
        this.context = context;
    }

    @JavascriptInterface
    public void openImage(String imageUrl,String img) {
        int position = 0;
        String[] imgs = imageUrl.split(",");
        ArrayList<String> imgUrlList = new ArrayList<>();
        for (String s : imgs) {
            imgUrlList.add(s);
        }
        for (int i = 0;i<imgUrlList.size();i++){
            if (img.equals(imgUrlList.get(i))){
                position = i;
            }
        }
//        Logger.i("注入js成功");
        Intent intent = new Intent(context,NewsImageActivity.class);
        intent.putExtra("openImageAllNum",imgUrlList.size());
        intent.putExtra("openImagePosition",position);
        intent.putExtra("openImageList",imgUrlList);
        context.startActivity(intent);
    }

}
