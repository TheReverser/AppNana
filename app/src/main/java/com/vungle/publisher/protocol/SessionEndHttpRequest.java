package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.SessionEnd;
import com.vungle.publisher.protocol.message.SessionStart;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class SessionEndHttpRequest extends ProtocolHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<SessionEndHttpRequest> {
        @Inject
        com.vungle.publisher.protocol.message.SessionEnd.Factory g;

        protected final /* synthetic */ HttpRequest a() {
            return new SessionEndHttpRequest();
        }

        protected final SessionEndHttpRequest a(long j, long j2) throws JSONException {
            SessionEndHttpRequest sessionEndHttpRequest = (SessionEndHttpRequest) c();
            sessionEndHttpRequest.c.putString("Content-Type", "application/json");
            sessionEndHttpRequest.b = this.d + "sessionEnd";
            SessionStart a = this.g.a.a(j);
            SessionEnd a2 = com.vungle.publisher.protocol.message.SessionEnd.Factory.a();
            a2.b = a;
            a2.a = Long.valueOf(j2);
            sessionEndHttpRequest.d = a2.a();
            return sessionEndHttpRequest;
        }
    }

    protected SessionEndHttpRequest() {
    }

    protected final b b() {
        return b.sessionEnd;
    }

    protected final HttpRequest.a a() {
        return HttpRequest.a.POST;
    }
}
