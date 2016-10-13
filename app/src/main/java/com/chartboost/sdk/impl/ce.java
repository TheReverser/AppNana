package com.chartboost.sdk.impl;

import java.io.Closeable;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ce {
    private static final ce a;
    private final int b;
    private final long c;
    private final LinkedList<cd> d = new LinkedList();
    private final ExecutorService e = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), cs.a("OkHttp ConnectionPool", true));
    private final Runnable f = new Runnable(this) {
        final /* synthetic */ ce a;

        {
            this.a = r1;
        }

        public void run() {
            List<Closeable> arrayList = new ArrayList(2);
            int i = 0;
            synchronized (this.a) {
                ListIterator listIterator = this.a.d.listIterator(this.a.d.size());
                while (listIterator.hasPrevious()) {
                    int i2;
                    cd cdVar = (cd) listIterator.previous();
                    if (!cdVar.d() || cdVar.a(this.a.c)) {
                        listIterator.remove();
                        arrayList.add(cdVar);
                        if (arrayList.size() == 2) {
                            break;
                        }
                        i2 = i;
                    } else {
                        if (cdVar.g()) {
                            i2 = i + 1;
                        }
                        i2 = i;
                    }
                    i = i2;
                }
                listIterator = this.a.d.listIterator(this.a.d.size());
                while (listIterator.hasPrevious() && i > this.a.b) {
                    cdVar = (cd) listIterator.previous();
                    if (cdVar.g()) {
                        arrayList.add(cdVar);
                        listIterator.remove();
                        i2 = i - 1;
                    } else {
                        i2 = i;
                    }
                    i = i2;
                }
            }
            for (Closeable a : arrayList) {
                cs.a(a);
            }
        }
    };

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        long parseLong = property2 != null ? Long.parseLong(property2) : 300000;
        if (property != null && !Boolean.parseBoolean(property)) {
            a = new ce(0, parseLong);
        } else if (property3 != null) {
            a = new ce(Integer.parseInt(property3), parseLong);
        } else {
            a = new ce(5, parseLong);
        }
    }

    public ce(int i, long j) {
        this.b = i;
        this.c = (j * 1000) * 1000;
    }

    public static ce a() {
        return a;
    }

    public synchronized cd a(cb cbVar) {
        cd cdVar;
        ListIterator listIterator = this.d.listIterator(this.d.size());
        while (listIterator.hasPrevious()) {
            Closeable closeable = (cd) listIterator.previous();
            if (closeable.b().a().equals(cbVar) && closeable.d() && System.nanoTime() - closeable.h() < this.c) {
                listIterator.remove();
                if (closeable.j()) {
                    break;
                }
                try {
                    cr.a().a(closeable.c());
                    break;
                } catch (SocketException e) {
                    cs.a(closeable);
                    cr.a().a("Unable to tagSocket(): " + e);
                }
            }
        }
        cdVar = null;
        if (cdVar != null) {
            if (cdVar.j()) {
                this.d.addFirst(cdVar);
            }
        }
        this.e.execute(this.f);
        return cdVar;
    }

    public void a(cd cdVar) {
        if (!cdVar.j()) {
            if (cdVar.d()) {
                try {
                    cr.a().b(cdVar.c());
                    synchronized (this) {
                        this.d.addFirst(cdVar);
                        cdVar.m();
                        cdVar.f();
                    }
                    this.e.execute(this.f);
                    return;
                } catch (SocketException e) {
                    cr.a().a("Unable to untagSocket(): " + e);
                    cs.a((Closeable) cdVar);
                    return;
                }
            }
            cs.a((Closeable) cdVar);
        }
    }

    public void b(cd cdVar) {
        if (cdVar.j()) {
            this.e.execute(this.f);
            if (cdVar.d()) {
                synchronized (this) {
                    this.d.addFirst(cdVar);
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
}
