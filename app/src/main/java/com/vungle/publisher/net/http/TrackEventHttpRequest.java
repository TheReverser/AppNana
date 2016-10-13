package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.HttpRequest.b;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class TrackEventHttpRequest extends HttpRequest {
    Ad<?, ?, ?> e;
    a f;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.net.http.HttpRequest.Factory<TrackEventHttpRequest> {
        protected final /* synthetic */ HttpRequest a() {
            return new TrackEventHttpRequest();
        }

        @Inject
        Factory() {
        }
    }

    TrackEventHttpRequest() {
    }

    protected final b b() {
        return b.trackEvent;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.GET;
    }

    public final String toString() {
        return "{" + b.trackEvent + ": " + this.b + "}";
    }
}
