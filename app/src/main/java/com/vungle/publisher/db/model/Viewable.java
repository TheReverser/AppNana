package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.at;
import com.vungle.publisher.at.a;
import com.vungle.publisher.at.b;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.sdk.VunglePub.Gender;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class Viewable<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends as<Integer> implements at<A, V, R> {
    protected String o;
    protected a p;
    protected b q;
    protected A u;

    /* compiled from: vungle */
    public static abstract class BaseFactory<A extends Ad<A, V, R>, W extends Viewable<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends as.a<W, Integer> {
        protected BaseFactory() {
        }

        protected W b(A a, R r) {
            if (r == null) {
                return null;
            }
            Viewable viewable = (Viewable) c_();
            viewable.u = a;
            viewable.o = r.f();
            viewable.p = a.aware;
            return viewable;
        }

        protected final W a(String str, b bVar, boolean z) throws SQLException {
            Viewable viewable = (Viewable) c_();
            viewable.o = str;
            viewable.q = bVar;
            return a(viewable, z);
        }

        private W a(W w, boolean z) throws SQLException {
            Throwable th;
            W w2 = null;
            Integer num = (Integer) w.s;
            b bVar = w.q;
            Cursor query;
            try {
                String str;
                String str2 = w.o;
                String str3;
                if (num != null) {
                    str3 = "id: " + num;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.c.getReadableDatabase().query("viewable", null, "id = ?", new String[]{String.valueOf(num)}, null, null, null);
                    str = str3;
                } else if (str2 == null) {
                    Logger.w(Logger.DATABASE_TAG, "unable to fetch " + bVar + ": no id or ad_id");
                    str = null;
                    query = null;
                } else {
                    str3 = "ad_id " + str2;
                    Logger.d(Logger.DATABASE_TAG, "fetching " + bVar + " by " + str3);
                    query = this.c.getReadableDatabase().query("viewable", null, "ad_id = ? AND type = ?", new String[]{str2, String.valueOf(bVar)}, null, null, null);
                    str = str3;
                }
                if (query != null) {
                    try {
                        int count = query.getCount();
                        switch (count) {
                            case Gender.MALE /*0*/:
                                Logger.v(Logger.DATABASE_TAG, "no " + bVar + " found for " + str);
                                break;
                            case Gender.FEMALE /*1*/:
                                Logger.d(Logger.DATABASE_TAG, "have " + bVar + " for " + str);
                                query.moveToFirst();
                                w2 = a((Viewable) w, query, z);
                                break;
                            default:
                                throw new SQLException(count + " " + bVar + " records for " + str);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                Logger.v(Logger.DATABASE_TAG, "fetched " + w2);
                return w2;
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }

        protected W a(W w, Cursor cursor, boolean z) {
            w.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            w.o = aq.f(cursor, "ad_id");
            w.p = (a) aq.a(cursor, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, a.class);
            w.q = (b) aq.a(cursor, "type", b.class);
            return w;
        }

        protected final String e_() {
            return "viewable";
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public DatabaseHelper a;
    }

    protected abstract com.vungle.publisher.db.model.Ad.Factory<A, V, R> D();

    public final /* bridge */ /* synthetic */ Object s() {
        return (Integer) this.s;
    }

    protected Viewable() {
    }

    protected final String b() {
        return "viewable";
    }

    public final Integer E() {
        return (Integer) this.s;
    }

    public final String d() {
        return this.o;
    }

    public final A c() {
        if (this.u == null) {
            this.u = (Ad) D().a((Object) this.o);
        }
        return this.u;
    }

    public final a e() {
        return this.p;
    }

    public final void a(a aVar) {
        Logger.v(Logger.PREPARE_TAG, "setting " + this.q + " status from " + this.p + " to " + aVar + " for ad_id: " + this.o);
        this.p = aVar;
    }

    public final b f() {
        return this.q;
    }

    protected ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_ID, (Integer) this.s);
            contentValues.put("ad_id", this.o);
            contentValues.put("type", this.q.toString());
        }
        contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, this.p.toString());
        return contentValues;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "ad_id", this.o, false);
        as.a(p, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, this.p, false);
        as.a(p, "type", this.q, false);
        return p;
    }

    protected final String z() {
        return String.valueOf(this.q);
    }
}
