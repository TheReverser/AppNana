package com.vungle.publisher.audio;

import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class VolumeChangeContentObserver extends ContentObserver {
    private final AudioManager a;
    private final Handler b;
    private volatile int c;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public AudioManager a;
    }

    public VolumeChangeContentObserver(AudioManager audioManager, Handler handler) {
        super(handler);
        this.a = audioManager;
        this.b = handler;
        this.c = audioManager.getStreamVolume(3);
    }

    public final void onChange(boolean selfChange) {
        try {
            super.onChange(selfChange);
            int i = this.c;
            int streamVolume = this.a.getStreamVolume(3);
            this.c = streamVolume;
            if (streamVolume != i) {
                Logger.v(Logger.DEVICE_TAG, "volume changed " + i + " --> " + streamVolume);
                Message message = new Message();
                message.arg1 = i;
                message.arg2 = streamVolume;
                this.b.sendMessage(message);
            }
        } catch (Throwable e) {
            Logger.e(Logger.DEVICE_TAG, e);
        }
    }
}
