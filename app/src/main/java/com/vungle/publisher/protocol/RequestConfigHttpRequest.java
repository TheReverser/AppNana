package com.vungle.publisher.protocol;

import com.vungle.publisher.bt;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.RequestConfig;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestConfigHttpRequest extends ProtocolHttpRequest {
    boolean e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestConfigHttpRequest> {
        @Inject
        RequestConfigHttpRequest g;
        @Inject
        RequestConfig h;

        protected final /* bridge */ /* synthetic */ HttpRequest a() {
            return this.g;
        }

        public final /* synthetic */ HttpRequest b() {
            return d();
        }

        public final /* synthetic */ ProtocolHttpRequest c() {
            return d();
        }

        public final RequestConfigHttpRequest d() throws bt {
            try {
                if (this.g.e) {
                    return this.g;
                }
                RequestConfigHttpRequest requestConfigHttpRequest = (RequestConfigHttpRequest) super.c();
                requestConfigHttpRequest.b = this.d + "config";
                requestConfigHttpRequest.c.putString("Content-Type", "application/json");
                requestConfigHttpRequest.d = this.h.a();
                return requestConfigHttpRequest;
            } catch (Throwable e) {
                throw new bt(e);
            }
        }
    }

    @Inject
    RequestConfigHttpRequest() {
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.GET;
    }

    protected final b b() {
        return b.requestConfig;
    }
}
