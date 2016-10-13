package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bh;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class RequestConfig extends BaseJsonSerializable {
    @Inject
    bh a;

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.putOpt("pubAppId", this.a.b());
        return b;
    }
}
