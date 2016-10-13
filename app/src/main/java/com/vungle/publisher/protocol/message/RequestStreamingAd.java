package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bu;
import com.vungle.publisher.c;
import com.vungle.publisher.protocol.message.RequestAd.a;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public final class RequestStreamingAd extends RequestAd<RequestStreamingAd> {
    ExtraInfo g;
    String h;
    String i;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends a<RequestStreamingAd> {
        @Inject
        protected Factory e;

        protected final /* synthetic */ Object a() {
            return new RequestStreamingAd();
        }

        protected final /* bridge */ /* synthetic */ Object[] a(int i) {
            return new RequestStreamingAd[i];
        }

        Factory() {
        }

        public final RequestStreamingAd a(String str, c cVar) {
            RequestStreamingAd requestStreamingAd = (RequestStreamingAd) super.b();
            requestStreamingAd.g = Factory.a(cVar.getExtras());
            requestStreamingAd.h = str;
            requestStreamingAd.i = cVar.getPlacement();
            return requestStreamingAd;
        }
    }

    RequestStreamingAd() {
    }

    public final JSONObject b() throws JSONException {
        JSONObject b = super.b();
        b.putOpt("campaignId", this.h);
        b.putOpt("extraInfo", bu.a(this.g));
        b.putOpt("placement", this.i);
        return b;
    }
}
