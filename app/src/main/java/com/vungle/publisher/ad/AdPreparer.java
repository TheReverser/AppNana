package com.vungle.publisher.ad;

import android.content.Context;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.prepare.PrepareAdRunnable.Factory;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdPreparer {
    @Inject
    Context a;
    @Inject
    public Factory b;
    @Inject
    public ScheduledPriorityExecutor c;

    AdPreparer() {
    }

    public final void a(String str) {
        Logger.d(Logger.PREPARE_TAG, "prepare ad request: " + str);
        this.c.a(this.b.a(str, null, null), b.prepareLocalAd);
    }
}
