package com.chartboost.sdk.Tracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.c;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.az;
import com.facebook.BuildConfig;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private static final String a = a.class.getSimpleName();
    private static a i;
    private static boolean j = false;
    private String b;
    private String c = p();
    private JSONArray d = new JSONArray();
    private long e;
    private long f;
    private long g = System.currentTimeMillis();
    private g h = new g("CBSessionDirectory", false);

    private a() {
    }

    public static a a() {
        if (i == null) {
            synchronized (Chartboost.class) {
                if (i == null) {
                    i = new a();
                }
            }
        }
        return i;
    }

    public static void b() {
        a("start");
        a("did-become-active");
    }

    public static void a(String str) {
        if (i != null) {
            i.a("session", str, null, null, null, null, "session");
        }
    }

    public void c() {
        a(false);
    }

    public void a(boolean z) {
        if (i != null) {
            com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
            try {
                a.a("complete", Boolean.valueOf(z));
                i.a("session", "end", null, null, null, null, a.e(), "session");
                a("did-become-active");
            } catch (Throwable e) {
                CBLogging.b(a, "error creating json", e);
            }
        }
    }

    public static void a(String str, String str2, boolean z) {
        if (i != null) {
            i.a("ad-get", str, str2, b(z), "single", null, "system");
        }
    }

    public static void b(String str, String str2, boolean z) {
        if (i != null) {
            i.a("ad-loaded", str, str2, b(z), null, null, "system");
        }
    }

    public static void a(String str, String str2, String str3) {
        if (i != null) {
            i.a("ad-show", str, str2, str3, null, null, "system");
        }
    }

    public static void b(String str, String str2, String str3) {
        if (i != null) {
            i.a("ad-click", str, str2, str3, null, null, "system");
        }
    }

    public static void a(String str, String str2, String str3, int i) {
        if (i != null) {
            i.a("ad-click", str, str2, str3, a(i), null, "system");
        }
    }

    public static void c(String str, String str2, String str3) {
        if (i != null) {
            i.a("ad-close", str, str2, str3, null, null, "system");
        }
    }

    public static void a(String str, String str2, CBImpressionError cBImpressionError) {
        if (i != null) {
            i.a("ad-error", str, str2, cBImpressionError != null ? cBImpressionError.toString() : BuildConfig.VERSION_NAME, null, null, "system");
        }
    }

    public static void d() {
        if (i != null) {
            i.a("video-prefetcher", "begin", null, null, null, null, "system");
        }
    }

    public static void e() {
        if (i != null) {
            i.a("video-prefetcher", null, null, null, null, null, "system");
        }
    }

    public static void a(String str, String str2) {
        if (i != null) {
            i.a("start", str, str2, null, null, null, "system");
        }
    }

    public static void a(String str, String str2, String str3, String str4) {
        if (i != null) {
            i.a("failure", str, str2, str3, str4, null, "system");
        }
    }

    public static void d(String str, String str2, String str3) {
        if (i != null) {
            i.a(GraphResponse.SUCCESS_KEY, str, str2, str3, null, null, "system");
        }
    }

    public static void e(String str, String str2, String str3) {
        if (i != null) {
            i.a(str, str2, str3, null, null, null, "system");
        }
    }

    public static void b(String str, String str2) {
        if (i != null) {
            i.a("confirmation-show", str, str2, null, null, null, "system");
        }
    }

    public static void c(String str, String str2, boolean z) {
        if (i != null) {
            i.a("confirmation-dismiss", str, str2, b(z), null, null, "system");
        }
    }

    public static void c(String str, String str2) {
        if (i != null) {
            i.a("replay", str, str2, null, null, null, "system");
        }
    }

    public static void a(String str, String str2, int i) {
        if (i != null) {
            i.a("playback-start", str, str2, a(i), null, null, "system");
        }
    }

    public static void d(String str, String str2) {
        if (i != null) {
            i.a("playback-stop", str, str2, null, null, null, "system");
        }
    }

    public static void b(String str, String str2, int i) {
        if (i != null) {
            i.a("close-show", str, str2, a(i), null, null, "system");
        }
    }

    public static void d(String str, String str2, boolean z) {
        if (i != null) {
            i.a("controls-toggle", str, str2, b(z), null, null, "system");
        }
    }

    public static void a(String str, String str2, String str3, int i, int i2) {
        if (i != null) {
            i.a("install-click", str, str3, str2, a(i), a(i2), "system");
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        if (i != null) {
            i.a(str, str2, str3, str4, str5, str6, jSONObject, "system");
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (i != null) {
            i.a(str, str2, str3, str4, str5, str6, new JSONObject(), str7);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, String str7) {
        JSONObject trackingLevels = CBPreferences.getInstance().getTrackingLevels();
        JSONObject jSONObject2 = new JSONObject();
        if (trackingLevels != null && trackingLevels.optBoolean(str7)) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.e;
            currentTimeMillis -= this.g;
            try {
                jSONObject2.put("event", a((Object) str));
                jSONObject2.put("kingdom", a((Object) str2));
                jSONObject2.put("phylum", a((Object) str3));
                jSONObject2.put("class", a((Object) str4));
                jSONObject2.put("family", a((Object) str5));
                jSONObject2.put("genus", a((Object) str6));
                jSONObject2.put("meta", a((Object) jSONObject));
                jSONObject2.put("clientTimestamp", System.currentTimeMillis() / 1000);
                jSONObject2.put("session_id", l());
                jSONObject2.put("totalSessionTime", j / 1000);
                jSONObject2.put("currentSessionTime", currentTimeMillis / 1000);
            } catch (Throwable e) {
                CBLogging.b(a, "error creating json", e);
            }
            synchronized (this) {
                this.d.put(jSONObject2);
                Object a = com.chartboost.sdk.Libraries.e.a.a();
                a.a("events", this.d);
                CBLogging.a(a, "###Writing" + a((Object) str) + "to tracking cache dir");
                this.h.a(this.c, com.chartboost.sdk.Libraries.e.a.a(a));
                k();
                if (f()) {
                    CBLogging.a(a, "### Got enough track events sending to server...");
                    this.c = p();
                    this.d = new JSONArray();
                    az.a(Chartboost.sharedChartboost().getContext()).d();
                }
            }
        }
    }

    public boolean f() {
        if (this.d == null || this.d.length() < 50) {
            return false;
        }
        return true;
    }

    public String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("startTime", System.currentTimeMillis());
            jSONObject.put("deviceID", c.e());
            return b.b(jSONObject.toString().getBytes());
        } catch (Throwable e) {
            Throwable th = e;
            String l = new Long(System.currentTimeMillis()).toString();
            CBLogging.b(a, "error json", th);
            return l;
        }
    }

    public void h() {
        com.chartboost.sdk.Libraries.e.a a = this.h.a("cb_previous_session_info");
        if (a != null) {
            this.f = a.h("timestamp");
            this.e = a.h("start_timestamp");
            this.b = a.e("session_id");
            if (System.currentTimeMillis() - this.f > 180000) {
                a(true);
            } else if (!TextUtils.isEmpty(this.b)) {
                k();
                j = false;
                return;
            }
        }
        j();
        j = true;
    }

    public static boolean i() {
        return j;
    }

    public void j() {
        long currentTimeMillis = System.currentTimeMillis();
        this.e = currentTimeMillis;
        this.f = currentTimeMillis;
        this.b = g();
        a(currentTimeMillis, currentTimeMillis);
        SharedPreferences a = CBUtility.a();
        int i = a.getInt("cbPrefSessionCount", 0) + 1;
        Editor edit = a.edit();
        edit.putInt("cbPrefSessionCount", i);
        edit.commit();
    }

    public void k() {
        a(this.e, System.currentTimeMillis());
    }

    public void a(long j, long j2) {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("start_timestamp", Long.valueOf(j));
        a.a("timestamp", Long.valueOf(j2));
        a.a("session_id", this.b);
        this.h.a("cb_previous_session_info", a);
    }

    public av a(com.chartboost.sdk.Libraries.e.a aVar) {
        av avVar = new av("/api/track", "https://live.chartboost.com", a.class.getSimpleName());
        avVar.a("track", (Object) aVar);
        avVar.a(f.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a)));
        avVar.a(com.chartboost.sdk.impl.k.a.LOW);
        return avVar;
    }

    public String toString() {
        return "Session [ startTime: " + o() + " sessionEvents: " + n() + " ]";
    }

    public String l() {
        return this.b;
    }

    public g m() {
        return this.h;
    }

    public JSONArray n() {
        return this.d;
    }

    public long o() {
        return this.e;
    }

    public String p() {
        return new Long(System.currentTimeMillis()).toString();
    }

    public static String b(boolean z) {
        return z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public static String a(int i) {
        return new Integer(i).toString();
    }

    private static Object a(Object obj) {
        return obj != null ? obj : BuildConfig.VERSION_NAME;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals("cb_previous_session_info");
    }
}
