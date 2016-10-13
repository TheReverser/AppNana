package com.jirbo.adcolony;

import com.facebook.BuildConfig;
import com.facebook.internal.NativeProtocol;
import java.io.Serializable;

class ag implements Serializable {
    String a = BuildConfig.VERSION_NAME;
    int b;
    int c;
    int d;

    ag() {
    }

    ag(String str) {
        this.a = str;
    }

    boolean a(g gVar) {
        if (gVar == null) {
            return false;
        }
        this.a = gVar.a("uuid", NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE);
        this.b = gVar.g("skipped_plays");
        this.c = gVar.g("play_order_index");
        return true;
    }

    g a() {
        g gVar = new g();
        gVar.b("uuid", this.a);
        gVar.b("skipped_plays", this.b);
        gVar.b("play_order_index", this.c);
        return gVar;
    }
}
