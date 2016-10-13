package com.chartboost.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.c;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.Libraries.l;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.ab;
import com.chartboost.sdk.impl.as;
import com.chartboost.sdk.impl.av;
import com.chartboost.sdk.impl.ax;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.d;
import com.chartboost.sdk.impl.az;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.i;
import com.facebook.BuildConfig;
import com.facebook.internal.AnalyticsEvents;
import java.util.Locale;
import org.json.JSONObject;

public final class Chartboost {
    private static volatile Chartboost c = null;
    private static volatile boolean q = false;
    protected l a = null;
    protected Handler b;
    private Context d = null;
    private CBImpressionActivity e = null;
    private com.chartboost.sdk.Model.a f = null;
    private CBPreferences g = null;
    private ax h = null;
    private az i = null;
    private com.chartboost.sdk.impl.l j = null;
    private com.chartboost.sdk.Tracking.a k = null;
    private boolean l = false;
    private boolean m = false;
    private SparseBooleanArray n = new SparseBooleanArray();
    private c o = null;
    private boolean p = false;
    private Runnable r = new b();

    public interface a {
        void a();
    }

    public interface CBAPIResponseCallback {
        void onFailure(CBImpressionError cBImpressionError);

        void onSuccess(JSONObject jSONObject);
    }

    public interface CBAgeGateConfirmation {
        void execute(boolean z);
    }

    private class b implements Runnable {
        final /* synthetic */ Chartboost a;
        private int b;
        private int c;
        private int d;

        private ChartboostDelegate a() {
            return this.a.g != null ? this.a.g.getDelegate() : null;
        }

        private b(Chartboost chartboost) {
            int i = -1;
            this.a = chartboost;
            ChartboostDelegate a = a();
            this.b = chartboost.e == null ? -1 : chartboost.e.hashCode();
            this.c = chartboost.a == null ? -1 : chartboost.a.hashCode();
            if (a != null) {
                i = a.hashCode();
            }
            this.d = i;
        }

        public void run() {
            ChartboostDelegate a = a();
            if (this.a.getContext() != null) {
                this.a.clearCache();
            }
            if (this.a.a != null && this.a.a.hashCode() == this.c) {
                this.a.a = null;
            }
            if (this.a.e != null && this.a.e.hashCode() == this.b) {
                this.a.e = null;
            }
            if (a != null && a.hashCode() == this.d) {
                this.a.g.setDelegate(null);
            }
        }
    }

    private Chartboost() {
        c = this;
        this.b = new Handler();
        this.h = ax.a();
        this.o = c.a(this);
        this.g = CBPreferences.getInstance();
    }

    public static Chartboost sharedChartboost() {
        if (c == null) {
            synchronized (Chartboost.class) {
                if (c == null) {
                    c = new Chartboost();
                }
            }
        }
        return c;
    }

    public CBPreferences getPreferences() {
        return this.g;
    }

    public void startSession() {
    }

    public Context getContext() {
        return this.d;
    }

    public static boolean isSessionStarted() {
        return q;
    }

    private static void a(boolean z) {
        q = z;
    }

    public void onCreate(Activity activity, String appId, String appSignature, ChartboostDelegate chartBoostDelegate) {
        a(activity, appId, appSignature, chartBoostDelegate);
    }

    private void a(Activity activity, String str, String str2, ChartboostDelegate chartboostDelegate) {
        a("onCreate()");
        if (!(this.a == null || this.a.b(activity) || !f())) {
            e(this.a);
            a(this.a, false);
        }
        this.b.removeCallbacks(this.r);
        this.a = l.a(activity);
        this.d = activity.getApplicationContext();
        if (!this.l) {
            this.h.a(this.d);
            this.l = true;
        }
        c.a();
        this.g.setAppID(str);
        this.g.setAppSignature(str2);
        this.g.setDelegate(chartboostDelegate);
        if (k.b) {
            k.a();
        }
        if (this.i == null) {
            this.i = az.a(this.d);
            this.j = this.i.a();
        }
    }

    private boolean f() {
        return a(this.a);
    }

    protected boolean a(l lVar) {
        if (lVar == null) {
            return false;
        }
        Boolean valueOf = Boolean.valueOf(this.n.get(lVar.a()));
        if (valueOf != null) {
            return valueOf.booleanValue();
        }
        return false;
    }

    private void a(Activity activity, boolean z) {
        if (activity != null) {
            a(activity.hashCode(), z);
        }
    }

    private void a(l lVar, boolean z) {
        if (lVar != null) {
            a(lVar.a(), z);
        }
    }

    private void a(int i, boolean z) {
        this.n.put(i, z);
    }

    private void b(l lVar, boolean z) {
    }

    protected c a() {
        if (c() == null) {
            return null;
        }
        return this.o;
    }

