package com.vungle.publisher.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;
import com.vungle.log.Logger;
import com.vungle.publisher.ca;
import com.vungle.publisher.cb;
import com.vungle.sdk.VunglePub.Gender;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AndroidNetwork implements cb {
    @Inject
    ConnectivityManager a;
    @Inject
    Provider<NetworkBroadcastReceiver> b;
    @Inject
    TelephonyManager c;

    public final ca a() {
        try {
            NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case Gender.MALE /*0*/:
                    return ca.mobile;
                case Gender.FEMALE /*1*/:
                case HapticPlaybackThread.HAPTIC_PLAYBACK_IS_READY /*6*/:
                    return ca.wifi;
                default:
                    Logger.d(Logger.NETWORK_TAG, "unknown connectivity type: " + type);
                    return null;
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting connectivity type", e);
            return null;
        }
    }

    public final String b() {
        String str = null;
        try {
            str = this.c.getNetworkOperatorName();
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting network operator", e);
        }
        return str;
    }
}
