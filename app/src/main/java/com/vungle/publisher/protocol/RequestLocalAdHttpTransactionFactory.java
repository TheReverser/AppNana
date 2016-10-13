package com.vungle.publisher.protocol;

import com.vungle.publisher.cg;
import com.vungle.publisher.ci;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestLocalAdHttpTransactionFactory extends Factory {
    @Inject
    RequestLocalAdHttpRequest.Factory a;
    @Inject
    Lazy<RequestLocalAdHttpResponseHandler> b;

    public final HttpTransaction a(ci ciVar) {
        return super.a(this.a.d(), (cg) this.b.get(), ciVar);
    }
}
