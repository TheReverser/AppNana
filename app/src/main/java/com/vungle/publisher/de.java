package com.vungle.publisher;

import com.vungle.log.Logger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: vungle */
final class de {
    private static final Map<String, List<dd>> a = new HashMap();
    private static final Map<Class<?>, Class<?>> b = new ConcurrentHashMap();

    de() {
    }

    static List<dd> a(Class<?> cls, String str) {
        String str2 = cls.getName() + '.' + str;
        synchronized (a) {
            List<dd> list = (List) a.get(str2);
        }
        if (list != null) {
            return list;
        }
        List<dd> arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name = cls2.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break;
            }
            for (Method method : cls2.getMethods()) {
                String name2 = method.getName();
                if (name2.startsWith(str)) {
                    int modifiers = method.getModifiers();
                    if ((modifiers & 1) != 0 && (modifiers & 1032) == 0) {
                        Class[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            dg dgVar;
                            name = name2.substring(str.length());
                            if (name.length() == 0) {
                                dgVar = dg.PostThread;
                            } else if (name.equals("MainThread")) {
                                dgVar = dg.MainThread;
                            } else if (name.equals("BackgroundThread")) {
                                dgVar = dg.BackgroundThread;
                            } else if (name.equals("Async")) {
                                dgVar = dg.Async;
                            } else if (!b.containsKey(cls2)) {
                                throw new cx("Illegal onEvent method, check for typos: " + method);
                            }
                            Class cls3 = parameterTypes[0];
                            stringBuilder.setLength(0);
                            stringBuilder.append(name2);
                            stringBuilder.append('>').append(cls3.getName());
                            if (hashSet.add(stringBuilder.toString())) {
                                arrayList.add(new dd(method, dgVar, cls3));
                            }
                        } else {
                            continue;
                        }
                    } else if (!b.containsKey(cls2)) {
                        Logger.d(Logger.EVENT_TAG, "Skipping method (not public, static or abstract): " + cls2 + "." + name2);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            throw new cx("Subscriber " + cls + " has no public methods called " + str);
        }
        synchronized (a) {
            a.put(str2, arrayList);
        }
        return arrayList;
    }
}
