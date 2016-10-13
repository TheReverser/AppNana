package com.chartboost.sdk.impl;

import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.List;
import java.util.zip.Deflater;

final class et implements ew {
    static final byte[] a;

    static final class a implements eh {
        private final dr a;
        private final boolean b;
        private final ep c = new ep(this.a);

        a(dr drVar, boolean z) {
            this.a = drVar;
            this.b = z;
        }

        public void a() {
        }

        public boolean a(com.chartboost.sdk.impl.eh.a aVar) throws IOException {
            boolean z = false;
            try {
                int i = this.a.i();
                int i2 = this.a.i();
                int i3 = (-16777216 & i2) >>> 24;
                i2 &= 16777215;
                int i4;
                if ((Integer.MIN_VALUE & i) != 0) {
                    int i5 = (2147418112 & i) >>> 16;
                    i4 = 65535 & i;
                    if (i5 != 3) {
                        throw new ProtocolException("version != 3: " + i5);
                    }
                    switch (i4) {
                        case Gender.FEMALE /*1*/:
                            a(aVar, i3, i2);
                            return true;
                        case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                            b(aVar, i3, i2);
                            return true;
                        case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                            c(aVar, i3, i2);
                            return true;
                        case Logger.INFO_LOGGING_LEVEL /*4*/:
                            h(aVar, i3, i2);
                            return true;
                        case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                            f(aVar, i3, i2);
                            return true;
                        case HapticPlaybackThread.PAUSE_AV_FOR_HAPTIC_BUFFERING /*7*/:
                            g(aVar, i3, i2);
                            return true;
                        case HapticPlaybackThread.HAPTIC_DOWNLOAD_ERROR /*8*/:
                            d(aVar, i3, i2);
                            return true;
                        case HapticPlaybackThread.HAPTIC_QUIT_PLAYBACK /*9*/:
                            e(aVar, i3, i2);
                            return true;
                        default:
                            this.a.b((long) i2);
                            return true;
                    }
                }
                i4 = Integer.MAX_VALUE & i;
                if ((i3 & 1) != 0) {
                    z = true;
                }
                aVar.a(z, i4, this.a, i2);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        private void a(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            boolean z;
            boolean z2 = true;
            int i3 = this.a.i();
            int i4 = this.a.i();
            int i5 = i3 & Integer.MAX_VALUE;
            i4 &= Integer.MAX_VALUE;
            int g = (57344 & this.a.g()) >>> 13;
            List a = this.c.a(i2 - 10);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((i & 2) == 0) {
                z2 = false;
            }
            aVar.a(z2, z, i5, i4, g, a, ek.SPDY_SYN_STREAM);
        }

        private void b(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            boolean z;
            int i3 = this.a.i() & Integer.MAX_VALUE;
            List a = this.c.a(i2 - 4);
            if ((i & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            aVar.a(false, z, i3, -1, -1, a, ek.SPDY_REPLY);
        }

        private void c(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_RST_STREAM length: %d != 8", Integer.valueOf(i2));
            }
            int i3 = this.a.i() & Integer.MAX_VALUE;
            eg a = eg.a(this.a.i());
            if (a == null) {
                throw a("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(r1));
            } else {
                aVar.a(i3, a);
            }
        }

        private void d(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            aVar.a(false, false, this.a.i() & Integer.MAX_VALUE, -1, -1, this.c.a(i2 - 4), ek.SPDY_HEADERS);
        }

        private void e(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_WINDOW_UPDATE length: %d != 8", Integer.valueOf(i2));
            }
            int i3 = this.a.i() & Integer.MAX_VALUE;
            long i4 = (long) (this.a.i() & Integer.MAX_VALUE);
            if (i4 == 0) {
                throw a("windowSizeIncrement was 0", Long.valueOf(i4));
            } else {
                aVar.a(i3, i4);
            }
        }

        private void f(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            boolean z = true;
            if (i2 != 4) {
                throw a("TYPE_PING length: %d != 4", Integer.valueOf(i2));
            }
            boolean z2;
            int i3 = this.a.i();
            boolean z3 = this.b;
            if ((i3 & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z3 != z2) {
                z = false;
            }
            aVar.a(z, i3, 0);
        }

        private void g(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            if (i2 != 8) {
                throw a("TYPE_GOAWAY length: %d != 8", Integer.valueOf(i2));
            }
            int i3 = this.a.i() & Integer.MAX_VALUE;
            eg c = eg.c(this.a.i());
            if (c == null) {
                throw a("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(r1));
            } else {
                aVar.a(i3, c, ds.a);
            }
        }

        private void h(com.chartboost.sdk.impl.eh.a aVar, int i, int i2) throws IOException {
            boolean z = true;
            int i3 = this.a.i();
            if (i2 != (i3 * 8) + 4) {
                throw a("TYPE_SETTINGS length: %d != 4 + 8 * %d", Integer.valueOf(i2), Integer.valueOf(i3));
            }
            es esVar = new es();
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = this.a.i();
                int i6 = (-16777216 & i5) >>> 24;
                esVar.a(i5 & 16777215, i6, this.a.i());
            }
            if ((i & 1) == 0) {
                z = false;
            }
            aVar.a(z, esVar);
        }

        private static IOException a(String str, Object... objArr) throws IOException {
            throw new IOException(String.format(str, objArr));
        }

        public void close() throws IOException {
            this.c.a();
        }
    }

    static final class b implements ei {
        private final dq a;
        private final dx b = new dx();
        private final dq c;
        private final boolean d;
        private boolean e;

        b(dq dqVar, boolean z) {
            this.a = dqVar;
            this.d = z;
            Deflater deflater = new Deflater();
            deflater.setDictionary(et.a);
            this.c = dy.a(new du(this.b, deflater));
        }

        public void b() {
        }

        public void a(int i, int i2, List<ej> list) throws IOException {
        }

        public synchronized void a() {
        }

        public synchronized void c() throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            this.a.a();
        }

        public synchronized void a(boolean z, boolean z2, int i, int i2, int i3, int i4, List<ej> list) throws IOException {
            int i5 = 0;
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                a((List) list);
                int l = (int) (10 + this.b.l());
                int i6 = z ? 1 : 0;
                if (z2) {
                    i5 = 2;
                }
                i5 |= i6;
                this.a.b(-2147287039);
                this.a.b(((i5 & 255) << 24) | (l & 16777215));
                this.a.b(i & Integer.MAX_VALUE);
                this.a.b(i2 & Integer.MAX_VALUE);
                this.a.a((((i3 & 7) << 13) | 0) | (i4 & 255));
                this.a.a(this.b, this.b.l());
                this.a.a();
            }
        }

        public synchronized void a(int i, eg egVar) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (egVar.p == -1) {
                throw new IllegalArgumentException();
            } else {
                this.a.b(-2147287037);
                this.a.b(8);
                this.a.b(Integer.MAX_VALUE & i);
                this.a.b(egVar.p);
                this.a.a();
            }
        }

        public synchronized void a(boolean z, int i, dx dxVar, int i2) throws IOException {
            a(i, z ? 1 : 0, dxVar, i2);
        }

        void a(int i, int i2, dx dxVar, int i3) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (((long) i3) > 16777215) {
                throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + i3);
            } else {
                this.a.b(Integer.MAX_VALUE & i);
                this.a.b(((i2 & 255) << 24) | (16777215 & i3));
                if (i3 > 0) {
                    this.a.a(dxVar, (long) i3);
                }
            }
        }

