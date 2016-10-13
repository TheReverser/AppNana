package com.vungle.publisher.net.http;

import com.facebook.internal.Utility;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.at.b;
import com.vungle.publisher.bo;
import com.vungle.publisher.cf;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.j;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public class DownloadHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    String a;
    String b;
    b c;
    @Inject
    AdPreparer d;
    @Inject
    EventBus e;

    @Singleton
    /* compiled from: vungle */
    static class Factory {
        @Inject
        Provider<DownloadHttpResponseHandler> a;

        Factory() {
        }
    }

    DownloadHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, cf cfVar) throws IOException, JSONException {
        InputStream inputStream;
        Throwable th;
        HttpRequest httpRequest;
        OutputStream outputStream = null;
        try {
            HttpURLConnection httpURLConnection = cfVar.a;
            inputStream = httpURLConnection.getInputStream();
            try {
                OutputStream fileOutputStream;
                File file = new File(this.b);
                if (bo.b(file)) {
                    int read;
                    Logger.d(Logger.NETWORK_TAG, "downloading to: " + this.b);
                    byte[] bArr = new byte[Utility.DEFAULT_STREAM_BUFFER_SIZE];
                    fileOutputStream = new FileOutputStream(file);
                    int i = 0;
                    while (true) {
                        try {
                            read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            outputStream = fileOutputStream;
                            th = th3;
                        }
                    }
                    fileOutputStream.flush();
                    read = httpURLConnection.getContentLength();
                    if (read < 0 || read == i) {
                        Logger.d(Logger.NETWORK_TAG, "download complete: " + this.b + ", size: " + i);
                        AdPreparer adPreparer = this.d;
                        String str = this.a;
                        b bVar = this.c;
                        Logger.d(Logger.PREPARE_TAG, "process " + bVar + " request for ad " + str);
                        adPreparer.c.a(adPreparer.b.a(str, bVar, Integer.valueOf(i)), ScheduledPriorityExecutor.b.prepareLocalAd);
                    } else {
                        Logger.w(Logger.NETWORK_TAG, "download size mismatch: " + this.b + ", expected size: " + read + ", actual size: " + i);
                        b(httpTransaction, cfVar);
                    }
                } else {
                    Logger.w(Logger.NETWORK_TAG, "could not create or directory not writeable: " + file);
                    b(httpTransaction, cfVar);
                    fileOutputStream = null;
                }
                HttpRequest httpRequest2 = httpTransaction.a;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest2 + ": error closing input stream", e);
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest2 + ": error closing output stream", th4);
                    }
                }
            } catch (Throwable th5) {
                th4 = th5;
                httpRequest = httpTransaction.a;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest + ": error closing input stream", e2);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th22) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest + ": error closing output stream", th22);
                    }
                }
                throw th4;
            }
        } catch (Throwable th6) {
            th4 = th6;
            inputStream = null;
            httpRequest = httpTransaction.a;
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th4;
        }
    }

    protected final void b(HttpTransaction httpTransaction, cf cfVar) {
        this.e.a(new j());
    }
}
