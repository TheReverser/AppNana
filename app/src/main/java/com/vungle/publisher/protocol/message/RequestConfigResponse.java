package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bu;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestConfigResponse extends BaseJsonSerializable {
    public Boolean a;
    public Integer b;
    public Integer c;
    public a d;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends JsonDeserializationFactory<RequestConfigResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestConfigResponse();
        }

        protected final /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
            Object obj = null;
            if (jSONObject == null) {
                return null;
            }
            RequestConfigResponse requestConfigResponse = new RequestConfigResponse();
            requestConfigResponse.a = bu.a(jSONObject, "optIn");
            requestConfigResponse.b = bu.c(jSONObject, "updateDelay");
            requestConfigResponse.c = bu.c(jSONObject, "threshold");
            Class cls = a.class;
            String d = bu.d(jSONObject, "connection");
            if (d != null) {
                obj = Enum.valueOf(cls, d);
            }
            requestConfigResponse.d = (a) obj;
            return requestConfigResponse;
        }

        @Inject
        Factory() {
        }
    }

    /* compiled from: vungle */
    public enum a {
        all,
        wifi
    }

    public final /* bridge */ /* synthetic */ JSONObject b() throws JSONException {
        return super.b();
    }
}
