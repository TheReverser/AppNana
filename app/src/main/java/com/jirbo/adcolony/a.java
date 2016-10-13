package com.jirbo.adcolony;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.facebook.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class a {
    static boolean A = d;
    static boolean B = d;
    static boolean C = true;
    static boolean D = false;
    static boolean E = false;
    static boolean F = d;
    static boolean G = false;
    static boolean H = false;
    static h I = null;
    static AdColonyAd J = null;
    static ADCVideo K = null;
    static ADCVideo L = null;
    static a M = null;
    static b N = null;
    static boolean O = false;
    static boolean P = false;
    static boolean Q = false;
    static boolean R = false;
    static int S = 0;
    static String T = null;
    static String U = null;
    static String V = null;
    static String W = null;
    static String X = null;
    static ArrayList<String> Y = new ArrayList();
    static c Z = new c();
    public static final boolean a = false;
    static boolean aa = false;
    static long ab = 0;
    static int ac = g;
    static ArrayList<Bitmap> ad = new ArrayList();
    static ArrayList<AdColonyV4VCListener> ae = new ArrayList();
    static ArrayList<AdColonyAdAvailabilityListener> af = new ArrayList();
    static ArrayList<AdColonyNativeAdView> ag = new ArrayList();
    static HashMap ah = null;
    private static Activity ai = null;
    public static final boolean b = false;
    public static final boolean c = false;
    public static final boolean d = false;
    public static String e = null;
    public static final String f = null;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    static final String k = "AdColony";
    static d l = new d();
    static boolean m;
    static int n = i;
    static boolean o;
    static boolean p;
    static boolean q;
    static boolean r;
    static boolean s;
    static boolean t;
    static boolean u = d;
    static boolean v = true;
    static int w = g;
    static double x = 1.0d;
    static boolean y = d;
    static boolean z = d;

    static class a extends Handler {
        AdColonyAd a;

        a() {
        }

        public void a(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.a = a.J;
            } else {
                this.a = adColonyAd;
            }
            sendMessage(obtainMessage(a.h));
        }

        public void b(AdColonyAd adColonyAd) {
            if (adColonyAd == null) {
                this.a = a.J;
            } else {
                this.a = adColonyAd;
            }
            sendMessage(obtainMessage(a.g));
        }

        public void handleMessage(Message m) {
            switch (m.what) {
                case a.g /*0*/:
                    a.a("skip", this.a);
                    if (a.J != null) {
                        a.J.f = a.h;
                        a.J.a();
                        return;
                    }
                    return;
                case a.h /*1*/:
                    g gVar = new g();
                    if (a.L.F.Q) {
                        gVar.b("html5_endcard_loading_started", a.L.k);
                    }
                    if (a.L.F.Q) {
                        gVar.b("html5_endcard_loading_finished", a.L.l);
                    }
                    if (a.L.F.Q) {
                        gVar.b("html5_endcard_loading_time", a.L.p);
                    }
                    if (a.L.F.Q) {
                        gVar.b("html5_endcard_loading_timeout", a.L.m);
                    }
                    if (a.L.q < 60000.0d) {
                        gVar.b("endcard_time_spent", a.L.q);
                    }
                    gVar.b("endcard_dissolved", a.L.n);
                    ADCVideo aDCVideo = a.L;
                    gVar.b("replay", ADCVideo.e);
                    gVar.b("reward", a.L.o);
                    a.l.d.a("continue", gVar, this.a);
                    a.l.b.e();
                    if (a.J != null) {
                        a.J.f = 4;
                        a.J.a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static class b extends Handler {
        b() {
        }

        public void handleMessage(Message m) {
            int i = a.g;
            String str = (String) m.obj;
            int i2 = m.what;
            boolean z = str != null ? true : a.d;
            if (!z) {
                str = BuildConfig.VERSION_NAME;
            }
            AdColonyV4VCReward adColonyV4VCReward = new AdColonyV4VCReward(z, str, i2);
            while (i < a.ae.size()) {
                ((AdColonyV4VCListener) a.ae.get(i)).onAdColonyV4VCReward(adColonyV4VCReward);
                i += a.h;
            }
        }

        public void a(boolean z, String str, int i) {
            if (!z) {
                str = null;
            }
            sendMessage(obtainMessage(i, str));
        }
    }

    a() {
    }

    static void a(Activity activity) {
        if (activity != ai && activity != null) {
            ai = activity;
            M = new a();
            N = new b();
            a aVar = new a();
        }
    }

    static void b(Activity activity) {
        p = d;
        a(activity);
        I = null;
        m = g.i();
        if (G) {
            G = d;
            o = d;
            l = new d();
        }
    }

    static boolean a() {
        if (ai == null) {
            return true;
        }
        return d;
    }

    static Activity b() {
        if (ai != null) {
            return ai;
        }
        throw new AdColonyException("AdColony.configure() must be called before any other AdColony methods. If you have called AdColony.configure(), the Activity reference you passed in via AdColony.configure() OR AdColony.resume() is null.");
    }

    static boolean c() {
        return (G || q || !o) ? true : d;
    }

    static boolean d() {
        return (!o || G || q) ? d : true;
    }

    static void a(String str) {
        G = true;
        e(str);
    }

    static void a(RuntimeException runtimeException) {
        G = true;
        e(runtimeException.toString());
        runtimeException.printStackTrace();
    }

    static void e() {
        b();
    }

    static void a(int i) {
        boolean z = d;
        n = i;
        l.a.f = i <= 0 ? true : d;
        l.b.f = i <= h ? true : d;
        l.c.f = i <= i ? true : d;
        l lVar = l.d;
        if (i <= j) {
            z = true;
        }
        lVar.f = z;
        if (i <= 0) {
            b("DEVELOPER LOGGING ENABLED");
        }
        if (i <= h) {
            c("DEBUG LOGGING ENABLED");
        }
    }

    static boolean b(int i) {
        return n <= i ? true : d;
    }

    static boolean f() {
        return n <= 0 ? true : d;
    }

    static boolean g() {
        return n <= h ? true : d;
    }

    static void a(int i, String str) {
        if (n <= i) {
            switch (i) {
                case g /*0*/:
                case h /*1*/:
                    Log.d(k, str);
                    return;
                case i /*2*/:
                    Log.i(k, str);
                    return;
                case j /*3*/:
                    Log.e(k, str);
                    return;
                default:
                    return;
            }
        }
    }

    static void b(String str) {
        a((int) g, str);
    }

    static void c(String str) {
        a((int) h, str);
    }

    static void d(String str) {
        a((int) i, str);
    }

    static void e(String str) {
        a((int) j, str);
    }

    static void f(String str) {
        Toast.makeText(b(), str, g).show();
    }

    static double g(String str) {
        return l.a(str);
    }

    static int h(String str) {
        return l.b(str);
    }

    static boolean i(String str) {
        return l.c(str);
    }

    static String j(String str) {
        return l.d(str);
    }

    static void k(String str) {
        l.a(str, null);
    }

    static void a(String str, String str2) {
        l.a(str, str2);
    }

    static void a(String str, AdColonyAd adColonyAd) {
        l.a(str, null, adColonyAd);
    }

    static void a(String str, String str2, AdColonyAd adColonyAd) {
        l.a(str, str2, adColonyAd);
    }

    static void h() {
        if (l != null && af.size() != 0 && ah != null) {
            for (Entry entry : ah.entrySet()) {
                boolean b;
                boolean b2;
                boolean booleanValue = ((Boolean) entry.getValue()).booleanValue();
                if (AdColony.isZoneV4VC((String) entry.getKey())) {
                    b = l.b((String) entry.getKey(), true, d);
                } else {
                    b = l.a((String) entry.getKey(), true, (boolean) d);
                }
                if (AdColony.isZoneNative((String) entry.getKey())) {
                    b2 = new AdColonyNativeAdView(b(), (String) entry.getKey(), 300, true).b(true);
                } else {
                    b2 = b;
                }
                if (booleanValue != b2) {
                    ah.put(entry.getKey(), Boolean.valueOf(b2));
                    for (int i = g; i < af.size(); i += h) {
                        ((AdColonyAdAvailabilityListener) af.get(i)).onAdColonyAdAvailabilityChange(b2, (String) entry.getKey());
                    }
                }
            }
        }
    }

    static void a(AdColonyNativeAdView adColonyNativeAdView) {
        ag.add(adColonyNativeAdView);
    }

    static void a(j jVar) {
        l.a(jVar);
    }
}
