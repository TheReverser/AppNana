package com.chartboost.sdk.impl;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class l {
    private AtomicInteger a;
    private final Map<String, Queue<k<?>>> b;
    private final Set<k<?>> c;
    private final PriorityBlockingQueue<k<?>> d;
    private final PriorityBlockingQueue<k<?>> e;
    private final b f;
    private final f g;
    private final n h;
    private g[] i;
    private c j;

    public interface a {
        boolean a(k<?> kVar);
    }

    public l(b bVar, f fVar, int i, n nVar) {
        this.a = new AtomicInteger();
        this.b = new HashMap();
        this.c = new HashSet();
        this.d = new PriorityBlockingQueue();
        this.e = new PriorityBlockingQueue();
        this.f = bVar;
        this.g = fVar;
        this.i = new g[i];
        this.h = nVar;
    }

    public l(b bVar, f fVar, int i) {
        this(bVar, fVar, i, new e(new Handler(Looper.getMainLooper())));
    }

    public l(b bVar, f fVar) {
        this(bVar, fVar, 4);
    }

    public void a() {
        b();
        this.j = new c(this.d, this.e, this.f, this.h);
        this.j.start();
        for (int i = 0; i < this.i.length; i++) {
            g gVar = new g(this.e, this.g, this.f, this.h);
            this.i[i] = gVar;
            gVar.start();
        }
    }

    public void b() {
        if (this.j != null) {
            this.j.a();
        }
        for (int i = 0; i < this.i.length; i++) {
            if (this.i[i] != null) {
                this.i[i].a();
            }
        }
    }

    public int c() {
        return this.a.incrementAndGet();
    }

    public void a(a aVar) {
        synchronized (this.c) {
            for (k kVar : this.c) {
                if (aVar.a(kVar)) {
                    kVar.g();
                }
            }
        }
    }

    public void a(final Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        a(new a(this) {
            final /* synthetic */ l a;

            public boolean a(k<?> kVar) {
                return kVar.b() == obj;
            }
        });
    }

    public <T> k<T> a(k<T> kVar) {
        kVar.a(this);
        synchronized (this.c) {
            this.c.add(kVar);
        }
        kVar.a(c());
        kVar.a("add-to-queue");
        if (kVar.r()) {
            synchronized (this.b) {
                String e = kVar.e();
                if (this.b.containsKey(e)) {
                    Queue queue = (Queue) this.b.get(e);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(kVar);
                    this.b.put(e, queue);
                    if (s.b) {
                        s.a("Request for cacheKey=%s is in flight, putting on hold.", e);
                    }
                } else {
                    this.b.put(e, null);
                    this.d.add(kVar);
                }
            }
        } else {
            this.e.add(kVar);
        }
        return kVar;
    }

    void b(k<?> kVar) {
        synchronized (this.c) {
            this.c.remove(kVar);
        }
        if (kVar.r()) {
            synchronized (this.b) {
                Queue queue = (Queue) this.b.remove(kVar.e());
                if (queue != null) {
                    if (s.b) {
                        s.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.d.addAll(queue);
                }
            }
        }
    }
}
