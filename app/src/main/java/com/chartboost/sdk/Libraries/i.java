package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import com.chartboost.sdk.d;
import com.chartboost.sdk.d.b;
import com.chartboost.sdk.impl.bb;
import com.facebook.internal.NativeProtocol;
import java.io.File;

public final class i implements b {
    private static Handler a = new Handler();
    private a b;
    private d c;
    private float d = 1.0f;
    private bb.b e = new bb.b(this) {
        final /* synthetic */ i a;

        {
            this.a = r1;
        }

        public void a(a aVar, Bundle bundle) {
            this.a.b = aVar;
            this.a.c.a(this.a);
        }
    };

    public static class a {
        private int a;
        private String b;
        private File c;
        private Bitmap d;
        private g e;
        private int f = -1;
        private int g = -1;

        public a(String str, File file, g gVar) {
            this.c = file;
            this.b = str;
            this.d = null;
            this.a = 1;
            this.e = gVar;
        }

        public Bitmap a() {
            if (this.d == null) {
                b();
            }
            return this.d;
        }

        public void b() {
            if (this.d == null) {
                CBLogging.a("MemoryBitmap", "Loading image '" + this.b + "' from cache");
                byte[] b = this.e.b(this.c);
                if (b == null) {
                    CBLogging.b("MemoryBitmap", "decode() - bitmap not found");
                    return;
                }
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(b, 0, b.length, options);
                Options options2 = new Options();
                options2.inJustDecodeBounds = false;
                options2.inDither = false;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                options2.inTempStorage = new byte[32768];
                options2.inSampleSize = 1;
                while (options2.inSampleSize < 32) {
                    try {
                        this.d = BitmapFactory.decodeByteArray(b, 0, b.length, options2);
                        break;
                    } catch (Throwable e) {
                        CBLogging.b("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", e);
                        options2.inSampleSize *= 2;
                    } catch (Throwable e2) {
                        CBLogging.b("MemoryBitmap", "Exception raised decoding bitmap", e2);
                    }
                }
                this.a = options2.inSampleSize;
            }
        }

        public void c() {
            try {
                if (!(this.d == null || this.d.isRecycled())) {
                    this.d.recycle();
                }
            } catch (Exception e) {
            }
            this.d = null;
        }

        public int d() {
            if (this.d != null) {
                return this.d.getWidth();
            }
            if (this.f >= 0) {
                return this.f;
            }
            f();
            return this.f;
        }

        public int e() {
            if (this.d != null) {
                return this.d.getHeight();
            }
            if (this.g >= 0) {
                return this.g;
            }
            f();
            return this.g;
        }

        private void f() {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.c.getAbsolutePath(), options);
                this.f = options.outWidth;
                this.g = options.outHeight;
            } catch (Throwable e) {
                CBLogging.b("MemoryBitmap", "Error decoding file size", e);
            }
        }
    }

    public i(d dVar) {
        this.c = dVar;
    }

    public int b() {
        return this.b.d() * this.b.a;
    }

    public int c() {
        return this.b.e() * this.b.a;
    }

    public void a(String str) {
        a(this.c.g(), str, new Bundle());
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, String str, final Bundle bundle) {
        com.chartboost.sdk.Libraries.e.a a = aVar.a(str);
        if (!a.b()) {
            this.c.b(this);
            final String e = a.e(NativeProtocol.WEB_DIALOG_URL);
            final String e2 = a.e("checksum");
            this.d = a.a("scale").a(1.0f);
            a.post(new Runnable(this) {
                final /* synthetic */ i d;

                public void run() {
                    bb.a().a(e, e2, this.d.e, null, bundle == null ? new Bundle() : bundle);
                }
            });
        }
    }

    public boolean a() {
        return e();
    }

    public void d() {
        if (this.b != null) {
            this.b.c();
        }
    }

    public boolean e() {
        return this.b != null;
    }

    public Bitmap f() {
        return this.b != null ? this.b.a() : null;
    }

    public float g() {
        return this.d;
    }
}
