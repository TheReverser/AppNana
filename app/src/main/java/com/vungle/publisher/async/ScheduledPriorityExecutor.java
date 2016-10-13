package com.vungle.publisher.async;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ScheduledPriorityExecutor {
    private final a a;
    private final c b;
    private final c c;
    private final c d;
    private final BlockingQueue<Runnable> e = new PriorityBlockingQueue();

    /* compiled from: vungle */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[b.values().length];

        static {
            try {
                a[b.clientEvent.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.externalNetworkRequest.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* compiled from: vungle */
    class a extends Handler {
        final /* synthetic */ ScheduledPriorityExecutor a;

        /* compiled from: vungle */
        class a implements Comparable<a>, Runnable {
            final /* synthetic */ a a;
            private final Runnable b;
            private final long c;
            private final b d;

            public final /* bridge */ /* synthetic */ int compareTo(Object x0) {
                return this.d.compareTo(((a) x0).d);
            }

            a(a aVar, Runnable runnable, b bVar) {
                this(aVar, runnable, bVar, (byte) 0);
            }

            private a(a aVar, Runnable runnable, b bVar, byte b) {
                this.a = aVar;
                this.b = runnable;
                this.c = -1;
                this.d = bVar;
            }

            public final void run() {
                try {
                    this.b.run();
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e) {
                        Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e);
                    }
                } catch (Throwable e2) {
                    Logger.e(Logger.ASYNC_TAG, "error executing " + this, e2);
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e22) {
                        Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e22);
                    }
                } catch (Throwable th) {
                    try {
                        if (this.c > 0) {
                            this.a.postDelayed(this, this.c);
                        }
                    } catch (Throwable e3) {
                        Logger.e(Logger.ASYNC_TAG, "error rescheduling " + this, e3);
                    }
                }
            }

            public final boolean equals(Object object) {
                if (object != null && (object instanceof a)) {
                    if (this.b.equals(((a) object).b)) {
                        return true;
                    }
                }
                return false;
            }

            public final int hashCode() {
                return this.b.hashCode();
            }

            public final String toString() {
                return "{PriorityRunnable:: taskType: " + this.d + ", repeatMillis: " + this.c + "}";
            }
        }

        a(ScheduledPriorityExecutor scheduledPriorityExecutor, Looper looper) {
            this.a = scheduledPriorityExecutor;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Object obj = message.obj;
            if (obj == null || !(obj instanceof a)) {
                Logger.w(Logger.ASYNC_TAG, "unhandled message " + message);
                return;
            }
            c b;
            b a = ((a) obj).d;
            if (a != null) {
                switch (AnonymousClass1.a[a.ordinal()]) {
                    case Gender.FEMALE /*1*/:
                        b = this.a.b;
                        break;
                }
            }
            b = this.a.d;
            Logger.d(Logger.ASYNC_TAG, "processing " + obj);
            Logger.v(Logger.ASYNC_TAG, b + ", max pool size " + b.getMaximumPoolSize() + ", largest pool size " + b.getLargestPoolSize());
            b.execute((Runnable) obj);
        }
    }

    /* compiled from: vungle */
    public enum b {
        deviceId,
        databaseWrite,
        requestStreamingAd,
        reportAd,
        reportInstall,
        requestLocalAd,
        prepareLocalAd,
        downloadLocalAd,
        requestConfig,
        sessionEndTimer,
        sessionEnd,
        sessionStart,
        unfilledAd,
        deleteExpiredAds,
        otherTask,
        externalNetworkRequest,
        clientEvent
    }

    /* compiled from: vungle */
    class c extends ThreadPoolExecutor {
        final /* synthetic */ ScheduledPriorityExecutor a;

        /* compiled from: vungle */
        class AnonymousClass1 implements ThreadFactory {
            int a = 0;
            final /* synthetic */ ScheduledPriorityExecutor b;
            final /* synthetic */ String c;

            AnonymousClass1(ScheduledPriorityExecutor scheduledPriorityExecutor, String str) {
                this.b = scheduledPriorityExecutor;
                this.c = str;
            }

            public final Thread newThread(Runnable runnable) {
                StringBuilder append = new StringBuilder().append(this.c);
                int i = this.a;
                this.a = i + 1;
                String stringBuilder = append.append(i).toString();
                Logger.v(Logger.ASYNC_TAG, "starting " + stringBuilder);
                return new Thread(runnable, stringBuilder);
            }
        }

        c(ScheduledPriorityExecutor scheduledPriorityExecutor, int i, String str) {
            this.a = scheduledPriorityExecutor;
            super(2, 2, 30, TimeUnit.SECONDS, i, new AnonymousClass1(scheduledPriorityExecutor, str));
            allowCoreThreadTimeOut(true);
        }

        protected final void afterExecute(Runnable runnable, Throwable throwable) {
            super.afterExecute(runnable, throwable);
            if (throwable != null) {
                Logger.e(Logger.ASYNC_TAG, throwable);
            }
        }
    }

    @Inject
    ScheduledPriorityExecutor() {
        HandlerThread handlerThread = new HandlerThread("VungleAsyncMasterThread");
        handlerThread.start();
        this.b = new c(this, new LinkedBlockingQueue(), "VungleAsyncClientEventThread-");
        this.b.allowCoreThreadTimeOut(true);
        this.c = new c(this, new LinkedBlockingQueue(), "VungleAsyncExternalNetworkRequestThread-");
        this.c.allowCoreThreadTimeOut(true);
        this.a = new a(this, handlerThread.getLooper());
        this.d = new c(this, this.e, "VungleAsyncMainThread-");
        this.d.allowCoreThreadTimeOut(true);
    }

    public final void a(Runnable runnable, b bVar) {
        this.a.sendMessage(b(runnable, bVar));
    }

    public final void a(Runnable runnable, long j) {
        a(runnable, b.otherTask, j);
    }

    public final void a(Runnable runnable, b bVar, long j) {
        Logger.d(Logger.ASYNC_TAG, "scheduling " + bVar + " delayed " + j + " ms");
        this.a.sendMessageDelayed(b(runnable, bVar), j);
    }

    private Message b(Runnable runnable, b bVar) {
        a aVar = this.a;
        int ordinal = bVar.ordinal();
        aVar.getClass();
        return aVar.obtainMessage(ordinal, new a(aVar, runnable, bVar));
    }

    public final void a(b bVar) {
        this.a.removeMessages(bVar.ordinal());
    }
}
