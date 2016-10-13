package com.vungle.publisher.event;

import com.vungle.log.Logger;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.ad;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ae;
import com.vungle.publisher.ag;
import com.vungle.publisher.ah;
import com.vungle.publisher.ai;
import com.vungle.publisher.aj;
import com.vungle.publisher.al;
import com.vungle.publisher.ao;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.bk;
import com.vungle.publisher.d;
import com.vungle.publisher.k;
import com.vungle.publisher.l;
import com.vungle.publisher.x;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class ClientEventListenerAdapter extends bk {
    public EventListener a;
    @Inject
    ScheduledPriorityExecutor b;
    @Inject
    AdManager c;
    private int d;
    private int e;
    private AtomicBoolean g = new AtomicBoolean();

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<ClientEventListenerAdapter> a;
    }

    ClientEventListenerAdapter() {
    }

    public void onEvent(d dVar) {
        final boolean a = this.c.a();
        if (this.g.compareAndSet(!a, a)) {
            a(new Runnable(this) {
                final /* synthetic */ ClientEventListenerAdapter b;

                public final void run() {
                    this.b.a.onAdPlayableChanged(a);
                }
            });
        }
    }

    public void onEvent(x xVar) {
        Logger.d(Logger.EVENT_TAG, "onAdStart() callback");
        this.d = 0;
        this.e = 0;
        a(new Runnable(this) {
            final /* synthetic */ ClientEventListenerAdapter a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.a.onAdStart();
            }
        });
    }

    public void onEvent(ao playAdSuccessEvent) {
        a(playAdSuccessEvent.b);
    }

    public void onEvent(ad errorEndPlayEvent) {
        if (errorEndPlayEvent instanceof al) {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - activity destroyed");
        } else {
            Logger.d(Logger.EVENT_TAG, "onAdEnd() - error during playback");
        }
        a(false);
    }

    private void a(final boolean z) {
        final int i = this.d;
        final int i2 = this.e;
        final boolean z2 = ((float) i) / ((float) i2) > 0.8f;
        Logger.d(Logger.EVENT_TAG, "onVideoEnd(" + z2 + ", " + i + ", " + i2 + ") callback");
        a(new Runnable(this) {
            final /* synthetic */ ClientEventListenerAdapter d;

            public final void run() {
                this.d.a.onVideoView(z2, i, i2);
            }
        });
        Logger.d(Logger.EVENT_TAG, "onAdEnd(" + z + ") callback");
        a(new Runnable(this) {
            final /* synthetic */ ClientEventListenerAdapter b;

            public final void run() {
                this.b.a.onAdEnd(z);
            }
        });
    }

    public void onEvent(ag agVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(error) callback");
        a("Error launching ad");
    }

    public void onEvent(ae aeVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(already playing) callback");
        a("Ad already playing");
    }

    public void onEvent(ah ahVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(not initialized) callback");
        a("Vungle Publisher SDK was not successfully initialized - please check the logs");
    }

    public void onEvent(ai throttledCancelPlayEvent) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(throttled) callback");
        a("Only " + throttledCancelPlayEvent.a + " of minimum " + throttledCancelPlayEvent.b + " seconds elapsed between ads");
    }

    public void onEvent(aj ajVar) {
        Logger.d(Logger.EVENT_TAG, "onAdUnavailable(unavailable) callback");
        a("No cached or streaming ad available");
    }

    public void onEvent(k durationPlayVideoEvent) {
        this.e = durationPlayVideoEvent.a;
    }

    public void onEvent(l endPlayVideoEvent) {
        int i = endPlayVideoEvent.a;
        if (i > this.d) {
            Logger.d(Logger.EVENT_TAG, "new watched millis " + i);
            this.d = i;
            return;
        }
        Logger.d(Logger.EVENT_TAG, "shorter watched millis " + i);
    }

    private void a(final String str) {
        a(new Runnable(this) {
            final /* synthetic */ ClientEventListenerAdapter b;

            public final void run() {
                this.b.a.onAdUnavailable(str);
            }
        });
    }

    private void a(Runnable runnable) {
        this.b.a(runnable, b.clientEvent);
    }
}
