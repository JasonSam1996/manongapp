package com.jason.manongapp.base.utils;

import android.app.Activity;

import com.jason.manongapp.base.R;

public class FinishUtils {

    public static void finishUtils(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.finish_in,R.anim.finish_to);

    }

}
