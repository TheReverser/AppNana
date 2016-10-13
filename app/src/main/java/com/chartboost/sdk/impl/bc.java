package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.NaCl;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.j;
import com.chartboost.sdk.Model.CBError;
import org.apache.http.entity.ByteArrayEntity;

public class bc extends av {
    private static final String v = bc.class.getSimpleName();
    private static byte[] y = null;
    private a A;
    private byte[] w;
    private j x;
    private boolean z;

    public bc(String str, String str2, String str3) {
        super(str, str2, str3);
        this.z = false;
        this.A = null;
        this.w = NaCl.randombytes(24);
        this.x = NaCl.keypair();
        this.j = true;
    }

    public byte[] k() {
        return this.w;
    }

    public a c(boolean z) {
        if (this.A != null) {
            return this.A;
        }
        if (this.A != null || z) {
            return null;
        }
        byte[] encrypt = NaCl.encrypt(g().toString().getBytes(), k(), l(), m().b);
        String b = b.b(k());
        String b2 = b.b(m().a);
        return e.a(e.a("body", new String(encrypt)), e.a("nonce", b), e.a("publicKey", b2));
    }

    public static bc b(a aVar) {
        try {
            bc bcVar = new bc(aVar.e("path"), aVar.e("endpoint"), aVar.e("identifier"));
            bcVar.g = aVar.e("method");
            bcVar.d = aVar.a("query").f();
            bcVar.c = aVar.a("body");
            bcVar.e = aVar.a("headers").f();
            bcVar.l = aVar.i("ensureDelivery");
            bcVar.b = aVar.e("eventType");
            bcVar.o = aVar.e("endPoint");
            bcVar.a = aVar.e("path");
            bcVar.p = aVar.e("identifier");
            bcVar.f = aVar.a("entity");
            bcVar.A = aVar.a("preEncrypt");
            bcVar.j = aVar.i("isEncryptedCBRequest");
            bcVar.r = aVar.f("retryCount");
            return bcVar;
        } catch (Throwable e) {
            CBLogging.d(v, "Unable to deserialize failed request", e);
            return null;
        }
    }

    public a a() {
        a a = super.a();
        a.a("preEncrypt", this.A);
        a.a("isEncryptedCBRequest", Boolean.valueOf(this.j));
        return a;
    }

    public static byte[] l() {
        if (y == null) {
            y = NaCl.a();
        }
        return y;
    }

    protected j m() {
        return this.x;
    }

    protected void b() {
        super.b();
        this.e.put("Content-Type", "application/x-chartboost-encrypted");
        this.e.put("Accept", "application/x-chartboost-encrypted");
        a c = c(this.z);
        try {
            this.f = new ByteArrayEntity(c.e("body").getBytes());
            this.e.put("X-Chartboost-Crypto-Nonce", c.e("nonce"));
            this.e.put("X-Chartboost-Crypto-Public-Key", c.e("publicKey"));
        } catch (Exception e) {
            new CBError(CBError.a.MISCELLANEOUS, "failed to decrypt request data").a(false);
        }
    }
}
