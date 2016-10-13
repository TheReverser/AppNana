package com.vungle.publisher.net.http;

import com.vungle.publisher.net.http.HttpRequest.a;
import com.vungle.publisher.net.http.HttpRequest.b;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class DownloadHttpRequest extends HttpRequest {

    @Singleton
    /* compiled from: vungle */
    static class Factory extends com.vungle.publisher.net.http.HttpRequest.Factory<DownloadHttpRequest> {
        protected final /* synthetic */ HttpRequest a() {
            return new DownloadHttpRequest();
        }

        @Inject
        Factory() {
        }
    }

    DownloadHttpRequest() {
    }

    protected final a a() {
        return a.GET;
    }

    protected final b b() {
        return b.download;
    }

    public final String toString() {
        return "{" + b.download + ": " + this.b + "}";
    }
}
