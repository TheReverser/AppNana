package com.chartboost.sdk.impl;

import com.facebook.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class cw {
    private final String[] a;

    public static class a {
        private final List<String> a = new ArrayList(20);

        public a a(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return c(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return c(BuildConfig.VERSION_NAME, str.substring(1));
            }
            return c(BuildConfig.VERSION_NAME, str);
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("fieldname == null");
            } else if (str2 == null) {
                throw new IllegalArgumentException("value == null");
            } else if (str.length() != 0 && str.indexOf(0) == -1 && str2.indexOf(0) == -1) {
                return c(str, str2);
            } else {
                throw new IllegalArgumentException("Unexpected header: " + str + ": " + str2);
            }
        }

        private a c(String str, String str2) {
            this.a.add(str);
            this.a.add(str2.trim());
            return this;
        }

        public a b(String str) {
            for (int i = 0; i < this.a.size(); i += 2) {
                if (str.equalsIgnoreCase((String) this.a.get(i))) {
                    this.a.remove(i);
                    this.a.remove(i);
                }
            }
            return this;
        }

        public a b(String str, String str2) {
            b(str);
            a(str, str2);
            return this;
        }

        public String c(String str) {
            for (int size = this.a.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase((String) this.a.get(size))) {
                    return (String) this.a.get(size + 1);
                }
            }
            return null;
        }

        public cw a() {
            return new cw();
        }
    }

    private cw(a aVar) {
        this.a = (String[]) aVar.a.toArray(new String[aVar.a.size()]);
    }

    public String a(String str) {
        return a(this.a, str);
    }

    public int a() {
        return this.a.length / 2;
    }

    public String a(int i) {
        int i2 = i * 2;
        if (i2 < 0 || i2 >= this.a.length) {
            return null;
        }
        return this.a[i2];
    }

    public String b(int i) {
        int i2 = (i * 2) + 1;
        if (i2 < 0 || i2 >= this.a.length) {
            return null;
        }
        return this.a[i2];
    }

    public a b() {
        a aVar = new a();
        aVar.a.addAll(Arrays.asList(this.a));
        return aVar;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a(); i++) {
            stringBuilder.append(a(i)).append(": ").append(b(i)).append("\n");
        }
        return stringBuilder.toString();
    }

    private static String a(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }
}
