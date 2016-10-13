package com.vungle.publisher;

import android.content.ContentValues;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.internal.AnalyticsEvents;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.AdAvailabilityEventListener;
import com.vungle.publisher.ad.AdManager.AnonymousClass1;
import com.vungle.publisher.ar;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.at.a;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.Viewable.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.reporting.AdReportManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public abstract class VunglePubBase {
    public static final String VERSION = "VungleDroid/3.3.0";
    @Inject
    AdManager a;
    @Inject
    AsyncInitEventListener b;
    @Inject
    CacheManager c;
    @Inject
    DatabaseHelper d;
    @Inject
    Demographic e;
    @Inject
    protected bf f;
    @Inject
    EventBus g;
    @Inject
    AdConfig h;
    @Inject
    SafeBundleAdConfigFactory i;
    @Inject
    SdkConfig j;
    @Inject
    SdkState k;
    private boolean l;
    private boolean m;

    @Singleton
    /* compiled from: vungle */
    static class AsyncInitEventListener extends bk {
        @Inject
        AdManager a;
        @Inject
        ScheduledPriorityExecutor b;
        @Inject
        ProtocolHttpGateway c;
        @Inject
        AdReportManager d;
        @Inject
        SdkState e;
        private final ct g = new ct();

        AsyncInitEventListener() {
        }

        public void onEvent(bi biVar) {
            Logger.d(Logger.DEVICE_TAG, "device ID available");
            if (this.g.a(1) == 3) {
                a();
            }
        }

        public void onEvent(ar arVar) {
            Logger.d(Logger.DATABASE_TAG, "on database ready");
            if (this.g.a(0) == 3) {
                a();
            }
        }

        private void a() {
            d();
            this.b.a(new Runnable(this) {
                final /* synthetic */ AsyncInitEventListener a;

                {
                    this.a = r1;
                }

                public final void run() {
                    this.a.e.a(true);
                    this.a.c.a();
                    AdReportManager adReportManager = this.a.d;
                    if (adReportManager.e.o.getBoolean("IsVgAppInstalled", false)) {
                        Logger.v(Logger.REPORT_TAG, "install already reported");
                    } else {
                        Logger.d(Logger.REPORT_TAG, "reporting install");
                        adReportManager.d.c(new ci());
                    }
                    adReportManager.a();
                    AdManager adManager = this.a.a;
                    Factory factory = adManager.o;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, a.a.toString());
                    Logger.d(Logger.DATABASE_TAG, "updated " + factory.a.getWritableDatabase().updateWithOnConflict("viewable", contentValues, "status = ?", new String[]{a.b.toString()}, 3) + " " + a.b + " viewables to status " + a.a);
                    adManager.b(true);
                    ((AdAvailabilityEventListener) adManager.j.get()).b();
                }
            }, 2000);
        }
    }

    protected VunglePubBase() {
    }

    public boolean init(Context context, String vungleAppId) {
        boolean z = this.m;
        if (z) {
            try {
                Logger.d(Logger.VUNGLE_TAG, "already initialized");
                return z;
            } catch (Throwable e) {
                Logger.e(Logger.VUNGLE_TAG, "VunglePub initialization failed", e);
                return z;
            }
        }
        int i = VERSION.SDK_INT;
        Object obj = i >= 9 ? 1 : null;
        if (obj != null) {
            Logger.d(Logger.DEVICE_TAG, "Device Android API level " + i);
        } else {
            Logger.w(Logger.DEVICE_TAG, "Device Android API level " + i + " does not meet required minimum 9");
        }
        if (obj != null) {
            a(context, vungleAppId);
            if (this.j.b()) {
                Logger.i(Logger.VUNGLE_TAG, "VungleDroid/3.3.0 init(" + vungleAppId + ")");
                CacheManager cacheManager = this.c;
                Logger.d(Logger.FILE_TAG, "deleting old ad temp directory");
                CacheManager.a((String) cacheManager.b.get());
                this.b.b();
                DatabaseHelper databaseHelper = this.d;
                databaseHelper.d.a(new Runnable(databaseHelper) {
                    final /* synthetic */ DatabaseHelper a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        Logger.d(Logger.DATABASE_TAG, "initializing database vungle");
                        this.a.getWritableDatabase();
                        Logger.d(Logger.DATABASE_TAG, "done initializing database vungle");
                        this.a.b.a(new ar());
                    }
                }, b.databaseWrite);
                this.f.p();
                Logger.v(Logger.VUNGLE_TAG, "initialization successful");
                this.m = true;
                return true;
            }
            Logger.w(Logger.VUNGLE_TAG, "initialization failed");
        }
        return z;
    }

    private boolean a(boolean z, String str) {
        boolean z2 = this.m;
        if (z2) {
            Logger.v(Logger.VUNGLE_TAG, "VunglePub was initialized");
        } else if (z) {
            Logger.w(Logger.VUNGLE_TAG, "Please call VunglePub.init() before " + str);
        }
        return z2;
    }

    private boolean a() {
        boolean z = this.l;
        if (!z) {
            Logger.d(Logger.VUNGLE_TAG, "VunglePub not injected");
        }
        return z;
    }

    protected void a(Context context, String str) {
        if (this.l) {
            Logger.d(Logger.VUNGLE_TAG, "already injected");
            return;
        }
        Injector instance = Injector.getInstance();
        instance.a(context, str);
        instance.a.inject(this);
        Logger.d(Logger.VUNGLE_TAG, "injection successful");
        this.l = true;
    }

    public Demographic getDemographic() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error getting demographic info", e);
        }
        return this.e;
    }

    public void addEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                this.j.a(eventListeners);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error adding event listeners", e);
        }
    }

    public void setEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                SdkConfig sdkConfig = this.j;
                sdkConfig.a();
                sdkConfig.a(eventListeners);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error setting event listeners", e);
        }
    }

    public void clearEventListeners() {
        try {
            if (a()) {
                this.j.a();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error clearing event listeners", e);
        }
    }

    public void removeEventListeners(EventListener... eventListeners) {
        try {
            if (a()) {
                SdkConfig sdkConfig = this.j;
                if (eventListeners != null) {
                    for (Object obj : eventListeners) {
                        bm bmVar = (bm) sdkConfig.a.remove(obj);
                        if ((bmVar != null ? 1 : null) != null) {
                            Logger.d(Logger.CONFIG_TAG, "removing event listener " + obj);
                            bmVar.d();
                        } else {
                            Logger.d(Logger.CONFIG_TAG, "event listener not found for remove " + obj);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error removing event listeners", e);
        }
    }

    public AdConfig getGlobalAdConfig() {
        try {
            a();
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error getting globalAdConfig", e);
        }
        return this.h;
    }

    public void onResume() {
        try {
            if (a(false, "onResume()")) {
                SdkState sdkState = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityResume()");
                sdkState.a(true);
                sdkState.b(false);
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error onResume()", e);
        }
    }

    public void onPause() {
        try {
            if (a(false, "onPause()")) {
                SdkState sdkState = this.k;
                Logger.d(Logger.AD_TAG, "onDeveloperActivityPause()");
                sdkState.f();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error onPause()", e);
        }
    }

    public boolean isAdPlayable() {
        boolean z = false;
        try {
            if (a(true, "isAdPlayable()")) {
                z = this.a.a();
            }
        } catch (Throwable e) {
            Logger.e(Logger.VUNGLE_TAG, "error returning ad playable", e);
        }
        return z;
    }

    public void playAd() {
        playAd(null);
    }

    public void playAd(AdConfig adConfig) {
        Object obj = 1;
        Object obj2 = null;
        try {
            Logger.d(Logger.AD_TAG, "VunglePub.playAd()");
            if (a(true, "playAd()")) {
                SdkState sdkState = this.k;
                if (!sdkState.b()) {
                    sdkState.e.a(new ai((int) ((SdkState.d() - sdkState.c()) / 1000), sdkState.e()));
                    obj = null;
                } else if (!sdkState.m.compareAndSet(false, true)) {
                    Logger.d(Logger.AD_TAG, "ad already playing");
                    sdkState.e.a(new ae());
                    obj = null;
                }
                if (obj != null) {
                    ((EndAdEventListener) sdkState.k.get()).b();
                }
                obj2 = obj;
            } else if (this.l) {
                this.g.a(new ah());
            }
            if (obj2 != null) {
                AdManager adManager = this.a;
                adManager.f.a(new AnonymousClass1(adManager, this.i.merge(this.h, adConfig)), b.otherTask);
            }
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            if (this.l) {
                this.g.a(new ag());
            }
        }
    }
}
