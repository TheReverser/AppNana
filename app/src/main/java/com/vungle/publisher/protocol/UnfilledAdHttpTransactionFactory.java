package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class UnfilledAdHttpTransactionFactory extends Factory {
    @Inject
    UnfilledAdHttpRequest.Factory a;
    @Inject
    FireAndForgetHttpResponseHandler b;

    public final HttpTransaction a(long j) {
        return super.a(this.a.a(j), this.b);
    }
}
