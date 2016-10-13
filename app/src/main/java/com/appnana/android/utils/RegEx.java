package com.appnana.android.utils;

import java.util.regex.Pattern;

public class RegEx {
    public static boolean match(String reg, String text) {
        if (Pattern.compile(reg).matcher(text.toLowerCase()).matches()) {
            return true;
        }
        return false;
    }
}
