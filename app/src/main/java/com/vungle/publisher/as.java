package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.sdk.VunglePub.Gender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class as<I> implements av<I> {
    public Class<I> r = String.class;
    public I s;
    @Inject
    public DatabaseHelper t;

    /* compiled from: vungle */
    public static abstract class a<T extends as<I>, I> {
        @Inject
        public DatabaseHelper c;

        public abstract T a(T t, Cursor cursor);

        public abstract I[] b(int i);

        public abstract T[] c(int i);

        public abstract T c_();

        public abstract String e_();

        public final int a(I... iArr) {
            int i = 0;
            int length = iArr == null ? 0 : iArr.length;
            if (length == 0) {
                Logger.d(Logger.DATABASE_TAG, "no " + e_() + " records requested for delete");
                return 0;
            }
            int i2;
            c_();
            boolean z = iArr instanceof String[];
            String[] strArr = z ? (String[]) iArr : new String[length];
            if (!z) {
                int length2 = iArr.length;
                i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    strArr[i2] = String.valueOf(iArr[i]);
                    i++;
                    i2 = i3;
                }
            }
            i2 = this.c.getWritableDatabase().delete(e_(), as.v() + " IN (" + aq.a(length) + ")", strArr);
            if (i2 == length) {
                Logger.d(Logger.DATABASE_TAG, "deleted " + i2 + " " + e_() + " records by " + as.v() + " in " + cr.b(iArr));
                return i2;
            }
            Logger.w(Logger.DATABASE_TAG, "deleted " + i2 + " of " + length + " requested records by " + as.v() + " in " + cr.b(iArr));
            return i2;
        }

        public int a(List<T> list) {
            Object[] b;
            int i = 0;
            as[] asVarArr = list == null ? null : (as[]) list.toArray(c(list.size()));
            if (asVarArr != null) {
                b = b(asVarArr.length);
                int length = asVarArr.length;
                int i2 = 0;
                while (i2 < length) {
                    int i3 = i + 1;
                    b[i] = asVarArr[i2].s();
                    i2++;
                    i = i3;
                }
            } else {
                b = null;
            }
            return a(b);
        }

        public List<T> d() {
            return b(null, null, null);
        }

        public final T a(I i) {
            return a((Object) i, null, null);
        }

        public final T a(I i, String str, String[] strArr) {
            as c_ = c_();
            c_.a((Object) i);
            return a(c_, str, strArr);
        }

        final T a(T t, String str, String[] strArr) {
            if (t == null) {
                throw new IllegalArgumentException("null model");
            }
            String v = as.v();
            Object s = t.s();
            if (s == null) {
                throw new IllegalArgumentException("null " + v);
            }
            StringBuilder append = new StringBuilder().append(v).append(" = ?");
            Iterable arrayList = new ArrayList();
            arrayList.add(String.valueOf(s));
            if (str != null) {
                append.append(" AND ").append(str);
                if (strArr != null) {
                    arrayList.addAll(Arrays.asList(strArr));
                }
            }
            String stringBuilder = append.toString();
            List b = b(stringBuilder, (String[]) arrayList.toArray(new String[arrayList.size()]), null);
            int size = b.size();
            switch (size) {
                case Gender.MALE /*0*/:
                    return null;
                case Gender.FEMALE /*1*/:
                    return (as) b.get(0);
                default:
                    throw new SQLException(size + " " + e_() + " records found for query: " + stringBuilder + ", parameters: " + cr.a(arrayList));
            }
        }

        public final List<T> a(String str, String[] strArr, String str2) {
            return b(str, strArr, str2);
        }

        public final List<T> b(String str, String[] strArr, String str2) {
            Throwable th;
            Cursor cursor;
            try {
                String e_ = e_();
                Logger.d(Logger.DATABASE_TAG, "fetching " + (str == null ? "all " + e_ + " records" : e_ + " records by " + str + " " + cr.b(strArr)));
                Cursor query = this.c.getReadableDatabase().query(e_, null, str, strArr, null, null, str2, null);
                try {
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, (count == 0 ? "no " : "fetched " + count + " ") + e_ + " records by " + str + " " + cr.b(strArr));
                    List<T> a = a(query);
                    if (query != null) {
                        query.close();
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        public final boolean a(String str, String[] strArr) {
            boolean z = false;
            Cursor cursor = null;
            try {
                cursor = this.c.getWritableDatabase().rawQuery("SELECT EXISTS (SELECT 1 FROM " + e_() + " WHERE " + str + " LIMIT 1)", strArr);
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        public static void a(T... tArr) {
            if (tArr != null) {
                for (as r : tArr) {
                    r.r();
                }
            }
        }

        private List<T> a(Cursor cursor) {
            List<T> arrayList = new ArrayList(cursor.getCount());
            while (cursor.moveToNext()) {
                arrayList.add(b(c_(), cursor));
            }
            return arrayList;
        }

        public final T b(T t, Cursor cursor) {
            a((as) t, cursor);
            Logger.v(Logger.DATABASE_TAG, "fetched " + t);
            return t;
        }
    }

    public abstract ContentValues a(boolean z);

    public abstract <T extends as<I>> a<T, I> a_();

    public abstract String b();

    public static void a(StringBuilder stringBuilder, String str, Object obj) {
        a(stringBuilder, str, obj, false);
    }

    public static void a(StringBuilder stringBuilder, String str, Object obj, boolean z) {
        if (!z) {
            stringBuilder.append(", ");
        }
        stringBuilder.append(str).append(": ").append(obj);
    }

    public I s() {
        return this.s;
    }

    protected final void a(I i) {
        this.s = i;
    }

    protected static String v() {
        return ShareConstants.WEB_DIALOG_PARAM_ID;
    }

    public boolean f_() {
        return true;
    }

    public I r() {
        Object s = s();
        if (!f_() || s == null) {
            Logger.d(Logger.DATABASE_TAG, "inserting " + this);
            long insertOrThrow = this.t.getWritableDatabase().insertOrThrow(b(), null, a(true));
            if (this.r == null || Integer.class.equals(this.r)) {
                this.s = Integer.valueOf((int) insertOrThrow);
            } else if (Long.class.equals(this.r)) {
                this.s = Long.valueOf(insertOrThrow);
            }
            Logger.v(Logger.DATABASE_TAG, "inserted " + this);
            return s();
        }
        throw new SQLException("attempt to insert with non-auto generated id " + y());
    }

    public final void w() {
        a_().a(this, null, null);
    }

    public final I x() {
        I s = s();
        if (s == null) {
            return r();
        }
        m();
        return s;
    }

    public int m() {
        String str = ShareConstants.WEB_DIALOG_PARAM_ID;
        Object s = s();
        if (s == null) {
            throw new IllegalArgumentException("null " + str);
        }
        String b = b();
        String str2 = str + " " + s;
        int updateWithOnConflict = this.t.getWritableDatabase().updateWithOnConflict(b, a(false), "id = ?", new String[]{s.toString()}, 3);
        switch (updateWithOnConflict) {
            case Gender.MALE /*0*/:
                Logger.d(Logger.DATABASE_TAG, "no " + b + " rows updated by " + str2);
                break;
            case Gender.FEMALE /*1*/:
                Logger.d(Logger.DATABASE_TAG, "update successful " + y());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "updated " + updateWithOnConflict + " " + b + " records for " + str2);
                break;
        }
        return updateWithOnConflict;
    }

    public int n() {
        return a_().a(s());
    }

    public final String y() {
        return o().append('}').toString();
    }

    public StringBuilder o() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{').append(z()).append(":: ");
        a(stringBuilder, ShareConstants.WEB_DIALOG_PARAM_ID, s(), true);
        return stringBuilder;
    }

    public String z() {
        return b();
    }

    public String toString() {
        return p().append('}').toString();
    }

    public StringBuilder p() {
        return o();
    }
}
