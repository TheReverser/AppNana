package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.as.a;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class AdReportExtra extends as<Integer> {
    Integer a;
    public String b;
    public String c;
    @Inject
    Factory d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<AdReportExtra, Integer> {
        @Inject
        Provider<AdReportExtra> a;

        protected final /* synthetic */ as a(as asVar, Cursor cursor) {
            AdReportExtra adReportExtra = (AdReportExtra) asVar;
            adReportExtra.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            adReportExtra.a = aq.d(cursor, "ad_report_id");
            adReportExtra.b = aq.f(cursor, ShareConstants.WEB_DIALOG_PARAM_NAME);
            adReportExtra.c = aq.f(cursor, "value");
            return adReportExtra;
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new AdReportExtra[i];
        }

        protected final /* synthetic */ as c_() {
            return b();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        final int a(Integer num) {
            int delete = this.c.getWritableDatabase().delete("ad_report_extra", "ad_report_id = ?", new String[]{String.valueOf(num)});
            Logger.v(Logger.DATABASE_TAG, "deleted " + delete + " ad_report_extra records for adReportId: " + num);
            return delete;
        }

        final Map<String, AdReportExtra> b(Integer num) {
            Throwable th;
            Cursor cursor = null;
            if (num == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch ad_report_extra records by ad_report_id " + num);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching ad_report_extra records by ad_report_id " + num);
                Cursor query = this.c.getReadableDatabase().query("ad_report_extra", null, "ad_report_id = ?", new String[]{String.valueOf(num)}, null, null, null);
                try {
                    Map<String, AdReportExtra> hashMap;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " ad_report_extra for ad_report_id " + num);
                    if (count > 0) {
                        hashMap = new HashMap();
                        while (query.moveToNext()) {
                            as b = b();
                            b(b, query);
                            hashMap.put(b.b, b);
                        }
                    } else {
                        hashMap = null;
                    }
                    if (query == null) {
                        return hashMap;
                    }
                    query.close();
                    return hashMap;
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
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected final String e_() {
            return "ad_report_extra";
        }

        final AdReportExtra b() {
            return (AdReportExtra) this.a.get();
        }

        protected static AdReportExtra[] a(int i) {
            return new AdReportExtra[i];
        }
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.d;
    }

    protected final String b() {
        return "ad_report_extra";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_report_id", this.a);
        }
        contentValues.put(ShareConstants.WEB_DIALOG_PARAM_NAME, this.b);
        contentValues.put("value", this.c);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "ad_report_id", this.a, false);
        as.a(p, ShareConstants.WEB_DIALOG_PARAM_NAME, this.b, false);
        as.a(p, "value", this.c, false);
        return p;
    }
}
