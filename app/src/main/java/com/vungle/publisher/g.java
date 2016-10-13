package com.vungle.publisher;

import com.vungle.publisher.db.model.Ad;

/* compiled from: vungle */
public abstract class g extends bj implements f {
    protected final Ad<?, ?, ?> a;

    public g(Ad<?, ?, ?> ad) {
        this.a = ad;
    }

    public final Ad<?, ?, ?> a() {
        return this.a;
    }
}
