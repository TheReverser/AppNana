package com.chartboost.sdk.impl;

import java.io.IOException;
import java.util.List;

public interface er {
    public static final er a = new er() {
        public boolean a(int i, List<ej> list) {
            return true;
        }

        public boolean a(int i, List<ej> list, boolean z) {
            return true;
        }

        public boolean a(int i, dr drVar, int i2, boolean z) throws IOException {
            drVar.b((long) i2);
            return true;
        }

        public void a(int i, eg egVar) {
        }
    };

    void a(int i, eg egVar);

    boolean a(int i, dr drVar, int i2, boolean z) throws IOException;

    boolean a(int i, List<ej> list);

    boolean a(int i, List<ej> list, boolean z);
}
