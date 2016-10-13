package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.CBPreferences;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.Model.CBError;
import com.facebook.BuildConfig;
import com.facebook.internal.ServerProtocol;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

public abstract class ay {
    protected static com.chartboost.sdk.Libraries.e.a h = null;
    protected String a;
    protected String b;
    protected com.chartboost.sdk.Libraries.e.a c;
    protected Map<String, Object> d;
    protected Map<String, Object> e;
    protected Object f;
    protected String g;
    protected c i;
    protected boolean j;
    protected boolean k = false;
    protected boolean l = false;
    protected com.chartboost.sdk.Libraries.f.a m = null;
    protected CBPreferences n;
    protected String o;
    protected String p;
    protected az q;
    protected int r;
    protected boolean s = false;
    protected boolean t = true;
    protected com.chartboost.sdk.impl.k.a u = com.chartboost.sdk.impl.k.a.NORMAL;

    public interface c {
        void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, i iVar);

        void a(ay ayVar, CBError cBError, i iVar);
    }

    public static abstract class d implements c {
        public void a(ay ayVar, CBError cBError, i iVar) {
        }
    }

    protected static class a implements c {
        private d a;
        private b b;

        public a(d dVar, b bVar) {
            this.a = dVar;
            this.b = bVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, i iVar) {
            if (this.a != null) {
                this.a.a(aVar, ayVar, iVar);
            }
        }

        public void a(ay ayVar, CBError cBError, i iVar) {
            if (this.b != null) {
                this.b.a(ayVar, cBError, iVar);
            }
        }
    }

    public static abstract class b implements c {
    }

    public ay(String str, String str2, String str3) {
        this.a = str;
        this.o = str2;
        this.p = str3;
        this.g = "POST";
        this.n = CBPreferences.getInstance();
        this.q = az.a(Chartboost.sharedChartboost().getContext());
        this.j = false;
        this.r = 0;
    }

    protected void b() {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put("Accept", "application/json");
        this.e.put("X-Chartboost-Client", com.chartboost.sdk.Libraries.a.a(this.n));
        this.e.put("X-Chartboost-API", "3.4.0");
        this.e.put("X-Chartboost-Client", com.chartboost.sdk.Libraries.a.a(this.n));
        try {
            if (g() != null) {
                if (this.f == null) {
                    this.f = new HashMap();
                }
                StringEntity stringEntity = new StringEntity(g().toString());
                stringEntity.setContentType(new BasicHeader("Content-Type", "application/json"));
                this.f = stringEntity;
            }
        } catch (Throwable e) {
            CBLogging.b("CBRequest", "error setting up http headers", e);
        }
    }

    public void a(String str, Object obj) {
        if (this.c == null) {
            this.c = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.c.a(str, obj);
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put(str, str2);
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        if (aVar != null && aVar.c(str)) {
            a(str, aVar.e(str));
        }
    }

    protected void a(Context context) {
        Object obj = null;
        a("app", CBPreferences.getInstance().getAppID());
        if (ServerProtocol.DIALOG_PARAM_SDK_VERSION.equals(Build.PRODUCT)) {
            a("model", (Object) "Android Simulator");
        } else {
            a("model", Build.MODEL);
        }
        a("device_type", Build.MANUFACTURER + " " + Build.MODEL);
        a("os", "Android " + VERSION.RELEASE);
        a("country", Locale.getDefault().getCountry());
        a("language", Locale.getDefault().getLanguage());
        a(ServerProtocol.DIALOG_PARAM_SDK_VERSION, (Object) "4.1.1");
        a("timestamp", String.valueOf(Long.valueOf(new Date().getTime() / 1000).intValue()));
        a("session", Integer.valueOf(CBUtility.a().getInt("cbPrefSessionCount", 0)));
        int ordinal = ax.a().b().ordinal();
        if (ordinal != com.chartboost.sdk.impl.ax.b.CONNECTION_ERROR.ordinal()) {
            a("reachability", Integer.valueOf(ordinal));
        }
        b(context);
        a("scale", BuildConfig.VERSION_NAME + context.getResources().getDisplayMetrics().density);
        try {
            Object packageName = context.getPackageName();
            a("bundle", context.getPackageManager().getPackageInfo(packageName, 128).versionName);
            a("bundle_id", packageName);
        } catch (Throwable e) {
            CBLogging.b("CBRequest", "Exception raised getting package mager object", e);
        }
        if (h == null) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getPhoneType() != 0) {
                Object obj2;
                String simOperator = telephonyManager.getSimOperator();
                if (TextUtils.isEmpty(simOperator)) {
                    obj2 = null;
                } else {
                    obj2 = simOperator.substring(0, 3);
                    obj = simOperator.substring(3);
                }
                h = e.a(e.a("carrier-name", telephonyManager.getSimOperatorName()), e.a("mobile-country-code", obj2), e.a("mobile-network-code", obj), e.a("iso-country-code", telephonyManager.getNetworkCountryIso()), e.a("phone-type", Integer.valueOf(telephonyManager.getPhoneType())));
            } else {
                h = com.chartboost.sdk.Libraries.e.a.a();
            }
        }
        a("carrier", h);
        a("jb", Boolean.valueOf(k.a));
        a("custom-id", CBPreferences.getInstance().getCustomID());
    }

    public void c() {
        a("identity", com.chartboost.sdk.Libraries.c.b());
        com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
        if (c.b()) {
            a("tracking", Integer.valueOf(c.a()));
        }
    }

    public void d() {
        String appID = this.n.getAppID();
        String appSignature = this.n.getAppSignature();
        appSignature = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(String.format(Locale.US, "%s %s\n%s\n%s", new Object[]{this.g, e(), appSignature, f()}).getBytes()));
        a("X-Chartboost-App", appID);
        a("X-Chartboost-Signature", appSignature);
    }

    public String e() {
        String str = this.a == null ? "/" : this.a;
        return (str.startsWith("/") ? BuildConfig.VERSION_NAME : "/") + str + CBUtility.a(this.d);
    }

    public String f() {
        return this.c.toString();
    }

    public void b(Context context) {
        Throwable th;
        int i = 0;
        if (this.c == null || !this.c.a("w").c() || !this.c.a("h").c()) {
            int width;
            int height;
            Display defaultDisplay;
            try {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    Rect rect = new Rect();
                    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                    if (VERSION.SDK_INT < 9) {
                        rect.top = 0;
                    }
                    width = rect.width();
                    try {
                        height = rect.height();
                        i = width;
                    } catch (Throwable e) {
                        Throwable th2 = e;
                        height = width;
                        th = th2;
                        CBLogging.c("CBRequest", "Exception getting activity size", th);
                        width = height;
                        defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                        height = defaultDisplay.getWidth();
                        width = height;
                        height = defaultDisplay.getHeight();
                        i = height;
                        a("w", BuildConfig.VERSION_NAME + width);
                        a("h", BuildConfig.VERSION_NAME + i);
                    }
                }
                height = 0;
                width = i;
                i = height;
            } catch (Throwable e2) {
                th = e2;
                height = 0;
                CBLogging.c("CBRequest", "Exception getting activity size", th);
                width = height;
                defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                height = defaultDisplay.getWidth();
                width = height;
                height = defaultDisplay.getHeight();
                i = height;
                a("w", BuildConfig.VERSION_NAME + width);
                a("h", BuildConfig.VERSION_NAME + i);
            }
            defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            height = defaultDisplay.getWidth();
            if (width <= 0 || width > height) {
                width = height;
            }
            height = defaultDisplay.getHeight();
            if (i <= 0 || i > height) {
                i = height;
            }
            a("w", BuildConfig.VERSION_NAME + width);
            a("h", BuildConfig.VERSION_NAME + i);
        }
    }

    public com.chartboost.sdk.Libraries.e.a g() {
        return this.c;
    }

    public Map<String, Object> h() {
        return this.e;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public com.chartboost.sdk.Libraries.f.a i() {
        return this.m;
    }

    public void b(boolean z) {
        this.s = z;
    }

    public void a(com.chartboost.sdk.Libraries.f.a aVar) {
        if (f.c(aVar)) {
            this.m = aVar;
            return;
        }
        throw new IllegalArgumentException("Validation predicate must be a dictionary style -- either VDictionary, VDictionaryExact, VDictionaryWithValues, or just a list of KV pairs.");
    }

    public void a(f.k... kVarArr) {
        this.m = f.a(kVarArr);
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(com.chartboost.sdk.impl.k.a aVar) {
        this.u = aVar;
    }

    public void j() {
        a(null, null);
    }

    public void a(c cVar) {
        b();
        this.i = cVar;
        this.k = true;
        this.q.a(this, this.i);
    }

    public void a(d dVar, b bVar) {
        b();
        this.k = true;
        this.i = new a(dVar, bVar);
        this.q.a(this, this.i);
    }
}
