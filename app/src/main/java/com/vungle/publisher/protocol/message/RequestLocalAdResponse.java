package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bu;
import com.vungle.publisher.protocol.message.RequestAdResponse.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestLocalAdResponse extends RequestAdResponse {
    public Integer r;
    public Long s;
    public String t;
    public String u;
    public Integer v;
    public String w;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestLocalAdResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestLocalAdResponse();
        }

        public final /* synthetic */ Object a(JSONObject jSONObject) throws JSONException {
            return c(jSONObject);
        }

        public final /* synthetic */ RequestAdResponse b(JSONObject jSONObject) throws JSONException {
            return c(jSONObject);
        }

        @Inject
        protected Factory() {
        }

        private RequestLocalAdResponse c(JSONObject jSONObject) throws JSONException {
            Long l = null;
            if (jSONObject == null) {
                return null;
            }
            if (jSONObject.isNull("sleep")) {
                RequestLocalAdResponse requestLocalAdResponse = (RequestLocalAdResponse) super.b(jSONObject);
                String str = "expiry";
                long optLong = jSONObject.optLong(str, -1);
                if (optLong == -1) {
                    long optLong2 = jSONObject.optLong(str, -2);
                    if (optLong2 != -2) {
                        l = Long.valueOf(optLong2);
                    }
                } else {
                    l = Long.valueOf(optLong);
                }
                requestLocalAdResponse.s = l;
                JsonDeserializationFactory.a(jSONObject, "expiry", l);
                requestLocalAdResponse.t = bu.d(jSONObject, "postBundle");
                requestLocalAdResponse.u = bu.d(jSONObject, "preBundle");
                requestLocalAdResponse.v = bu.c(jSONObject, "size");
                requestLocalAdResponse.w = jSONObject.optString("md5");
                JsonDeserializationFactory.a(jSONObject, "md5", requestLocalAdResponse.w);
                return requestLocalAdResponse;
            }
            requestLocalAdResponse = new RequestLocalAdResponse();
            requestLocalAdResponse.r = Integer.valueOf(jSONObject.getInt("sleep"));
            return requestLocalAdResponse;
        }
    }

    RequestLocalAdResponse() {
    }
}
