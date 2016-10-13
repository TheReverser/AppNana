package com.vungle.publisher.inject;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.vungle.log.Logger;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.bf;
import com.vungle.publisher.bn;
import com.vungle.publisher.bo;
import com.vungle.publisher.bs;
import com.vungle.publisher.bv;
import com.vungle.publisher.bw;
import com.vungle.publisher.bx;
import com.vungle.publisher.by;
import com.vungle.publisher.bz;
import com.vungle.publisher.cb;
import com.vungle.publisher.ck;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.env.AdvertisingDeviceIdStrategy;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.inject.annotations.AdTempDirectory;
import com.vungle.publisher.inject.annotations.EnvSharedPreferences;
import com.vungle.publisher.inject.annotations.OldAdTempDirectory;
import com.vungle.publisher.inject.annotations.RequestConfigHttpTransaction;
import com.vungle.publisher.inject.annotations.TrackInstallHttpTransaction;
import com.vungle.publisher.inject.annotations.VungleBaseUrl;
import com.vungle.publisher.location.AndroidLocation;
import com.vungle.publisher.location.GoogleLocationClientDetailedLocationProvider;
import com.vungle.publisher.location.GoogleLocationServicesDetailedLocationProvider;
import com.vungle.publisher.net.AndroidNetwork;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import com.vungle.publisher.protocol.RequestConfigHttpRequest;
import com.vungle.publisher.protocol.TrackInstallHttpRequest;
import com.vungle.publisher.protocol.TrackInstallHttpResponseHandler;
import com.vungle.sdk.VungleAdvert;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(injects = {AdManager.class, AndroidDevice.class, AndroidLocation.class, AndroidNetwork.class, FullScreenAdActivity.class, GoogleLocationClientDetailedLocationProvider.class, GoogleLocationServicesDetailedLocationProvider.class, PostRollFragment.class, VideoFragment.class, VungleAdvert.class, VunglePub.class})
/* compiled from: vungle */
class ConfigurablePublisherModule implements bs {
    Context a;
    String b;
    BitmapFactory c;
    Class<? extends FullScreenAdActivity> d;
    WrapperFramework e;
    String f;
    private boolean g;

    ConfigurablePublisherModule() {
    }

