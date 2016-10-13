package com.vungle.publisher;

import com.immersion.hapticmediasdk.HapticContentSDK;
import java.util.ArrayList;
import java.util.List;

/* compiled from: vungle */
final class da {
    private static final List<da> d = new ArrayList();
    Object a;
    df b;
    da c;

    private da(Object obj, df dfVar) {
        this.a = obj;
        this.b = dfVar;
    }

    static da a(df dfVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                da daVar = (da) d.remove(size - 1);
                daVar.a = obj;
                daVar.b = dfVar;
                daVar.c = null;
                return daVar;
            }
            return new da(obj, dfVar);
        }
    }

    static void a(da daVar) {
        daVar.a = null;
        daVar.b = null;
        daVar.c = null;
        synchronized (d) {
            if (d.size() < HapticContentSDK.b044404440444ф04440444) {
                d.add(daVar);
            }
        }
    }
}
