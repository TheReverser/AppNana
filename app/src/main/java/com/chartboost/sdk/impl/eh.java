package com.chartboost.sdk.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface eh extends Closeable {

    public interface a {
        void a(int i, int i2);

        void a(int i, int i2, List<ej> list) throws IOException;

        void a(int i, long j);

        void a(int i, eg egVar);

        void a(int i, eg egVar, ds dsVar);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, dr drVar, int i2) throws IOException;

        void a(boolean z, es esVar);

        void a(boolean z, boolean z2, int i, int i2, int i3, List<ej> list, ek ekVar);

        void b();
    }

    void a() throws IOException;

    boolean a(a aVar) throws IOException;
}
