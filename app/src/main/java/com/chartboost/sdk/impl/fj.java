package com.chartboost.sdk.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class fj extends LinkedHashMap<String, Object> implements fg {
    public /* synthetic */ Object put(Object x0, Object x1) {
        return a((String) x0, x1);
    }

    public fj(String str, Object obj) {
        a(str, obj);
    }

    public boolean b(String str) {
        return super.containsKey(str);
    }

    public Object a(String str) {
        return super.get(str);
    }

    public Object a(String str, Object obj) {
        return super.put(str, obj);
    }

    public void putAll(Map m) {
        for (Entry entry : m.entrySet()) {
            a(entry.getKey().toString(), entry.getValue());
        }
    }

    public String toString() {
        return bx.a(this);
    }

    public boolean equals(Object o) {
        if (!(o instanceof fg)) {
            return false;
        }
        fg fgVar = (fg) o;
        if (!keySet().equals(fgVar.keySet())) {
            return false;
        }
        for (String str : keySet()) {
            Object a = a(str);
            Object a2 = fgVar.a(str);
            if (a == null && a2 != null) {
                return false;
            }
            if (a2 == null) {
                if (a != null) {
                    return false;
                }
            } else if ((a instanceof Number) && (a2 instanceof Number)) {
                if (((Number) a).doubleValue() != ((Number) a2).doubleValue()) {
                    return false;
                }
            } else if ((a instanceof Pattern) && (a2 instanceof Pattern)) {
                Pattern pattern = (Pattern) a;
                Pattern pattern2 = (Pattern) a2;
                if (!pattern.pattern().equals(pattern2.pattern()) || pattern.flags() != pattern2.flags()) {
                    return false;
                }
            } else if (!a.equals(a2)) {
                return false;
            }
        }
        return true;
    }
}
