package com.vungle.publisher.event;

import com.vungle.publisher.cw;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class EventBus {
    public final cw a = new cw();

    @Inject
    EventBus() {
    }

    public final void a(Object obj) {
        this.a.b(obj);
    }
}
