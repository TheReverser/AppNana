package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bu;
import com.vungle.publisher.protocol.message.RequestAdResponse.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestStreamingAdResponse extends RequestAdResponse {
    public Boolean r;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestStreamingAdResponse> {
        protected final /* synthetic */ Object a() {
            return new RequestStreamingAdResponse();
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

        private RequestStreamingAdResponse c(JSONObject jSONObject) throws JSONException {
            RequestStreamingAdResponse requestStreamingAdResponse = null;
            if (jSONObject != null) {
                Boolean a = bu.a(jSONObject, "shouldStream");
                if (Boolean.TRUE.equals(a)) {
                    requestStreamingAdResponse = (RequestStreamingAdResponse) super.b(jSONObject);
                } else {
                    requestStreamingAdResponse = new RequestStreamingAdResponse();
                }
                requestStreamingAdResponse.r = a;
            }
            return requestStreamingAdResponse;
        }
    }
}
