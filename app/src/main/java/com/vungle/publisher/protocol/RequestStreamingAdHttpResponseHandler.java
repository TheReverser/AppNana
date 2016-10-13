package com.vungle.publisher.protocol;

import com.vungle.publisher.cf;
import com.vungle.publisher.cg;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.n;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse.Factory;
import com.vungle.publisher.t;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class RequestStreamingAdHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    @Inject
    EventBus a;
    @Inject
    Factory b;

    RequestStreamingAdHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        this.a.a(new t((RequestStreamingAdResponse) this.b.a(cg.a(cfVar.a))));
    }

    protected final void b(HttpTransaction httpTransaction, cf cfVar) {
        this.a.a(new n());
    }
}
