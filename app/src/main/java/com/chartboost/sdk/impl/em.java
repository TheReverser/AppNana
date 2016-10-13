package com.chartboost.sdk.impl;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.util.List;

public final class em implements ew {
    private static final ds a = ds.a("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    static final class a implements ee {
        int a;
        byte b;
        int c;
        int d;
        private final dr e;

        public a(dr drVar) {
            this.e = drVar;
        }

        public long b(dx dxVar, long j) throws IOException {
            while (this.d == 0) {
                if ((this.b & 4) != 0) {
                    return -1;
                }
                a();
            }
            long b = this.e.b(dxVar, Math.min(j, (long) this.d));
            if (b == -1) {
                return -1;
            }
            this.d = (int) (((long) this.d) - b);
            return b;
        }

        public void close() throws IOException {
        }

        private void a() throws IOException {
            int i = this.c;
            int i2 = this.e.i();
            int i3 = this.e.i();
            short s = (short) ((1073676288 & i2) >> 16);
            this.d = s;
            this.a = s;
            byte b = (byte) ((65280 & i2) >> 8);
            this.b = (byte) (i2 & 255);
            this.c = Integer.MAX_VALUE & i3;
            if (b != (byte) 10) {
                throw em.d("%s != TYPE_CONTINUATION", Byte.valueOf(b));
            } else if (this.c != i) {
                throw em.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    static final class b implements eh {
        final a a;
        private final dr b;
        private final a c = new a(this.b);
        private final boolean d;

        b(dr drVar, int i, boolean z) {
            this.b = drVar;
            this.d = z;
            this.a = new a(z, i, this.c);
        }

        public void a() throws IOException {
            if (!this.d) {
                if (!em.a.equals(this.b.c((long) em.a.e()))) {
                    throw em.d("Expected a connection header but was %s", this.b.c((long) em.a.e()).a());
                }
            }
        }

        public boolean a(com.chartboost.sdk.impl.eh.a aVar) throws IOException {
            try {
                int i = this.b.i();
                short s = (short) ((1073676288 & i) >> 16);
                byte b = (byte) ((65280 & i) >> 8);
                byte b2 = (byte) (i & 255);
                int i2 = this.b.i() & Integer.MAX_VALUE;
                switch (b) {
                    case Gender.MALE /*0*/:
                        b(aVar, s, b2, i2);
                        break;
                    case Gender.FEMALE /*1*/:
                        a(aVar, s, b2, i2);
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        c(aVar, s, b2, i2);
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                        d(aVar, s, b2, i2);
                        break;
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        e(aVar, s, b2, i2);
                        break;
                    case Logger.WARN_LOGGING_LEVEL /*5*/:
                        f(aVar, s, b2, i2);
                        break;
                    case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                        g(aVar, s, b2, i2);
                        break;
                    case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                        h(aVar, s, b2, i2);
                        break;
                    case HapticPlaybackThread.HAPTIC_QUIT_PLAYBACK /*9*/:
                        i(aVar, s, b2, i2);
                        break;
                    default:
                        this.b.b((long) s);
                        break;
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        private void a(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (i == 0) {
                throw em.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            int i2;
            boolean z = (b & 1) != 0;
            if ((b & 8) != 0) {
                i2 = this.b.i() & Integer.MAX_VALUE;
                s = (short) (s - 4);
            } else {
                i2 = -1;
            }
            aVar.a(false, z, i, -1, i2, a(s, b, i), ek.HTTP_20_HEADERS);
        }

        private List<ej> a(short s, byte b, int i) throws IOException {
            a aVar = this.c;
            this.c.d = s;
            aVar.a = s;
            this.c.b = b;
            this.c.c = i;
            this.a.a();
            this.a.b();
            return this.a.c();
        }

        private void b(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            aVar.a((b & 1) != 0, i, this.b, s);
        }

        private void c(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (s != (short) 4) {
                throw em.d("TYPE_PRIORITY length: %d != 4", Short.valueOf(s));
            } else if (i == 0) {
                throw em.d("TYPE_PRIORITY streamId == 0", new Object[0]);
            } else {
                aVar.a(i, this.b.i() & Integer.MAX_VALUE);
            }
        }

        private void d(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (s != (short) 4) {
                throw em.d("TYPE_RST_STREAM length: %d != 4", Short.valueOf(s));
            } else if (i == 0) {
                throw em.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
            } else {
                eg b2 = eg.b(this.b.i());
                if (b2 == null) {
                    throw em.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r0));
                } else {
                    aVar.a(i, b2);
                }
            }
        }

        private void e(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (i != 0) {
                throw em.d("TYPE_SETTINGS streamId != 0", new Object[0]);
            } else if ((b & 1) != 0) {
                if (s != (short) 0) {
                    throw em.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                aVar.b();
            } else if (s % 8 != 0) {
                throw em.d("TYPE_SETTINGS length %% 8 != 0: %s", Short.valueOf(s));
            } else {
                es esVar = new es();
                for (short s2 = (short) 0; s2 < s; s2 += 8) {
                    esVar.a(this.b.i() & 16777215, 0, this.b.i());
                }
                aVar.a(false, esVar);
                if (esVar.c() >= 0) {
                    this.a.a(esVar.c());
                }
            }
        }

        private void f(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (i == 0) {
                throw em.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            aVar.a(i, this.b.i() & Integer.MAX_VALUE, a((short) (s - 4), b, i));
        }

        private void g(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            boolean z = true;
            if (s != (short) 8) {
                throw em.d("TYPE_PING length != 8: %s", Short.valueOf(s));
            } else if (i != 0) {
                throw em.d("TYPE_PING streamId != 0", new Object[0]);
            } else {
                int i2 = this.b.i();
                int i3 = this.b.i();
                if ((b & 1) == 0) {
                    z = false;
                }
                aVar.a(z, i2, i3);
            }
        }

        private void h(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (s < (short) 8) {
                throw em.d("TYPE_GOAWAY length < 8: %s", Short.valueOf(s));
            } else if (i != 0) {
                throw em.d("TYPE_GOAWAY streamId != 0", new Object[0]);
            } else {
                int i2 = this.b.i();
                int i3 = s - 8;
                eg b2 = eg.b(this.b.i());
                if (b2 == null) {
                    throw em.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r0));
                }
                ds dsVar = ds.a;
                if (i3 > 0) {
                    dsVar = this.b.c((long) i3);
                }
                aVar.a(i2, b2, dsVar);
            }
        }

        private void i(com.chartboost.sdk.impl.eh.a aVar, short s, byte b, int i) throws IOException {
            if (s != (short) 4) {
                throw em.d("TYPE_WINDOW_UPDATE length !=4: %s", Short.valueOf(s));
            }
            long i2 = (long) (this.b.i() & Integer.MAX_VALUE);
            if (i2 == 0) {
                throw em.d("windowSizeIncrement was 0", Long.valueOf(i2));
            } else {
                aVar.a(i, i2);
            }
        }

        public void close() throws IOException {
            this.b.close();
        }
    }

    static final class c implements ei {
        private final dq a;
        private final boolean b;
        private final dx c = new dx();
        private final b d = new b(this.c);
        private boolean e;

        c(dq dqVar, boolean z) {
            this.a = dqVar;
            this.b = z;
        }

        public synchronized void c() throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            this.a.a();
        }

        public synchronized void b() throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            a(0, (byte) 4, (byte) 1, 0);
            this.a.a();
        }

        public synchronized void a() throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (this.b) {
                this.a.a(em.a.f());
                this.a.a();
            }
        }

        public synchronized void a(boolean z, boolean z2, int i, int i2, int i3, int i4, List<ej> list) throws IOException {
            if (z2) {
                throw new UnsupportedOperationException();
            } else if (this.e) {
                throw new IOException("closed");
            } else {
                a(z, i, i3, (List) list);
            }
        }

        public synchronized void a(int i, int i2, List<ej> list) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (this.c.l() != 0) {
                throw new IllegalStateException();
            } else {
                this.d.a((List) list);
                a((int) (4 + this.c.l()), (byte) 5, (byte) 4, i);
                this.a.b(Integer.MAX_VALUE & i2);
                this.a.a(this.c, this.c.l());
            }
        }

        private void a(boolean z, int i, int i2, List<ej> list) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (this.c.l() != 0) {
                throw new IllegalStateException();
            } else {
                this.d.a((List) list);
                int l = (int) this.c.l();
                byte b = (byte) 4;
                if (z) {
                    b = (byte) 5;
                }
                if (i2 != -1) {
                    b = (byte) (b | 8);
                }
                if (i2 != -1) {
                    l += 4;
                }
                a(l, (byte) 1, b, i);
                if (i2 != -1) {
                    this.a.b(Integer.MAX_VALUE & i2);
                }
                this.a.a(this.c, this.c.l());
            }
        }

        public synchronized void a(int i, eg egVar) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (egVar.p == -1) {
                throw new IllegalArgumentException();
            } else {
                a(4, (byte) 3, (byte) 0, i);
                this.a.b(egVar.o);
                this.a.a();
            }
        }

