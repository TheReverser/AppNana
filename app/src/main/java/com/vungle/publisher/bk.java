package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.event.EventBus;
import javax.inject.Inject;

/* compiled from: vungle */
public class bk implements bm {
    private boolean a;
    @Inject
    public EventBus f;

    public final void b() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.f.a.a((Object) this, "onEvent", false);
        this.a = true;
    }

    public final void c() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening sticky");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening sticky");
        this.f.a.a((Object) this, "onEvent", true);
        this.a = true;
    }

    public final void d() {
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " unregistered");
        this.f.a.a((Object) this);
        this.a = false;
    }
}
