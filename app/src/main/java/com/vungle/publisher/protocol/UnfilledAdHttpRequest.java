package com.vungle.publisher.protocol;

import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.internal.ServerProtocol;
import com.vungle.publisher.bf;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class UnfilledAdHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<UnfilledAdHttpRequest> {
        @Inject
        bf g;
        @Inject
        com.vungle.publisher.protocol.message.RequestLocalAd.Factory h;

        protected final /* synthetic */ HttpRequest a() {
            return new UnfilledAdHttpRequest();
        }

        public final UnfilledAdHttpRequest a(long j) {
            UnfilledAdHttpRequest unfilledAdHttpRequest = (UnfilledAdHttpRequest) super.c();
            Builder appendQueryParameter = Uri.parse(this.d + "unfilled").buildUpon().appendQueryParameter(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c.b());
            String a = this.g.a();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("ifa", a);
            }
            a = this.g.c();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("isu", a);
            }
            a = this.g.j();
            if (a != null) {
                appendQueryParameter.appendQueryParameter("mac", a);
            }
            appendQueryParameter.appendQueryParameter("ut", String.valueOf(j));
            unfilledAdHttpRequest.b = appendQueryParameter.toString();
            return unfilledAdHttpRequest;
        }
    }

    protected UnfilledAdHttpRequest() {
    }

    protected final b b() {
        return b.unfilledAd;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.POST;
    }
}
