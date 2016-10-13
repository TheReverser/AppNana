package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class SessionStartHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<SessionStartHttpRequest> {
        @Inject
        com.vungle.publisher.protocol.message.SessionStart.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new SessionStartHttpRequest();
        }

        protected final SessionStartHttpRequest a(long j) throws JSONException {
            SessionStartHttpRequest sessionStartHttpRequest = (SessionStartHttpRequest) c();
            sessionStartHttpRequest.c.putString("Content-Type", "application/json");
            sessionStartHttpRequest.b = this.d + "sessionStart";
            sessionStartHttpRequest.d = this.g.a(j).a();
            return sessionStartHttpRequest;
        }
    }

    protected SessionStartHttpRequest() {
    }

    protected final b b() {
        return b.sessionStart;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.POST;
    }
}
