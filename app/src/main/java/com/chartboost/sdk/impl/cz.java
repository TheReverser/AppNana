package com.chartboost.sdk.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class cz {
    private static final ThreadLocal<DateFormat> a = new ThreadLocal<DateFormat>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected DateFormat a() {
            DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat;
        }
    };
    private static final String[] b = new String[]{"EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    private static final DateFormat[] c = new DateFormat[b.length];

    public static Date a(String str) {
        try {
            return ((DateFormat) a.get()).parse(str);
        } catch (ParseException e) {
            synchronized (b) {
                int length = b.length;
                int i = 0;
                while (i < length) {
                    DateFormat dateFormat = c[i];
                    if (dateFormat == null) {
                        dateFormat = new SimpleDateFormat(b[i], Locale.US);
                        c[i] = dateFormat;
                    }
                    try {
                        return dateFormat.parse(str);
                    } catch (ParseException e2) {
                        i++;
                    }
                }
                return null;
            }
        }
    }

    public static String a(Date date) {
        return ((DateFormat) a.get()).format(date);
    }
}
