package com.chartboost.sdk.impl;

import com.chartboost.sdk.impl.dg.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class df {
    static final String a = cr.a().b();
    public static final String b = (a + "-Sent-Millis");
    public static final String c = (a + "-Received-Millis");
    public static final String d = (a + "-Response-Source");
    public static final String e = (a + "-Selected-Protocol");
    private static final Comparator<String> f = new Comparator<String>() {
        public /* synthetic */ int compare(Object x0, Object x1) {
            return a((String) x0, (String) x1);
        }

        public int a(String str, String str2) {
            if (str == str2) {
                return 0;
            }
            if (str == null) {
                return -1;
            }
            if (str2 == null) {
                return 1;
            }
            return String.CASE_INSENSITIVE_ORDER.compare(str, str2);
        }
    };

    public static long a(dg dgVar) {
        return a(dgVar.d());
    }

    public static long a(di diVar) {
        return a(diVar.g());
    }

    public static long a(cw cwVar) {
        return a(cwVar.a("Content-Length"));
    }

    private static long a(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static Map<String, List<String>> a(cw cwVar, String str) {
        Map treeMap = new TreeMap(f);
        for (int i = 0; i < cwVar.a(); i++) {
            String a = cwVar.a(i);
            String b = cwVar.b(i);
            List arrayList = new ArrayList();
            List list = (List) treeMap.get(a);
            if (list != null) {
                arrayList.addAll(list);
            }
            arrayList.add(b);
            treeMap.put(a, Collections.unmodifiableList(arrayList));
        }
        if (str != null) {
            treeMap.put(null, Collections.unmodifiableList(Collections.singletonList(str)));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    public static void a(b bVar, Map<String, List<String>> map) {
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (("Cookie".equalsIgnoreCase(str) || "Cookie2".equalsIgnoreCase(str)) && !((List) entry.getValue()).isEmpty()) {
                bVar.b(str, a((List) entry.getValue()));
            }
        }
    }

    private static String a(List<String> list) {
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            stringBuilder.append((String) list.get(i));
        }
        return stringBuilder.toString();
    }
}
