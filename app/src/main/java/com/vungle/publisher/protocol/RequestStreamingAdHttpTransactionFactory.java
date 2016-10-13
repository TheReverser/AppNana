package com.vungle.publisher.protocol;

import com.vungle.publisher.c;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class RequestStreamingAdHttpTransactionFactory extends Factory {
    @Inject
    RequestStreamingAdHttpRequest.Factory a;
    @Inject
    RequestStreamingAdHttpResponseHandler b;

    public final HttpTransaction a(String str, c cVar) throws JSONException {
        return super.a(this.a.a(str, cVar), this.b);
    }
}
