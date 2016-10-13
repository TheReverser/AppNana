package com.chartboost.sdk.impl;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.g;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public final class bb {
    private static volatile bb c = null;
    private static Handler d = new Handler();
    private g a = new g("CBImagesDirectory", true);
    private Map<String, com.chartboost.sdk.Libraries.i.a> b = new HashMap();

    public interface b {
        void a(com.chartboost.sdk.Libraries.i.a aVar, Bundle bundle);
    }

    private class a implements Runnable {
        final /* synthetic */ bb a;
        private String b;
        private final WeakReference<ImageView> c;
        private b d;
        private String e;
        private Bundle f;

        public a(bb bbVar, ImageView imageView, b bVar, String str, Bundle bundle, String str2) {
            this.a = bbVar;
            this.c = new WeakReference(imageView);
            Drawable cVar = new c(this);
            if (imageView != null) {
                imageView.setImageDrawable(cVar);
            }
            this.e = str;
            this.d = bVar;
            this.f = bundle;
            this.b = str2;
        }

        public void run() {
            HttpResponse execute;
            HttpEntity entity;
            Throwable e;
            HttpResponse httpResponse;
            InputStream inputStream = null;
            if (this.a.b(this.e)) {
                a();
                return;
            }
            HttpClient b = aw.b();
            HttpGet httpGet = new HttpGet(this.b);
            CBLogging.a("CBWebImageCache", "downloading image to cache... " + this.b);
            try {
                execute = b.execute(httpGet);
                try {
                    int statusCode = execute.getStatusLine().getStatusCode();
                    if (statusCode != 200) {
                        CBLogging.d("CBWebImageCache:ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + this.b);
                        CBUtility.a(execute);
                        a();
                        return;
                    }
                    entity = execute.getEntity();
                    if (entity != null) {
                        inputStream = entity.getContent();
                        byte[] b2 = fa.b(inputStream);
                        if (com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(b2)).equals(this.e)) {
                            this.a.a.a(String.format("%s%s", new Object[]{this.e, ".png"}), b2);
                            this.a.a(this.e);
                            if (inputStream != null) {
                                inputStream.close();
                            } else {
                                CBUtility.a(entity);
                            }
                        } else {
                            CBLogging.d("CBWebImageCache:ImageDownloader", "Error: checksum did not match while downloading from " + this.b);
                            a();
                            if (inputStream != null) {
                                inputStream.close();
                                return;
                            } else {
                                CBUtility.a(entity);
                                return;
                            }
                        }
                    }
                    a();
                } catch (IOException e2) {
                    e = e2;
                    httpResponse = execute;
                    httpGet.abort();
                    CBUtility.a(httpResponse);
                    CBLogging.d("CBWebImageCache", "I/O error while retrieving bitmap from " + this.b, e);
                    a();
                } catch (IllegalStateException e3) {
                    e = e3;
                    httpGet.abort();
                    CBUtility.a(execute);
                    CBLogging.d("CBWebImageCache", "Incorrect URL: " + this.b, e);
                    a();
                } catch (Throwable th) {
                    e = th;
                    httpGet.abort();
                    CBUtility.a(execute);
                    CBLogging.d("CBWebImageCache", "Error while retrieving bitmap from " + this.b, e);
                    a();
                }
            } catch (IOException e4) {
                e = e4;
                httpGet.abort();
                CBUtility.a(httpResponse);
                CBLogging.d("CBWebImageCache", "I/O error while retrieving bitmap from " + this.b, e);
                a();
            } catch (IllegalStateException e5) {
                e = e5;
                execute = null;
                httpGet.abort();
                CBUtility.a(execute);
                CBLogging.d("CBWebImageCache", "Incorrect URL: " + this.b, e);
                a();
            } catch (Throwable th2) {
                e = th2;
                execute = null;
                httpGet.abort();
                CBUtility.a(execute);
                CBLogging.d("CBWebImageCache", "Error while retrieving bitmap from " + this.b, e);
                a();
            }
        }

        public void a() {
            final com.chartboost.sdk.Libraries.i.a b = b();
            if (!(b == null || this.c == null || this.c.get() == null || this != bb.b((ImageView) this.c.get()))) {
                b.b();
            }
            bb.d.post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.c != null) {
                        ImageView imageView = (ImageView) this.b.c.get();
                        a a = bb.b(imageView);
                        if (b != null && this.b == a) {
                            imageView.setImageBitmap(b.a());
                        }
                    }
                    if (this.b.d != null) {
                        this.b.d.a(b, this.b.f);
                    }
                }
            });
        }

        private com.chartboost.sdk.Libraries.i.a b() {
            return (com.chartboost.sdk.Libraries.i.a) this.a.b.get(this.e);
        }
    }

    static class c extends BitmapDrawable {
        private final WeakReference<a> a;

        public c(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public a a() {
            return (a) this.a.get();
        }
    }

    public static bb a() {
        if (c == null) {
            synchronized (bb.class) {
                if (c == null) {
                    c = new bb();
                }
            }
        }
        return c;
    }

    private bb() {
    }

    public void b() {
        this.a.b();
        this.b.clear();
    }

    public void a(String str, String str2, b bVar, ImageView imageView, Bundle bundle) {
        com.chartboost.sdk.Libraries.i.a a = a(str2);
        if (a != null) {
            if (imageView != null) {
                imageView.setImageBitmap(a.a());
            }
            if (bVar != null) {
                bVar.a(a, bundle);
                return;
            }
            return;
        }
        aw.a().execute(new a(this, imageView, bVar, str2, bundle, str));
    }

    private com.chartboost.sdk.Libraries.i.a a(String str) {
        if (!b(str)) {
            if (this.b.containsKey(str)) {
                this.b.remove(str);
            }
            return null;
        } else if (this.b.containsKey(str)) {
            return (com.chartboost.sdk.Libraries.i.a) this.b.get(str);
        } else {
            com.chartboost.sdk.Libraries.i.a aVar = new com.chartboost.sdk.Libraries.i.a(str, this.a.c(String.format("%s%s", new Object[]{str, ".png"})), this.a);
            this.b.put(str, aVar);
            return aVar;
        }
    }

    private static a b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof c) {
                return ((c) drawable).a();
            }
        }
        return null;
    }

    private boolean b(String str) {
        return this.a.b(String.format("%s%s", new Object[]{str, ".png"}));
    }
}
