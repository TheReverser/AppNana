package com.vungle.publisher.protocol;

import com.vungle.publisher.bt;
import com.vungle.publisher.c;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class RequestStreamingAdHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestStreamingAdHttpRequest> {
        @Inject
        com.vungle.publisher.protocol.message.RequestStreamingAd.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new RequestStreamingAdHttpRequest();
        }

        public final RequestStreamingAdHttpRequest a(String str, c cVar) throws bt {
            try {
                RequestStreamingAdHttpRequest requestStreamingAdHttpRequest = (RequestStreamingAdHttpRequest) super.c();
                requestStreamingAdHttpRequest.b = this.d + "requestStreamingAd";
                requestStreamingAdHttpRequest.c.putString("Content-Type", "application/json");
                requestStreamingAdHttpRequest.d = this.g.a(str, cVar).a();
                return requestStreamingAdHttpRequest;
            } catch (Throwable e) {
                throw new bt(e);
            }
        }
    }

    protected RequestStreamingAdHttpRequest() {
    }

    protected final b b() {
        return b.requestStreamingAd;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.GET;
    }
}
