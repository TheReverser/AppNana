package com.appnana.android.utils;

import java.text.DecimalFormat;

public class Locale {
    public static String formatNumber(int number) {
        return new DecimalFormat(",###").format((long) number);
    }
}
