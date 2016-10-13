package com.vungle.publisher;

import android.os.Looper;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: vungle */
public final class cw {
    static ExecutorService a = Executors.newCachedThreadPool();
    private static final Map<Class<?>, List<Class<?>>> b = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<df>> c = new HashMap();
    private final Map<Object, List<Class<?>>> d = new HashMap();
    private final Map<Class<?>, Object> e = new ConcurrentHashMap();
    private final ThreadLocal<a> f = new ThreadLocal<a>(this) {
        final /* synthetic */ cw a;

        {
            this.a = r1;
        }

        protected final /* synthetic */ Object initialValue() {
            return new a();
        }
    };
    private final cy g = new cy(this, Looper.getMainLooper());
    private final cv h = new cv(this);
    private final cu i = new cu(this);
    private final de j = new de();
    private boolean k;
    private boolean l = true;

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[dg.values().length];

        static {
            try {
                a[dg.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[dg.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[dg.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[dg.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* compiled from: vungle */
    static final class a {
        List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        df d;
        Object e;
        boolean f;

        a() {
        }
    }

    public final synchronized void a(Object obj, String str, boolean z) {
        for (dd ddVar : de.a(obj.getClass(), str)) {
            CopyOnWriteArrayList copyOnWriteArrayList;
            this.k = true;
            Class cls = ddVar.c;
            CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.c.get(cls);
            df dfVar = new df(obj, ddVar);
            if (copyOnWriteArrayList2 == null) {
                copyOnWriteArrayList2 = new CopyOnWriteArrayList();
                this.c.put(cls, copyOnWriteArrayList2);
                copyOnWriteArrayList = copyOnWriteArrayList2;
            } else {
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    if (((df) it.next()).equals(dfVar)) {
                        throw new cx("Subscriber " + obj.getClass() + " already registered to event " + cls);
                    }
                }
                copyOnWriteArrayList = copyOnWriteArrayList2;
            }
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i <= size) {
                if (i == size || dfVar.c > ((df) copyOnWriteArrayList.get(i)).c) {
                    copyOnWriteArrayList.add(i, dfVar);
                    break;
                }
                i++;
            }
            List list = (List) this.d.get(obj);
            if (list == null) {
                list = new ArrayList();
                this.d.put(obj, list);
            }
            list.add(cls);
            if (z) {
                Object obj2;
                synchronized (this.e) {
                    obj2 = this.e.get(cls);
                }
                if (obj2 != null) {
                    a(dfVar, obj2, Looper.getMainLooper() == Looper.myLooper());
                } else {
                    continue;
                }
            }
        }
    }

    public final synchronized void a(Object obj) {
        List<Class> list = (List) this.d.get(obj);
        if (list != null) {
            for (Class cls : list) {
                List list2 = (List) this.c.get(cls);
                if (list2 != null) {
                    int size = list2.size();
                    int i = 0;
                    while (i < size) {
                        int i2;
                        df dfVar = (df) list2.get(i);
                        if (dfVar.a == obj) {
                            dfVar.d = false;
                            list2.remove(i);
                            i2 = i - 1;
                            i = size - 1;
                        } else {
                            i2 = i;
                            i = size;
                        }
                        size = i;
                        i = i2 + 1;
                    }
                }
            }
            this.d.remove(obj);
        } else {
            Logger.w(Logger.EVENT_TAG, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public final void b(Object obj) {
        a aVar = (a) this.f.get();
        List list = aVar.a;
        list.add(obj);
        if (!aVar.b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            aVar.c = z;
            aVar.b = true;
            if (aVar.f) {
                throw new cx("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                Object remove = list.remove(0);
                Class cls = remove.getClass();
                List a = a(cls);
                int size = a.size();
                int i = 0;
                boolean z2 = false;
                while (i < size) {
                    CopyOnWriteArrayList copyOnWriteArrayList;
                    Class cls2 = (Class) a.get(i);
                    synchronized (this) {
                        copyOnWriteArrayList = (CopyOnWriteArrayList) this.c.get(cls2);
                    }
                    if (copyOnWriteArrayList != null) {
                        if (!copyOnWriteArrayList.isEmpty()) {
                            Iterator it = copyOnWriteArrayList.iterator();
                            while (it.hasNext()) {
                                df dfVar = (df) it.next();
                                aVar.e = remove;
                                aVar.d = dfVar;
                                try {
                                    a(dfVar, remove, aVar.c);
                                    z = aVar.f;
                                    aVar.e = null;
                                    aVar.d = null;
                                    aVar.f = false;
                                    if (z) {
                                        break;
                                    }
                                } catch (Throwable th) {
                                    aVar.b = false;
                                    aVar.c = false;
                                }
                            }
                            z = true;
                            i++;
                            z2 = z;
                        }
                    }
                    z = z2;
                    i++;
                    z2 = z;
                }
                if (!z2) {
                    Logger.d(Logger.EVENT_TAG, "No subscribers registered for event " + cls);
                    if (!(cls == cz.class || cls == dc.class)) {
                        b(new cz(this, remove));
                    }
                }
            }
            aVar.b = false;
            aVar.c = false;
        }
    }

    private void a(df dfVar, Object obj, boolean z) {
        da a;
        switch (AnonymousClass2.a[dfVar.b.b.ordinal()]) {
            case Gender.FEMALE /*1*/:
                a(dfVar, obj);
                return;
            case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                if (z) {
                    a(dfVar, obj);
                    return;
                }
                cy cyVar = this.g;
                a = da.a(dfVar, obj);
                synchronized (cyVar) {
                    cyVar.a.a(a);
                    if (!cyVar.b) {
                        cyVar.b = true;
                        if (!cyVar.sendMessage(cyVar.obtainMessage())) {
                            throw new cx("Could not send handler message");
                        }
                    }
                }
                return;
            case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                if (z) {
                    Runnable runnable = this.h;
                    a = da.a(dfVar, obj);
                    synchronized (runnable) {
                        runnable.a.a(a);
                        if (!runnable.b) {
                            runnable.b = true;
                            a.execute(runnable);
                        }
                    }
                    return;
                }
                a(dfVar, obj);
                return;
            case Logger.INFO_LOGGING_LEVEL /*4*/:
                Runnable runnable2 = this.i;
                runnable2.a.a(da.a(dfVar, obj));
                a.execute(runnable2);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + dfVar.b.b);
        }
    }

    private static List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (b) {
            list = (List) b.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    list.add(cls2);
                    a((List) list, cls2.getInterfaces());
                }
                b.put(cls, list);
            }
        }
        return list;
    }

    private static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    final void a(da daVar) {
        Object obj = daVar.a;
        df dfVar = daVar.b;
        da.a(daVar);
        if (dfVar.d) {
            a(dfVar, obj);
        }
    }

    private void a(df dfVar, Object obj) throws Error {
        Throwable cause;
        try {
            dfVar.b.a.invoke(dfVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            cause = e.getCause();
            if (obj instanceof dc) {
                Logger.e(Logger.EVENT_TAG, "SubscriberExceptionEvent subscriber " + dfVar.a.getClass() + " threw an exception", cause);
                dc dcVar = (dc) obj;
                Logger.e(Logger.EVENT_TAG, "Initial event " + dcVar.c + " caused exception in " + dcVar.d, dcVar.b);
                return;
            }
            if (this.l) {
                Logger.e(Logger.EVENT_TAG, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + dfVar.a.getClass(), cause);
            }
            b(new dc(this, cause, obj, dfVar.a));
        } catch (Throwable cause2) {
            throw new IllegalStateException("Unexpected exception", cause2);
        }
    }
}
