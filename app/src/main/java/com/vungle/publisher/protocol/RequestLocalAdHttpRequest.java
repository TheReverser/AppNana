package com.vungle.publisher.protocol;

import com.vungle.publisher.bt;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class RequestLocalAdHttpRequest extends ProtocolHttpRequest {
    RequestLocalAd e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestLocalAdHttpRequest> {
        @Inject
        com.vungle.publisher.protocol.message.RequestLocalAd.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new RequestLocalAdHttpRequest();
        }

        public final /* synthetic */ HttpRequest b() {
            return d();
        }

        public final /* synthetic */ ProtocolHttpRequest c() {
            return d();
        }

        public final RequestLocalAdHttpRequest d() throws bt {
            try {
                RequestLocalAdHttpRequest requestLocalAdHttpRequest = (RequestLocalAdHttpRequest) super.c();
                requestLocalAdHttpRequest.b = this.d + "requestAd";
                requestLocalAdHttpRequest.c.putString("Content-Type", "application/json");
                RequestLocalAd c = this.g.c();
                requestLocalAdHttpRequest.e = c;
                requestLocalAdHttpRequest.d = c.a();
                return requestLocalAdHttpRequest;
            } catch (Throwable e) {
                throw new bt(e);
            }
        }
    }

    protected RequestLocalAdHttpRequest() {
    }

    protected final b b() {
        return b.requestLocalAd;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.GET;
    }
}