    public void onStart(Activity activity) {
        a("onStart()");
        this.b.removeCallbacks(this.r);
        if (!(this.a == null || this.a.b(activity) || !f())) {
            e(this.a);
            a(this.a, false);
        }
        a(activity, true);
        this.a = l.a(activity);
        this.d = activity.getApplicationContext();
        if (!this.g.getImpressionsUseActivities()) {
            a(activity);
        }
        this.h.b(this.d);
        this.j.a();
        this.i.e();
        g();
    }

    protected void a(Activity activity) {
        this.d = activity.getApplicationContext();
        if (activity instanceof CBImpressionActivity) {
            g();
            a((CBImpressionActivity) activity);
        } else {
            this.a = l.a(activity);
            a(this.a, true);
        }
        this.b.removeCallbacks(this.r);
        if (activity != null && c(activity)) {
            b(l.a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                this.p = false;
            }
            a.a().a(activity, this.f);
            this.f = null;
        }
    }

    private void g() {
        if (this.d == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling startSession().");
        }
        a(true);
        if (this.k == null) {
            this.k = com.chartboost.sdk.Tracking.a.a();
        }
        this.k.h();
        com.chartboost.sdk.Tracking.a.b();
        final boolean i = com.chartboost.sdk.Tracking.a.i();
        this.g.a(new a(this) {
            final /* synthetic */ Chartboost b;

            public void a() {
                if (i) {
                    av avVar = new av("api/install", null, "main");
                    avVar.b(Chartboost.c.getValidContext());
                    avVar.a(true);
                    avVar.a(f.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, com.chartboost.sdk.Libraries.a.a));
                    avVar.a(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(com.chartboost.sdk.Libraries.e.a aVar, ay ayVar, i iVar) {
                            if (CBUtility.a(this.a.b.getContext())) {
                                Object e = aVar.e("latest-sdk-version");
                                if (!TextUtils.isEmpty(e) && !e.equals("4.1.1")) {
                                    CBLogging.a(String.format(Locale.US, "Chartboost SDK is not up to date. (Current: %s, Latest: %s)\n Download latest SDK at:\n\thttps://www.chartboost.com/support/sdk_download/?os=ios", new Object[]{"4.1.1", e}));
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    protected void b(l lVar) {
        com.chartboost.sdk.Model.a c = a.a().c();
        if (c != null) {
            c.q();
        }
    }

    protected void c(l lVar) {
        com.chartboost.sdk.Model.a c = a.a().c();
        if (c != null) {
            c.r();
        }
    }

    public void onStop(Activity activity) {
        a("onStop()");
        l a = l.a(activity);
        if (a(a)) {
            e(a);
        }
    }

    private void e(l lVar) {
        if (!this.g.getImpressionsUseActivities()) {
            d(lVar);
        }
        if (!(lVar.get() instanceof CBImpressionActivity)) {
            a(lVar, false);
        }
        this.h.c(this.d);
        this.j.b();
        this.i.f();
        if (this.k == null) {
            this.k = com.chartboost.sdk.Tracking.a.a();
        }
        this.k.c();
    }

    protected void d(l lVar) {
        c a = a();
        if (f(lVar) && a != null) {
            com.chartboost.sdk.Model.a c = a.a().c();
            if (c != null) {
                a.b(c);
                this.f = c;
            }
            b(lVar, false);
            if (lVar.get() instanceof CBImpressionActivity) {
                d();
            }
        }
        if (!(lVar.get() instanceof CBImpressionActivity)) {
            a(lVar, false);
        }
    }

    public boolean onBackPressed() {
        a("onBackPressed()");
        if (this.a == null) {
            throw new IllegalStateException("The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
        } else if (!this.g.getImpressionsUseActivities()) {
            return b();
        } else {
            if (!this.p) {
                return false;
            }
            this.p = false;
            b();
            return true;
        }
    }

    protected boolean b() {
        final a a = a.a();
        com.chartboost.sdk.Model.a c = a.c();
        if (c == null || c.c != com.chartboost.sdk.Model.a.b.DISPLAYED) {
            final c a2 = a();
            if (a2 == null || !a2.a()) {
                return false;
            }
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    a2.a(true);
                }
            });
            return true;
        } else if (c.p()) {
            return true;
        } else {
            a(new Runnable(this) {
                final /* synthetic */ Chartboost b;

                public void run() {
                    a.b();
                }
            });
            return true;
        }
    }

    public void onDestroy(Activity activity) {
        if (this.a == null || this.a.b(activity)) {
            this.b.removeCallbacks(this.r);
            this.r = new b();
            this.b.postDelayed(this.r, 10000);
        }
        b(activity);
    }

    protected void b(Activity activity) {
        b(l.a(activity), false);
    }

    private void a(String str) {
        if (!this.g.getIgnoreErrors() && !CBUtility.b()) {
            throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the " + str + " method of your host activity.");
        }
    }

    public void clearImageCache() {
    }

    public void clearCache() {
        if (getContext() == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling clearImageCache().");
        }
        bb.a().b();
        ab.e().a();
        as.e().a();
    }

    public void cacheInterstitial() {
        cacheInterstitial("Default");
    }

    public void cacheInterstitial(String location) {
        if (this.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheInterstitial().");
        }
        ab.e().b(location);
    }

    public void showInterstitial() {
        showInterstitial("Default");
    }

    public void showInterstitial(String location) {
        if (this.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showInterstitial().");
        }
        ab.e().a(location);
    }

    public boolean hasCachedInterstitial() {
        return hasCachedInterstitial("Default");
    }

    public boolean hasCachedMoreApps() {
        return as.e().h();
    }

    public boolean hasCachedInterstitial(String location) {
        return ab.e().c(location);
    }

    public void cacheMoreApps() {
        if (this.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheMoreApps().");
        }
        as.e().g();
    }

    public void showMoreApps() {
        if (this.a == null) {
            throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showMoreApps().");
        }
        as.e().f();
    }

    public void showMoreAppsData(CBAPIResponseCallback callback) {
        e.a(callback);
    }

    public String getDeviceIdentifier() {
        return c.b();
    }

    private void cacheInterstitialDataBatch(String location, int amount, CBAPIResponseCallback callback) {
        e.a(location, amount, callback);
    }

    private void cacheInterstitialData(String location, CBAPIResponseCallback callback) {
        e.a(location, callback);
    }

    private void showInterstitialData(String ad_id, CBAPIResponseCallback callback) {
        e.b(ad_id, callback);
    }

    protected Activity c() {
        if (this.g.getImpressionsUseActivities()) {
            return this.e;
        }
        return getHostActivity();
    }

    private boolean c(Activity activity) {
        if (this.g.getImpressionsUseActivities()) {
            if (this.e == activity) {
                return true;
            }
            return false;
        } else if (this.a != null) {
            return this.a.b(activity);
        } else {
            if (activity != null) {
                return false;
            }
            return true;
        }
    }

    private boolean f(l lVar) {
        if (this.g.getImpressionsUseActivities()) {
            if (lVar != null) {
                return lVar.b(this.e);
            }
            if (this.e == null) {
                return true;
            }
            return false;
        } else if (this.a != null) {
            return this.a.a(lVar);
        } else {
            if (lVar != null) {
                return false;
            }
            return true;
        }
    }

    protected void a(CBImpressionActivity cBImpressionActivity) {
        if (!this.m) {
            this.d = cBImpressionActivity.getApplicationContext();
            this.e = cBImpressionActivity;
            this.m = true;
        }
        this.b.removeCallbacks(this.r);
    }

    protected void d() {
        if (this.m) {
            this.e = null;
            this.m = false;
        }
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        boolean z = true;
        c a;
        if (!this.g.getImpressionsUseActivities()) {
            a = a();
            if (a == null || !f()) {
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else {
                a.a(aVar);
            }
        } else if (this.m) {
            a = a();
            if (c() == null || a == null) {
                CBLogging.b("Chartboost", "Missing CBViewController to manage the open CBImpressionActivity");
            } else {
                a.a(aVar);
            }
        } else if (f()) {
            Context hostActivity = getHostActivity();
            if (hostActivity == null) {
                CBLogging.b("Chartboost", "Failed to display impression as the host activity reference has been lost!");
                aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
            } else if (this.f == null || this.f == aVar) {
                this.f = aVar;
                Intent intent = new Intent(hostActivity, CBImpressionActivity.class);
                boolean z2 = (hostActivity.getWindow().getAttributes().flags & 1024) != 0;
                boolean z3;
                if ((hostActivity.getWindow().getAttributes().flags & 2048) != 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                String str = "paramFullscreen";
                if (!z2 || r3) {
                    z = false;
                }
                intent.putExtra(str, z);
                try {
                    hostActivity.startActivity(intent);
                    this.p = true;
                } catch (ActivityNotFoundException e) {
                    throw new RuntimeException("Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
                }
            } else {
                aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        } else {
            aVar.a(CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    protected Activity getHostActivity() {
        return this.a != null ? (Activity) this.a.get() : null;
    }

    protected void a(Runnable runnable) {
        if (CBUtility.b()) {
            runnable.run();
        } else {
            this.b.post(runnable);
        }
    }

    protected Context getValidContext() {
        return this.a != null ? this.a.b() : getContext();
    }

    @Deprecated
    public void setFramework(String framework) {
        if (TextUtils.isEmpty(framework)) {
            this.g.setUserAgentSuffix(null);
        } else if (framework.startsWith("-")) {
            this.g.setUserAgentSuffix(framework);
        } else {
            this.g.setUserAgentSuffix("-" + framework.substring(0, 1).toUpperCase(Locale.US) + (framework.length() > 1 ? framework.substring(1) : BuildConfig.VERSION_NAME));
        }
    }
}
