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
public final class TrackInstallHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<TrackInstallHttpRequest> {
        @Inject
        bf g;
        @Inject
        com.vungle.publisher.protocol.message.RequestLocalAd.Factory h;

        protected final /* synthetic */ HttpRequest a() {
            return new TrackInstallHttpRequest();
        }

        public final /* synthetic */ HttpRequest b() {
            return d();
        }

        public final /* synthetic */ ProtocolHttpRequest c() {
            return d();
        }

        public final TrackInstallHttpRequest d() {
            TrackInstallHttpRequest trackInstallHttpRequest = (TrackInstallHttpRequest) super.c();
            Builder appendQueryParameter = Uri.parse(this.d + "new").buildUpon().appendQueryParameter(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID, this.c.b());
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
            trackInstallHttpRequest.b = appendQueryParameter.toString();
            return trackInstallHttpRequest;
        }
    }

    protected TrackInstallHttpRequest() {
    }

    protected final b b() {
        return b.trackInstall;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.POST;
    }
}
