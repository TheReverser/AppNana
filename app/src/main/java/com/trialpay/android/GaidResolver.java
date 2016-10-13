package com.trialpay.android;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.facebook.BuildConfig;
import java.lang.reflect.InvocationTargetException;

public class GaidResolver {
    private static final String TAG = "Trialpay.GaidResolver";
    private static final String commonReflectionFailureMsg = "Something with wrong either with google_play_services/libproject or with the client code.";
    private String gaid;
    private Boolean isTrackingEnabled;

    public GaidResolver(final Context ctx) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                public void run() {
                    Log.d(GaidResolver.TAG, "run");
                    GaidResolver.this.resolve(ctx);
                }
            }).start();
        } else {
            resolve(ctx);
        }
    }

    private void resolve(Context ctx) {
        String advertisingIdClientClassStr = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
        try {
            Class advertisingIdClientClass = Class.forName(advertisingIdClientClassStr);
            Class[] inner = advertisingIdClientClass.getDeclaredClasses();
            Class infoClass = null;
            for (int i = 0; i < inner.length; i++) {
                String name = inner[i].getName();
                if (name.indexOf("Info") == name.length() - "Info".length()) {
                    infoClass = inner[i];
                    break;
                }
            }
            if (infoClass == null) {
                throw new ClassNotFoundException(advertisingIdClientClassStr + "$Info");
            }
            boolean z;
            Object info = advertisingIdClientClass.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{ctx});
            this.gaid = (String) infoClass.getMethod("getId", new Class[0]).invoke(info, new Object[0]);
            if (((Boolean) infoClass.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(info, new Object[0])).booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            this.isTrackingEnabled = Boolean.valueOf(z);
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
            Log.v(TAG, "gaid=" + (this.gaid != null ? this.gaid : "<null>"));
            Log.v(TAG, "isTrackingEnabled=" + (this.isTrackingEnabled != null ? this.isTrackingEnabled : "<null>"));
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
            Log.e(TAG, "Have you imported google-play-services lib from sdk/android/extras/google/google_play_services/libproject?");
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, commonReflectionFailureMsg);
            e2.printStackTrace();
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        } catch (IllegalAccessException e3) {
            Log.e(TAG, commonReflectionFailureMsg);
            e3.printStackTrace();
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        } catch (IllegalArgumentException e4) {
            Log.e(TAG, commonReflectionFailureMsg);
            e4.printStackTrace();
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        } catch (InvocationTargetException e5) {
            Throwable ex = e5.getCause();
            if (ex == null || !ex.getClass().getName().equals("com.google.android.gms.common.GooglePlayServicesAvailabilityException")) {
                if (ex != null) {
                    if (ex.getClass().getName().equals("com.google.android.gms.common.GooglePlayServicesNotAvailableException")) {
                        Log.i(TAG, "Google Play services is not available entirely.");
                    }
                }
                Log.i(TAG, "Unexpected error with Google Play Services invocation");
            } else {
                Log.i(TAG, "Encountered a recoverable error connecting to Google Play services.");
            }
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        } catch (Throwable th) {
            if (this.gaid == null) {
                this.gaid = BuildConfig.VERSION_NAME;
            }
        }
    }

    public String getGaid() {
        return this.gaid;
    }

    public Boolean isTrackingEnabled() {
        return this.isTrackingEnabled;
    }
}