    public void setBitmapFactory(BitmapFactory bitmapFactory) {
        if (this.g) {
            Logger.d(Logger.INJECT_TAG, "BitmapFactory in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting BitmapFactory in publisher module: " + bitmapFactory);
        this.c = bitmapFactory;
    }

    public final void a(Class<? extends FullScreenAdActivity> cls) {
        if (this.g) {
            Logger.d(Logger.INJECT_TAG, "full screen ad activity class in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting full screen ad activity class in publisher module: " + cls);
        this.d = cls;
    }

    public void setWrapperFramework(WrapperFramework framework) {
        if (this.g) {
            Logger.d(Logger.INJECT_TAG, "wrapper framework in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + framework);
        this.e = framework;
    }

    public void setWrapperFrameworkVersion(String wrapperFrameworkVersion) {
        if (this.g) {
            Logger.d(Logger.INJECT_TAG, "wrapper framework version in publisher module NOT set - already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "setting framework in publisher module: " + wrapperFrameworkVersion);
        this.f = wrapperFrameworkVersion;
    }

    public final void a(Context context, String str) {
        if (this.g) {
            Logger.d(Logger.INJECT_TAG, "publisher module already initialized");
            return;
        }
        Logger.d(Logger.INJECT_TAG, "initializing publisher module");
        this.a = context.getApplicationContext();
        this.b = str;
        this.g = true;
    }

    @Provides
    @AdTempDirectory
    static String a(Context context) {
        if (context.getExternalFilesDir(null) == null) {
            throw new bn();
        }
        return bo.a(context.getExternalFilesDir(null).getAbsolutePath(), ".vungle");
    }

    @Provides
    @OldAdTempDirectory
    static String b(Context context) {
        if (context.getExternalCacheDir() == null) {
            throw new bn();
        }
        return bo.a(context.getExternalCacheDir(), ".VungleCacheDir");
    }

    @Singleton
    @Provides
    static AudioManager c(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            Logger.d(Logger.DEVICE_TAG, "AudioManager not avaialble");
        }
        return audioManager;
    }

    @Provides
    static ConnectivityManager d(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            Logger.d(Logger.DEVICE_TAG, "ConnectivityManager not available");
        }
        return connectivityManager;
    }

    @Provides
    static bv a(bw bwVar) {
        return bwVar;
    }

    @Singleton
    @Provides
    static bx a() {
        bx googleLocationClientDetailedLocationProvider;
        Throwable e;
        try {
            googleLocationClientDetailedLocationProvider = new GoogleLocationClientDetailedLocationProvider();
            try {
                Injector.getInstance().a.inject(googleLocationClientDetailedLocationProvider);
            } catch (NoClassDefFoundError e2) {
                e = e2;
                Logger.d("error creating GoogleLocationClientDetailedLocationProvider", e);
                return googleLocationClientDetailedLocationProvider;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            googleLocationClientDetailedLocationProvider = null;
            e = th;
            Logger.d("error creating GoogleLocationClientDetailedLocationProvider", e);
            return googleLocationClientDetailedLocationProvider;
        }
        return googleLocationClientDetailedLocationProvider;
    }

    @Singleton
    @Provides
    static by b() {
        by googleLocationServicesDetailedLocationProvider;
        Throwable e;
        try {
            googleLocationServicesDetailedLocationProvider = new GoogleLocationServicesDetailedLocationProvider();
            try {
                Injector.getInstance().a.inject(googleLocationServicesDetailedLocationProvider);
            } catch (NoClassDefFoundError e2) {
                e = e2;
                Logger.d("error creating GoogleLocationServicesDetailedLocationProvider", e);
                return googleLocationServicesDetailedLocationProvider;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            googleLocationServicesDetailedLocationProvider = null;
            e = th;
            Logger.d("error creating GoogleLocationServicesDetailedLocationProvider", e);
            return googleLocationServicesDetailedLocationProvider;
        }
        return googleLocationServicesDetailedLocationProvider;
    }

    @Singleton
    @Provides
    static bf a(AndroidDevice androidDevice) {
        return androidDevice;
    }

    @Singleton
    @Provides
    static DeviceIdStrategy a(AdvertisingDeviceIdStrategy advertisingDeviceIdStrategy) {
        return advertisingDeviceIdStrategy;
    }

    @Provides
    @EnvSharedPreferences
    static SharedPreferences e(Context context) {
        return context.getSharedPreferences("VUNGLE_PUB_APP_INFO", 0);
    }

    @Singleton
    @Provides
    static bz a(AndroidLocation androidLocation) {
        return androidLocation;
    }

    @Singleton
    @Provides
    static cb a(AndroidNetwork androidNetwork) {
        return androidNetwork;
    }

    @Provides
    @RequestConfigHttpTransaction
    static HttpTransaction a(Factory factory, RequestConfigHttpRequest.Factory factory2, ck ckVar) {
        return factory.a(factory2.d(), ckVar);
    }

    @Provides
    static TelephonyManager f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            Logger.d(Logger.DEVICE_TAG, "TelephonyManager not avaialble");
        }
        return telephonyManager;
    }

    @Provides
    @TrackInstallHttpTransaction
    static HttpTransaction a(Factory factory, TrackInstallHttpRequest.Factory factory2, TrackInstallHttpResponseHandler trackInstallHttpResponseHandler) {
        return factory.a(factory2.d(), trackInstallHttpResponseHandler);
    }

    @Provides
    @VungleBaseUrl
    static String c() {
        return "http://api.vungle.com/api/v1/";
    }

    @Provides
    static WindowManager g(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            Logger.d(Logger.DEVICE_TAG, "WindowManager not available");
        }
        return windowManager;
    }
}
