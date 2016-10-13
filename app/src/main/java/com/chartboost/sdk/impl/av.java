package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;

public class av extends ay {
    public av(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public static av a(a aVar) {
        try {
            av avVar = new av(aVar.e("path"), aVar.e("endPoint"), aVar.e("identifier"));
            avVar.g = aVar.e("method");
            avVar.d = aVar.a("query").f();
            avVar.c = aVar.a("body");
            avVar.e = aVar.a("headers").f();
            avVar.l = aVar.i("ensureDelivery");
            avVar.b = aVar.e("eventType");
            avVar.o = aVar.e("endPoint");
            avVar.p = aVar.e("identifier");
            avVar.a = aVar.e("path");
            avVar.f = aVar.a("entity");
            avVar.j = aVar.i("isEncryptedCBRequest");
            avVar.r = aVar.f("retryCount");
            return avVar;
        } catch (Throwable e) {
            CBLogging.d("CBAPIRequest", "Unable to deserialize failed request", e);
            return null;
        }
    }

    public a a() {
        return e.a(e.a("path", this.a), e.a("method", this.g), e.a("query", e.a(this.d)), e.a("body", this.c), e.a("eventType", this.b), e.a("endPoint", this.o), e.a("identifier", this.p), e.a("headers", e.a(this.e)), e.a("ensureDelivery", Boolean.valueOf(this.l)), e.a("entity", this.f), e.a("retryCount", Integer.valueOf(this.r)), e.a("isEncryptedCBRequest", Boolean.valueOf(this.j)));
    }
}
