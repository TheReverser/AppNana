package com.vungle.publisher;

import android.database.Cursor;
import android.database.SQLException;
import com.facebook.BuildConfig;
import com.vungle.sdk.VunglePub.Gender;

/* compiled from: vungle */
public final class aq {
    public static Boolean a(Cursor cursor, String str) throws SQLException {
        Integer d = d(cursor, str);
        if (d == null) {
            return null;
        }
        switch (d.intValue()) {
            case Gender.MALE /*0*/:
                return Boolean.FALSE;
            case Gender.FEMALE /*1*/:
                return Boolean.TRUE;
            default:
                throw new SQLException("invalid boolean value " + d + " for column " + str);
        }
    }

    public static <E extends Enum<E>> E a(Cursor cursor, String str, Class<E> cls) throws SQLException {
        try {
            return a(cursor, cursor.getColumnIndexOrThrow(str), (Class) cls);
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    private static <E extends Enum<E>> E a(Cursor cursor, int i, Class<E> cls) throws SQLException {
        String string = cursor.getString(i);
        try {
            return cursor.isNull(i) ? null : Enum.valueOf(cls, string);
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid enum: " + string);
        }
    }

    public static Float b(Cursor cursor, String str) throws SQLException {
        try {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
            return cursor.isNull(columnIndexOrThrow) ? null : Float.valueOf(cursor.getFloat(columnIndexOrThrow));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static int c(Cursor cursor, String str) throws SQLException {
        try {
            Integer a = a(cursor, cursor.getColumnIndexOrThrow(str));
            return a == null ? 0 : a.intValue();
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static Integer d(Cursor cursor, String str) throws SQLException {
        try {
            return a(cursor, cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    private static Integer a(Cursor cursor, int i) throws SQLException {
        return cursor.isNull(i) ? null : Integer.valueOf(cursor.getInt(i));
    }

    public static Long e(Cursor cursor, String str) throws SQLException {
        try {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(str);
            return cursor.isNull(columnIndexOrThrow) ? null : Long.valueOf(cursor.getLong(columnIndexOrThrow));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static String f(Cursor cursor, String str) throws SQLException {
        try {
            return cursor.getString(cursor.getColumnIndexOrThrow(str));
        } catch (IllegalArgumentException e) {
            throw new SQLException("invalid column name: " + str);
        }
    }

    public static String a(int i) {
        if (i <= 0) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder stringBuilder = new StringBuilder((i << 1) - 1);
        stringBuilder.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            stringBuilder.append(",?");
        }
        return stringBuilder.toString();
    }
}
