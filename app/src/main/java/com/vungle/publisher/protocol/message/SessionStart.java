package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class SessionStart extends BaseJsonSerializable {
    String a;
    String b;
    String c;
    String d;
    Long e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        bf a;
        @Inject
        bh b;

        public final SessionStart a(long j) {
            SessionStart sessionStart = new SessionStart();
            sessionStart.a = this.a.a();
            sessionStart.b = this.a.c();
            sessionStart.c = this.a.j();
            sessionStart.d = this.b.b();
            sessionStart.e = Long.valueOf(j);
            return sessionStart;
        }
    }

    SessionStart() {
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.putOpt("ifa", this.a);
        b.putOpt("isu", this.b);
        b.putOpt("mac", this.c);
        BaseJsonSerializable.a("pubAppId", this.d);
        b.put("pubAppId", this.d);
        BaseJsonSerializable.a("start", this.e);
        b.put("start", this.e);
        return b;
    }
}
