package com.chartboost.sdk.impl;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class cs {
    public static final byte[] a = new byte[0];
    public static final String[] b = new String[0];
    public static final InputStream c = new ByteArrayInputStream(a);
    public static final Charset d = Charset.forName("US-ASCII");
    public static final Charset e = Charset.forName("UTF-8");
    public static final List<cj> f = a(Arrays.asList(new cj[]{cj.HTTP_2, cj.SPDY_3, cj.HTTP_11}));
    public static final List<cj> g = a(Arrays.asList(new cj[]{cj.SPDY_3, cj.HTTP_11}));
    public static final List<cj> h = a(Arrays.asList(new cj[]{cj.HTTP_2, cj.HTTP_11}));

    public static int a(URI uri) {
        return a(uri.getScheme(), uri.getPort());
    }

    public static int a(URL url) {
        return a(url.getProtocol(), url.getPort());
    }

    private static int a(String str, int i) {
        return i != -1 ? i : a(str);
    }

    public static int a(String str) {
        if ("http".equals(str)) {
            return 80;
        }
        if ("https".equals(str)) {
            return 443;
        }
        return -1;
    }

    public static void a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    public static void a(Closeable closeable, Closeable closeable2) throws IOException {
        Object obj = null;
        try {
            closeable.close();
        } catch (Throwable th) {
            obj = th;
        }
        try {
            closeable2.close();
        } catch (Throwable th2) {
            if (obj == null) {
                Throwable th3 = th2;
            }
        }
        if (obj != null) {
            if (obj instanceof IOException) {
                throw ((IOException) obj);
            } else if (obj instanceof RuntimeException) {
                throw ((RuntimeException) obj);
            } else if (obj instanceof Error) {
                throw ((Error) obj);
            } else {
                throw new AssertionError(obj);
            }
        }
    }

    public static boolean a(ee eeVar, int i) throws IOException {
        long nanoTime = System.nanoTime();
        dx dxVar = new dx();
        while (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime) < ((long) i)) {
            if (eeVar.b(dxVar, 2048) == -1) {
                return true;
            }
            dxVar.o();
        }
        return false;
    }

    public static <T> List<T> a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    public static <T> List<T> a(T[] tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    public static ThreadFactory a(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static cj a(ds dsVar) throws IOException {
        if (dsVar == null) {
            return cj.HTTP_11;
        }
        for (cj cjVar : cj.values()) {
            if (cjVar.d.equals(dsVar)) {
                return cjVar;
            }
        }
        throw new IOException("Unexpected protocol: " + dsVar.a());
    }
}
