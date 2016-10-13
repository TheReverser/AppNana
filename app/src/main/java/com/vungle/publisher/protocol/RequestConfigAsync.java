package com.vungle.publisher.protocol;

import com.vungle.publisher.ap;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestConfigAsync extends ap {
    @Inject
    RequestConfigRunnable b;

    @Singleton
    /* compiled from: vungle */
    static class RequestConfigRunnable implements Runnable {
        @Inject
        ProtocolHttpGateway a;

        RequestConfigRunnable() {
        }

        public void run() {
            this.a.a();
        }
    }

    protected final b b() {
        return b.requestConfig;
    }

    protected final Runnable a() {
        return this.b;
    }
}
