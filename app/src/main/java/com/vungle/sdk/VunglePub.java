package com.vungle.sdk;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.Demographic;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePubBase;
import com.vungle.publisher.inject.Injector;

/* compiled from: vungle */
public final class VunglePub {
    private static final VunglePubBase a = com.vungle.publisher.VunglePub.getInstance();
    private static boolean b;
    private static boolean c;
    private static boolean d;

    /* compiled from: vungle */
    public interface EventListener {
        void onVungleAdEnd();

        void onVungleAdStart();

        void onVungleView(double d, double d2);
    }

    /* compiled from: vungle */
    public static final class Gender {
        public static final int FEMALE = 1;
        public static final int MALE = 0;
        public static final int UNKNOWN = -1;

        static com.vungle.publisher.Demographic.Gender a(int i) {
            switch (i) {
                case MALE /*0*/:
                    return com.vungle.publisher.Demographic.Gender.male;
                case FEMALE /*1*/:
                    return com.vungle.publisher.Demographic.Gender.female;
                default:
                    return null;
            }
        }

        public static String toString(int gender) {
            switch (gender) {
                case MALE /*0*/:
                    return "male";
                case FEMALE /*1*/:
                    return "female";
                default:
                    return AnalyticsEvents.PARAMETER_SHARE_OUTCOME_UNKNOWN;
            }
        }

        private Gender() {
        }
    }

    /* compiled from: vungle */
    static class a implements com.vungle.publisher.EventListener {
        private EventListener a;

        a(EventListener eventListener) {
            this.a = eventListener;
        }

        public final void onAdEnd(boolean z) {
            this.a.onVungleAdEnd();
        }

        public final void onAdStart() {
            this.a.onVungleAdStart();
        }

        public final void onAdUnavailable(String str) {
        }

        public final void onAdPlayableChanged(boolean z) {
        }

        public final void onVideoView(boolean z, int watchedMillis, int videoMillis) {
            this.a.onVungleView(((double) watchedMillis) / 1000.0d, ((double) videoMillis) / 1000.0d);
        }
    }

    private VunglePub() {
    }

    public static String getVersionString() {
        return com.vungle.publisher.VunglePub.VERSION;
    }

    public static void init(Context context, String vungleAppId) {
        init(context, vungleAppId, 0, -1);
    }

    public static void init(Context context, String vungleAppId, int age, int gender) {
        boolean z = false;
        if (!b) {
            Injector.getInstance().a(VungleAdvert.class);
        }
        a.init(context, vungleAppId);
        if (!b) {
            boolean z2 = age > 0;
            com.vungle.publisher.Demographic.Gender a = Gender.a(gender);
            if (a != null) {
                z = true;
            }
            if (z2 || z) {
                Demographic demographic = a.getDemographic();
                if (z2) {
                    demographic.setAge(Integer.valueOf(age));
                }
                if (z) {
                    demographic.setGender(a);
                }
                b = true;
            }
        }
    }

    public static void setAutoRotation(boolean shouldAutoRotate) {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            globalAdConfig.setOrientation(shouldAutoRotate ? Orientation.autoRotate : Orientation.matchVideo);
        }
    }

    public static void setBackButtonEnabled(boolean isBackButtonEnabled) {
        c = isBackButtonEnabled;
    }

    public static void setEventListener(EventListener eventListener) {
        if (eventListener != null) {
            a.setEventListeners(new a(eventListener));
        }
    }

    public static void setIncentivizedBackButtonEnabled(boolean isBackButtonEnabled) {
        d = isBackButtonEnabled;
    }

    public static boolean getSoundEnabled() {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            return globalAdConfig.isSoundEnabled();
        }
        return false;
    }

    public static void setSoundEnabled(boolean isSoundEnabled) {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            globalAdConfig.setSoundEnabled(isSoundEnabled);
        }
    }

    public static boolean isVideoAvailable() {
        return isVideoAvailable(false);
    }

    public static boolean isVideoAvailable(boolean z) {
        return a.isAdPlayable();
    }

    public static boolean displayAdvert() {
        boolean isVideoAvailable = isVideoAvailable();
        AdConfig adConfig = new AdConfig();
        adConfig.setBackButtonImmediatelyEnabled(c);
        a.playAd(adConfig);
        return isVideoAvailable;
    }

    public static boolean displayIncentivizedAdvert(boolean showCloseButton) {
        return displayIncentivizedAdvert(null, showCloseButton);
    }

    public static boolean displayIncentivizedAdvert(String str, boolean z) {
        boolean isVideoAvailable = isVideoAvailable();
        AdConfig adConfig = new AdConfig();
        adConfig.setBackButtonImmediatelyEnabled(d);
        a.playAd(adConfig);
        return isVideoAvailable;
    }

    public static void onPause() {
        a.onPause();
    }

    public static void onResume() {
        a.onResume();
    }
}
