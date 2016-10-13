package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.au;
import com.vungle.publisher.cd;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DownloadHttpGateway extends cd {
    @Inject
    DownloadHttpTransactionFactory a;

    /* compiled from: vungle */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ au a;
        final /* synthetic */ DownloadHttpGateway b;

        public AnonymousClass1(DownloadHttpGateway downloadHttpGateway, au auVar) {
            this.b = downloadHttpGateway;
            this.a = auVar;
        }

        public final void run() {
            try {
                this.b.a.a(this.a.d(), this.a.f(), this.a.g(), this.a.k()).a();
            } catch (Throwable e) {
                Logger.w(Logger.NETWORK_TAG, "error requesting streaming ad", e);
            }
        }
    }

    DownloadHttpGateway() {
    }
}
