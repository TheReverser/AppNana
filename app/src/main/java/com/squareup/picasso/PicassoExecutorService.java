package com.squareup.picasso;

import android.net.NetworkInfo;
import com.appnana.android.giftcardrewards.R;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.squareup.picasso.Picasso.Priority;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        public PicassoFutureTask(BitmapHunter hunter) {
            super(hunter, null);
            this.hunter = hunter;
        }

        public int compareTo(PicassoFutureTask other) {
            Priority p1 = this.hunter.getPriority();
            Priority p2 = other.hunter.getPriority();
            return p1 == p2 ? this.hunter.sequence - other.hunter.sequence : p2.ordinal() - p1.ordinal();
        }
    }

    PicassoExecutorService() {
        super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new PicassoThreadFactory());
    }

    void adjustThreadCount(NetworkInfo info) {
        if (info == null || !info.isConnectedOrConnecting()) {
            setThreadCount(DEFAULT_THREAD_COUNT);
            return;
        }
        switch (info.getType()) {
            case Gender.MALE /*0*/:
                switch (info.getSubtype()) {
                    case Gender.FEMALE /*1*/:
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        setThreadCount(1);
                        return;
                    case DEFAULT_THREAD_COUNT /*3*/:
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                    case Logger.WARN_LOGGING_LEVEL /*5*/:
                    case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                    case R.styleable.TitlePageIndicator_selectedBold /*12*/:
                        setThreadCount(2);
                        return;
                    case R.styleable.TitlePageIndicator_titlePadding /*13*/:
                    case R.styleable.TitlePageIndicator_topPadding /*14*/:
                    case R.styleable.SherlockTheme_actionModeSplitBackground /*15*/:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                        return;
                    default:
                        setThreadCount(DEFAULT_THREAD_COUNT);
                        return;
                }
            case Gender.FEMALE /*1*/:
            case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
            case HapticPlaybackThread.HAPTIC_QUIT_PLAYBACK /*9*/:
                setThreadCount(4);
                return;
            default:
                setThreadCount(DEFAULT_THREAD_COUNT);
                return;
        }
    }

    private void setThreadCount(int threadCount) {
        setCorePoolSize(threadCount);
        setMaximumPoolSize(threadCount);
    }

    public Future<?> submit(Runnable task) {
        PicassoFutureTask ftask = new PicassoFutureTask((BitmapHunter) task);
        execute(ftask);
        return ftask;
    }
}
