package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.vungle.publisher.aq;
import com.vungle.publisher.as;
import com.vungle.publisher.db.model.EventTracking.a;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class EventTrackingHttpLogEntry extends as<Integer> {
    public String a;
    public String b;
    public a c;
    public long d;
    public Integer e;
    public Long f;
    public String g;
    @Inject
    Factory h;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends as.a<EventTrackingHttpLogEntry, Integer> {
        @Inject
        Provider<EventTrackingHttpLogEntry> a;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* synthetic */ as a(as asVar, Cursor cursor) {
            EventTrackingHttpLogEntry eventTrackingHttpLogEntry = (EventTrackingHttpLogEntry) asVar;
            eventTrackingHttpLogEntry.a = aq.f(cursor, "ad_id");
            eventTrackingHttpLogEntry.b = aq.f(cursor, "delivery_id");
            eventTrackingHttpLogEntry.c = (a) aq.a(cursor, "event", a.class);
            eventTrackingHttpLogEntry.s = aq.d(cursor, ShareConstants.WEB_DIALOG_PARAM_ID);
            eventTrackingHttpLogEntry.d = aq.e(cursor, "insert_timestamp_millis").longValue();
            eventTrackingHttpLogEntry.e = aq.d(cursor, "response_code");
            eventTrackingHttpLogEntry.f = aq.e(cursor, "response_timestamp_millis");
            eventTrackingHttpLogEntry.g = aq.f(cursor, NativeProtocol.WEB_DIALOG_URL);
            return eventTrackingHttpLogEntry;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ as[] c(int i) {
            return new EventTrackingHttpLogEntry[i];
        }

        protected final /* synthetic */ as c_() {
            return b();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        Factory() {
        }

        protected final String e_() {
            return "event_tracking_http_log";
        }

        public final EventTrackingHttpLogEntry b() {
            return (EventTrackingHttpLogEntry) this.a.get();
        }
    }

    protected final /* bridge */ /* synthetic */ as.a a_() {
        return this.h;
    }

    EventTrackingHttpLogEntry() {
    }

    protected final String b() {
        return "event_tracking_http_log";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(ShareConstants.WEB_DIALOG_PARAM_ID, (Integer) s());
            long currentTimeMillis = System.currentTimeMillis();
            this.d = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
        }
        contentValues.put("ad_id", this.a);
        contentValues.put("delivery_id", this.b);
        contentValues.put("event", this.c.toString());
        contentValues.put("response_code", this.e);
        contentValues.put("response_timestamp_millis", this.f);
        contentValues.put(NativeProtocol.WEB_DIALOG_URL, this.g);
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        as.a(p, "ad_id", this.a, false);
        as.a(p, "delivery_id", this.b, false);
        as.a(p, "event", this.c, false);
        as.a(p, "response_code", this.e, false);
        as.a(p, "response_timestamp_millis", this.f, false);
        as.a(p, NativeProtocol.WEB_DIALOG_URL, this.g, false);
        as.a(p, "insert_timestamp_millis", Long.valueOf(this.d), false);
        return p;
    }
}
