package com.chartboost.sdk.impl;

public final class cc {
    private final boolean a;
    private final boolean b;
    private final int c;
    private final int d;
    private final boolean e;
    private final boolean f;
    private final int g;
    private final int h;
    private final boolean i;

    private cc(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, int i3, int i4, boolean z5) {
        this.a = z;
        this.b = z2;
        this.c = i;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.g = i3;
        this.h = i4;
        this.i = z5;
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public boolean i() {
        return this.i;
    }

    public static cc a(cw cwVar) {
        boolean z = false;
        boolean z2 = false;
        int i = -1;
        int i2 = -1;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z5 = false;
        int i5 = 0;
        while (i5 < cwVar.a()) {
            if (cwVar.a(i5).equalsIgnoreCase("Cache-Control") || cwVar.a(i5).equalsIgnoreCase("Pragma")) {
                String b = cwVar.b(i5);
                boolean z6 = z;
                int i6 = 0;
                while (i6 < b.length()) {
                    String str;
                    int a = cv.a(b, i6, "=,;");
                    String trim = b.substring(i6, a).trim();
                    if (a == b.length() || b.charAt(a) == ',' || b.charAt(a) == ';') {
                        i6 = a + 1;
                        str = null;
                    } else {
                        i6 = cv.a(b, a + 1);
                        String trim2;
                        if (i6 >= b.length() || b.charAt(i6) != '\"') {
                            a = cv.a(b, i6, ",;");
                            trim2 = b.substring(i6, a).trim();
                            i6 = a;
                            str = trim2;
                        } else {
                            i6++;
                            a = cv.a(b, i6, "\"");
                            trim2 = b.substring(i6, a);
                            i6 = a + 1;
                            str = trim2;
                        }
                    }
                    if ("no-cache".equalsIgnoreCase(trim)) {
                        z6 = true;
                    } else if ("no-store".equalsIgnoreCase(trim)) {
                        z2 = true;
                    } else if ("max-age".equalsIgnoreCase(trim)) {
                        i = cv.a(str);
                    } else if ("s-maxage".equalsIgnoreCase(trim)) {
                        i2 = cv.a(str);
                    } else if ("public".equalsIgnoreCase(trim)) {
                        z3 = true;
                    } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                        z4 = true;
                    } else if ("max-stale".equalsIgnoreCase(trim)) {
                        i3 = cv.a(str);
                    } else if ("min-fresh".equalsIgnoreCase(trim)) {
                        i4 = cv.a(str);
                    } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                        z5 = true;
                    }
                }
                z = z6;
            }
            i5++;
        }
        return new cc(z, z2, i, i2, z3, z4, i3, i4, z5);
    }
}
