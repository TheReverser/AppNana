package com.chartboost.sdk.impl;

class bw extends bv {
    private ga<ca> a = new ga();

    bw() {
    }

    void a(Class cls, ca caVar) {
        this.a.a(cls, caVar);
    }

    public void a(Object obj, StringBuilder stringBuilder) {
        Object a = fd.a(obj);
        if (a == null) {
            stringBuilder.append(" null ");
            return;
        }
        ca caVar = null;
        for (Object a2 : ga.a(a.getClass())) {
            caVar = (ca) this.a.a(a2);
            if (caVar != null) {
                break;
            }
        }
        if (caVar == null && a.getClass().isArray()) {
            caVar = (ca) this.a.a((Object) Object[].class);
        }
        if (caVar == null) {
            throw new RuntimeException("json can't serialize type : " + a.getClass());
        }
        caVar.a(a, stringBuilder);
    }
}
