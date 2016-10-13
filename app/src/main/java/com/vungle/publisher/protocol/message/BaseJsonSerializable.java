package com.vungle.publisher.protocol.message;

import com.vungle.log.Logger;
import com.vungle.publisher.cl;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public abstract class BaseJsonSerializable implements cl {
    BaseJsonSerializable() {
    }

    public final String a() throws JSONException {
        JSONObject b = b();
        return b == null ? null : b.toString();
    }

    public JSONObject b() throws JSONException {
        return new JSONObject();
    }

    protected static void a(String str, Object obj) {
        if (obj == null) {
            Logger.d(Logger.PROTOCOL_TAG, "null " + str + " is required output");
        }
    }
}
