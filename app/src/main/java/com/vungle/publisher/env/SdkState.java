package com.vungle.publisher.env;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;
import com.vungle.log.Logger;
import com.vungle.publisher.aa;
import com.vungle.publisher.ab;
import com.vungle.publisher.ac;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.al;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.bf;
import com.vungle.publisher.bk;
import com.vungle.publisher.cm;
import com.vungle.publisher.co;
import com.vungle.publisher.cp;
import com.vungle.publisher.db.DatabaseBroadcastReceiver;
import com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.inject.annotations.EnvSharedPreferences;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.protocol.ProtocolHttpGateway.AnonymousClass5;
import com.vungle.publisher.protocol.ProtocolHttpGateway.AnonymousClass6;
import com.vungle.publisher.w;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class SdkState {
    @Inject
    AdManager a;
    @Inject
    Context b;
    @Inject
    DatabaseBroadcastReceiver c;
    @Inject
    bf d;
    @Inject
    public EventBus e;
    @Inject
    ExternalStorageStateBroadcastReceiver f;
    @Inject
    NetworkBroadcastReceiver g;
    @Inject
    ScheduledPriorityExecutor h;
    @Inject
    AdThrottleEndRunnable i;
    @Inject
    ProtocolHttpGateway j;
    @Inject
    public Lazy<EndAdEventListener> k;
    public final a l = new a();
    public final AtomicBoolean m = new AtomicBoolean();
    public long n;
    @Inject
    @EnvSharedPreferences
    public SharedPreferences o;
    private long p;
    private final AtomicInteger q = new AtomicInteger();

    @Singleton
    /* compiled from: vungle */
    static class AdThrottleEndRunnable implements Runnable {
        @Inject
        EventBus a;

        AdThrottleEndRunnable() {
        }

        public void run() {
            this.a.a(new ab());
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class EndAdEventListener extends bk {
        @Inject
        SdkState a;

        EndAdEventListener() {
        }

        public void onEvent(ac acVar) {
            Logger.d(Logger.EVENT_TAG, "SdkState received end ad event");
            this.a.b(true);
        }
    }

    /* compiled from: vungle */
    public static class a {
        public cp<Throwable> a = new cp(Throwable.class);

        a() {
        }
    }

    static /* synthetic */ boolean a(SdkState sdkState, int i) {
        if (!sdkState.q.compareAndSet(i, 0)) {
            return false;
        }
        sdkState.p = 0;
        return true;
    }

    public static boolean a() {
        boolean z = !TextUtils.isEmpty(cm.a("com.vungle.debug"));
        if (z) {
            Logger.d(Logger.AD_TAG, "in debug mode");
        } else {
            Logger.v(Logger.AD_TAG, "not in debug mode");
        }
        return z;
    }

    public final boolean b() {
        boolean z = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long c = c();
        int elapsedRealtime2 = (int) ((SystemClock.elapsedRealtime() - c()) / 1000);
        if (elapsedRealtime2 < 0) {
            Logger.d(Logger.AD_TAG, "negative adDelayElapsedSeconds " + elapsedRealtime2 + ", currentTimestampMillis " + elapsedRealtime + ", lastAdEndMillis " + c);
        } else {
            int e = e();
            if (elapsedRealtime2 < e) {
                z = false;
            }
            if (z) {
                Logger.v(Logger.AD_TAG, elapsedRealtime2 + " / " + e + " ad delay seconds elapsed");
            } else {
                Logger.d(Logger.AD_TAG, elapsedRealtime2 + " / " + e + " ad delay seconds elapsed");
            }
        }
        return z;
    }

    public final long c() {
        long j = this.o.getLong("VgLastViewedTime", 0);
        Logger.v(Logger.AD_TAG, "returning last ad end millis: " + j);
        return j;
    }

    public static long d() {
        return SystemClock.elapsedRealtime();
    }

    public final int e() {
        return this.o.getInt("VgAdDelayDuration", 0);
    }

    public final void a(boolean z) {
        Object obj;
        BroadcastReceiver broadcastReceiver = this.f;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme("file");
        broadcastReceiver.a.registerReceiver(broadcastReceiver, intentFilter);
        broadcastReceiver = this.g;
        broadcastReceiver.b.registerReceiver(broadcastReceiver, NetworkBroadcastReceiver.a);
        broadcastReceiver = this.c;
        broadcastReceiver.a.registerReceiver(broadcastReceiver, new IntentFilter("com.vungle.publisher.db.DUMP_TABLES"));
        this.d.p();
        this.h.a(b.sessionEnd);
        if (co.a(this.q)) {
            this.p = System.currentTimeMillis();
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            ProtocolHttpGateway protocolHttpGateway = this.j;
            protocolHttpGateway.d.a(new AnonymousClass6(protocolHttpGateway, this.p), b.sessionStart);
        }
        if (z) {
            this.e.a(new w());
        }
    }

    public final void b(boolean z) {
        if (this.m.compareAndSet(true, false)) {
            Logger.d(Logger.AD_TAG, "ending playing ad onResume()");
            ((EndAdEventListener) this.k.get()).d();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Logger.v(Logger.AD_TAG, "setting last ad end millis: " + elapsedRealtime);
            this.o.edit().putLong("VgLastViewedTime", elapsedRealtime).apply();
            this.n = 0;
            int e = e();
            if (e > 0) {
                this.e.a(new aa());
                this.h.a(this.i, (long) (e * 1000));
            }
            if (!z) {
                this.e.a(new al(this.n));
            }
        }
    }

    public final long f() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long j = this.p;
        final int i = this.q.get();
        BroadcastReceiver broadcastReceiver = this.f;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            Logger.w(Logger.DEVICE_TAG, "error unregistering external storage state broadcast receiver - not registered");
        }
        broadcastReceiver = this.g;
        try {
            broadcastReceiver.b.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e2) {
            Logger.w(Logger.NETWORK_TAG, "error unregistering network broadcast receiver - not registered");
        }
        broadcastReceiver = this.c;
        try {
            broadcastReceiver.a.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e3) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error unregistering database broadcast receiver - not registered");
        }
        this.h.a(new Runnable(this) {
            final /* synthetic */ SdkState d;

            public final void run() {
                try {
                    if (SdkState.a(this.d, i)) {
                        ProtocolHttpGateway protocolHttpGateway = this.d.j;
                        protocolHttpGateway.d.a(new AnonymousClass5(protocolHttpGateway, j, currentTimeMillis), b.sessionEnd);
                    }
                } catch (Throwable e) {
                    Logger.w(Logger.AD_TAG, "error sending session end", e);
                }
            }
        }, b.sessionEndTimer, 10000);
        return currentTimeMillis;
    }
}