        public synchronized void a(boolean z, int i, dx dxVar, int i2) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            byte b = (byte) 0;
            if (z) {
                b = (byte) 1;
            }
            a(i, b, dxVar, i2);
        }

        void a(int i, byte b, dx dxVar, int i2) throws IOException {
            a(i2, (byte) 0, b, i);
            if (i2 > 0) {
                this.a.a(dxVar, (long) i2);
            }
        }

        public synchronized void a(es esVar) throws IOException {
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                a(esVar.b() * 8, (byte) 4, (byte) 0, 0);
                for (int i = 0; i < 10; i++) {
                    if (esVar.a(i)) {
                        this.a.b(16777215 & i);
                        this.a.b(esVar.b(i));
                    }
                }
                this.a.a();
            }
        }

        public synchronized void a(boolean z, int i, int i2) throws IOException {
            byte b = (byte) 0;
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                if (z) {
                    b = (byte) 1;
                }
                a(8, (byte) 6, b, 0);
                this.a.b(i);
                this.a.b(i2);
                this.a.a();
            }
        }

        public synchronized void a(int i, eg egVar, byte[] bArr) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (egVar.o == -1) {
                throw em.c("errorCode.httpCode == -1", new Object[0]);
            } else {
                a(bArr.length + 8, (byte) 7, (byte) 0, 0);
                this.a.b(i);
                this.a.b(egVar.o);
                if (bArr.length > 0) {
                    this.a.a(bArr);
                }
                this.a.a();
            }
        }

        public synchronized void a(int i, long j) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw em.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            } else {
                a(4, (byte) 9, (byte) 0, i);
                this.a.b((int) j);
                this.a.a();
            }
        }

        public synchronized void close() throws IOException {
            this.e = true;
            this.a.close();
        }

        void a(int i, byte b, byte b2, int i2) throws IOException {
            if (i > 16383) {
                throw em.c("FRAME_SIZE_ERROR length > 16383: %s", Integer.valueOf(i));
            } else if ((Integer.MIN_VALUE & i2) != 0) {
                throw em.c("reserved bit set: %s", Integer.valueOf(i2));
            } else {
                this.a.b((((i & 16383) << 16) | ((b & 255) << 8)) | (b2 & 255));
                this.a.b(Integer.MAX_VALUE & i2);
            }
        }
    }

    public eh a(dr drVar, boolean z) {
        return new b(drVar, 4096, z);
    }

    public ei a(dq dqVar, boolean z) {
        return new c(dqVar, z);
    }

    public int a() {
        return 16383;
    }

    private static IllegalArgumentException c(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    private static IOException d(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }
}