        private void a(List<ej> list) throws IOException {
            if (this.b.l() != 0) {
                throw new IllegalStateException();
            }
            this.c.b(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ds dsVar = ((ej) list.get(i)).h;
                this.c.b(dsVar.e());
                this.c.a(dsVar);
                dsVar = ((ej) list.get(i)).i;
                this.c.b(dsVar.e());
                this.c.a(dsVar);
            }
            this.c.a();
        }

        public synchronized void a(es esVar) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            }
            int b = esVar.b();
            int i = (b * 8) + 4;
            this.a.b(-2147287036);
            this.a.b((i & 16777215) | 0);
            this.a.b(b);
            for (b = 0; b <= 10; b++) {
                if (esVar.a(b)) {
                    this.a.b(((esVar.c(b) & 255) << 24) | (b & 16777215));
                    this.a.b(esVar.b(b));
                }
            }
            this.a.a();
        }

        public synchronized void a(boolean z, int i, int i2) throws IOException {
            boolean z2 = true;
            synchronized (this) {
                if (this.e) {
                    throw new IOException("closed");
                }
                boolean z3;
                boolean z4 = this.d;
                if ((i & 1) == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z4 == z3) {
                    z2 = false;
                }
                if (z != z2) {
                    throw new IllegalArgumentException("payload != reply");
                }
                this.a.b(-2147287034);
                this.a.b(4);
                this.a.b(i);
                this.a.a();
            }
        }

        public synchronized void a(int i, eg egVar, byte[] bArr) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (egVar.q == -1) {
                throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
            } else {
                this.a.b(-2147287033);
                this.a.b(8);
                this.a.b(i);
                this.a.b(egVar.q);
                this.a.a();
            }
        }

        public synchronized void a(int i, long j) throws IOException {
            if (this.e) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + j);
            } else {
                this.a.b(-2147287031);
                this.a.b(8);
                this.a.b(i);
                this.a.b((int) j);
                this.a.a();
            }
        }

        public synchronized void close() throws IOException {
            this.e = true;
            cs.a(this.a, this.c);
        }
    }

    et() {
    }

    static {
        try {
            a = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(cs.e.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

    public eh a(dr drVar, boolean z) {
        return new a(drVar, z);
    }

    public ei a(dq dqVar, boolean z) {
        return new b(dqVar, z);
    }

    public int a() {
        return 16383;
    }
}
