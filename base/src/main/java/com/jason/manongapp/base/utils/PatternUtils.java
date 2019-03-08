package com.jason.manongapp.base.utils;

import com.orhanobut.logger.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {

    /**
     * 正则表达式工具类
     */

    public static final String PHONEPE = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    public static boolean phonePattern(String phpne) {
        Pattern pattern = Pattern.compile(PHONEPE);
        Matcher matcher = pattern.matcher(phpne);
        return phpne != null && phpne.length() > 0 &&  matcher.matches();
    }

}
