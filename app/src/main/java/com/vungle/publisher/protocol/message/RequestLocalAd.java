package com.vungle.publisher.protocol.message;

import android.util.Log;
import com.facebook.internal.NativeProtocol;
import com.vungle.log.Logger;
import com.vungle.publisher.bu;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.protocol.message.RequestAd.a;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestLocalAd extends RequestAd<RequestLocalAd> {
    public List<EventTrackingHttpLogEntry> g;
    HttpLogEntry[] h;
    String i;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestLocalAd> {
        @Inject
        com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory e;
        @Inject
        Factory f;
        @Inject
        Lazy<SdkState> g;

        protected final /* synthetic */ Object a() {
            return new RequestLocalAd();
        }

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new RequestLocalAd[i];
        }

        public final /* synthetic */ RequestAd b() {
            return c();
        }

        public final RequestLocalAd c() {
            int i;
            String str;
            HttpLogEntry[] httpLogEntryArr = null;
            int i2 = 0;
            RequestLocalAd requestLocalAd = (RequestLocalAd) super.b();
            Throwable[] thArr = (Throwable[]) ((SdkState) this.g.get()).l.a.a();
            int length = thArr.length;
            if (length > 0) {
                String[] strArr = new String[length];
                for (i = 0; i < length / 2; i++) {
                    String stackTraceString = Log.getStackTraceString(thArr[i]);
                    strArr[i] = Log.getStackTraceString(thArr[(length - 1) - i]);
                    strArr[(length - 1) - i] = stackTraceString;
                }
                String[] strArr2 = strArr;
            } else {
                Object[] objArr = null;
            }
            if (strArr2 == null || strArr2.length <= 0) {
                str = null;
            } else {
                str = strArr2[0];
            }
            requestLocalAd.i = str;
            List<EventTrackingHttpLogEntry> d = this.e.d();
            requestLocalAd.g = d;
            int size = d == null ? 0 : d.size();
            if (size > 0) {
                Logger.d(Logger.REPORT_TAG, "sending " + size + " event_tracking_http_log records");
                HttpLogEntry[] httpLogEntryArr2 = new HttpLogEntry[size];
                for (EventTrackingHttpLogEntry eventTrackingHttpLogEntry : d) {
                    HttpLogEntry httpLogEntry;
                    Logger.v(Logger.REPORT_TAG, "sending " + eventTrackingHttpLogEntry.y());
                    i = i2 + 1;
                    if (eventTrackingHttpLogEntry != null) {
                        HttpLogEntry httpLogEntry2 = new HttpLogEntry();
                        httpLogEntry2.a = eventTrackingHttpLogEntry.a;
                        httpLogEntry2.b = eventTrackingHttpLogEntry.b;
                        httpLogEntry2.c = Long.valueOf(eventTrackingHttpLogEntry.d);
                        httpLogEntry2.d = String.valueOf(eventTrackingHttpLogEntry.c);
                        httpLogEntry2.e = eventTrackingHttpLogEntry.e;
                        httpLogEntry2.f = eventTrackingHttpLogEntry.f;
                        httpLogEntry2.g = eventTrackingHttpLogEntry.g;
                        httpLogEntry = httpLogEntry2;
                    } else {
                        httpLogEntry = null;
                    }
                    httpLogEntryArr2[i2] = httpLogEntry;
                    i2 = i;
                }
                httpLogEntryArr = httpLogEntryArr2;
            }
            requestLocalAd.h = httpLogEntryArr;
            return requestLocalAd;
        }
    }

    /* compiled from: vungle */
    static class HttpLogEntry extends BaseJsonSerializable {
        String a;
        String b;
        Long c;
        String d;
        Integer e;
        Long f;
        String g;

        @Singleton
        /* compiled from: vungle */
        static class Factory extends MessageFactory<HttpLogEntry> {
            protected final /* synthetic */ Object a() {
                return new HttpLogEntry();
            }

            protected final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new HttpLogEntry[i];
            }

            @Inject
            Factory() {
            }
        }

        HttpLogEntry() {
        }

        public final JSONObject b() throws JSONException {
            JSONObject b = super.b();
            b.putOpt("campaignId", this.a);
            b.putOpt("deliveryId", this.b);
            b.putOpt("deviceMillis", this.c);
            b.putOpt("event", this.d);
            b.putOpt("responseCode", this.e);
            b.putOpt("responseMillis", this.f);
            b.putOpt(NativeProtocol.WEB_DIALOG_URL, this.g);
            return b;
        }
    }

    RequestLocalAd() {
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.putOpt("lastError", this.i);
        b.putOpt("httpLog", bu.a(this.h));
        return b;
    }
}
