package com.chartboost.sdk.impl;

import java.util.LinkedHashSet;
import java.util.Set;

public final class cm {
    private final Set<cl> a = new LinkedHashSet();

    public synchronized void a(cl clVar) {
        this.a.add(clVar);
    }

    public synchronized void b(cl clVar) {
        this.a.remove(clVar);
    }

    public synchronized boolean c(cl clVar) {
        return this.a.contains(clVar);
    }
}
