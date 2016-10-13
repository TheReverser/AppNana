package com.vungle.publisher.inject;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.be;
import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import com.vungle.publisher.bv;
import com.vungle.publisher.bw;
import com.vungle.publisher.bx;
import com.vungle.publisher.by;
import com.vungle.publisher.bz;
import com.vungle.publisher.cb;
import com.vungle.publisher.ck;
import com.vungle.publisher.env.AdvertisingDeviceIdStrategy;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.image.AssetBitmapFactory;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.location.AndroidLocation;
import com.vungle.publisher.net.AndroidNetwork;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import com.vungle.publisher.protocol.RequestConfigHttpRequest;
import com.vungle.publisher.protocol.TrackInstallHttpRequest;
import com.vungle.publisher.protocol.TrackInstallHttpResponseHandler;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ConfigurablePublisherModule$$ModuleAdapter extends ModuleAdapter<ConfigurablePublisherModule> {
    private static final String[] a = new String[]{"members/com.vungle.publisher.ad.AdManager", "members/com.vungle.publisher.env.AndroidDevice", "members/com.vungle.publisher.location.AndroidLocation", "members/com.vungle.publisher.net.AndroidNetwork", "members/com.vungle.publisher.FullScreenAdActivity", "members/com.vungle.publisher.location.GoogleLocationClientDetailedLocationProvider", "members/com.vungle.publisher.location.GoogleLocationServicesDetailedLocationProvider", "members/com.vungle.publisher.display.view.PostRollFragment", "members/com.vungle.publisher.display.view.VideoFragment", "members/com.vungle.sdk.VungleAdvert", "members/com.vungle.publisher.VunglePub"};
    private static final Class<?>[] b = new Class[0];
    private static final Class<?>[] c = new Class[0];

    /* compiled from: vungle */
    public static final class ProvideAdTempDirectoryProvidesAdapter extends ProvidesBinding<String> implements Provider<String> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideAdTempDirectoryProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.AdTempDirectory()/java.lang.String", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideAdTempDirectory");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final String get() {
            return ConfigurablePublisherModule.a((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideApplicationContextProvidesAdapter extends ProvidesBinding<Context> implements Provider<Context> {
        private final ConfigurablePublisherModule a;

        public ProvideApplicationContextProvidesAdapter(ConfigurablePublisherModule module) {
            super("android.content.Context", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideApplicationContext");
            this.a = module;
            setLibrary(false);
        }

        public final Context get() {
            return this.a.a;
        }
    }

    /* compiled from: vungle */
    public static final class ProvideAudioManagerProvidesAdapter extends ProvidesBinding<AudioManager> implements Provider<AudioManager> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideAudioManagerProvidesAdapter(ConfigurablePublisherModule module) {
            super("android.media.AudioManager", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideAudioManager");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final AudioManager get() {
            return ConfigurablePublisherModule.c((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideBitmapFactoryProvidesAdapter extends ProvidesBinding<BitmapFactory> implements Provider<BitmapFactory> {
        private final ConfigurablePublisherModule a;
        private Binding<AssetBitmapFactory> b;

        public ProvideBitmapFactoryProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.image.BitmapFactory", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideBitmapFactory");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.image.AssetBitmapFactory", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final BitmapFactory get() {
            ConfigurablePublisherModule configurablePublisherModule = this.a;
            return configurablePublisherModule.c == null ? (AssetBitmapFactory) this.b.get() : configurablePublisherModule.c;
        }
    }

    /* compiled from: vungle */
    public static final class ProvideConnectivityManagerProvidesAdapter extends ProvidesBinding<ConnectivityManager> implements Provider<ConnectivityManager> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideConnectivityManagerProvidesAdapter(ConfigurablePublisherModule module) {
            super("android.net.ConnectivityManager", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideConnectivityManager");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final ConnectivityManager get() {
            return ConfigurablePublisherModule.d((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideDeviceIdStrategyProvidesAdapter extends ProvidesBinding<DeviceIdStrategy> implements Provider<DeviceIdStrategy> {
        private final ConfigurablePublisherModule a;
        private Binding<AdvertisingDeviceIdStrategy> b;

        public ProvideDeviceIdStrategyProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideDeviceIdStrategy");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.env.AdvertisingDeviceIdStrategy", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final DeviceIdStrategy get() {
            return ConfigurablePublisherModule.a((AdvertisingDeviceIdStrategy) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideDeviceProvidesAdapter extends ProvidesBinding<bf> implements Provider<bf> {
        private final ConfigurablePublisherModule a;
        private Binding<AndroidDevice> b;

        public ProvideDeviceProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.bf", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideDevice");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.env.AndroidDevice", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final bf get() {
            return ConfigurablePublisherModule.a((AndroidDevice) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideEnvSharedPreferencesProvidesAdapter extends ProvidesBinding<SharedPreferences> implements Provider<SharedPreferences> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideEnvSharedPreferencesProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.EnvSharedPreferences()/android.content.SharedPreferences", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideEnvSharedPreferences");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final SharedPreferences get() {
            return ConfigurablePublisherModule.e((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideFullScreenAdActivityClassProvidesAdapter extends ProvidesBinding<Class> implements Provider<Class> {
        private final ConfigurablePublisherModule a;

        public ProvideFullScreenAdActivityClassProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.FullScreenAdActivityClass()/java.lang.Class", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideFullScreenAdActivityClass");
            this.a = module;
            setLibrary(false);
        }

        public final Class get() {
            ConfigurablePublisherModule configurablePublisherModule = this.a;
            return configurablePublisherModule.d == null ? FullScreenAdActivity.class : configurablePublisherModule.d;
        }
    }

    /* compiled from: vungle */
    public static final class ProvideGoogleAggregateDetailedLocationProviderProvidesAdapter extends ProvidesBinding<bv> implements Provider<bv> {
        private final ConfigurablePublisherModule a;
        private Binding<bw> b;

        public ProvideGoogleAggregateDetailedLocationProviderProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.bv", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideGoogleAggregateDetailedLocationProvider");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.bw", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final bv get() {
            return ConfigurablePublisherModule.a((bw) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideGoogleLocationClientDetailedLocationProviderProvidesAdapter extends ProvidesBinding<bx> implements Provider<bx> {
        private final ConfigurablePublisherModule a;

        public ProvideGoogleLocationClientDetailedLocationProviderProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.bx", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideGoogleLocationClientDetailedLocationProvider");
            this.a = module;
            setLibrary(false);
        }

        public final bx get() {
            return ConfigurablePublisherModule.a();
        }
    }

    /* compiled from: vungle */
    public static final class ProvideGoogleLocationServicesDetailedLocationProviderProvidesAdapter extends ProvidesBinding<by> implements Provider<by> {
        private final ConfigurablePublisherModule a;

        public ProvideGoogleLocationServicesDetailedLocationProviderProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.by", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideGoogleLocationServicesDetailedLocationProvider");
            this.a = module;
            setLibrary(false);
        }

        public final by get() {
            return ConfigurablePublisherModule.b();
        }
    }

    /* compiled from: vungle */
    public static final class ProvideLocationProvidesAdapter extends ProvidesBinding<bz> implements Provider<bz> {
        private final ConfigurablePublisherModule a;
        private Binding<AndroidLocation> b;

        public ProvideLocationProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.bz", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideLocation");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.location.AndroidLocation", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final bz get() {
            return ConfigurablePublisherModule.a((AndroidLocation) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideNetworkProvidesAdapter extends ProvidesBinding<cb> implements Provider<cb> {
        private final ConfigurablePublisherModule a;
        private Binding<AndroidNetwork> b;

        public ProvideNetworkProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.cb", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideNetwork");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.net.AndroidNetwork", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final cb get() {
            return ConfigurablePublisherModule.a((AndroidNetwork) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideOldAdTempDirectoryProvidesAdapter extends ProvidesBinding<String> implements Provider<String> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideOldAdTempDirectoryProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.OldAdTempDirectory()/java.lang.String", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideOldAdTempDirectory");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final String get() {
            return ConfigurablePublisherModule.b((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvidePublisherAppProvidesAdapter extends ProvidesBinding<bh> implements Provider<bh> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;
        private Binding<WrapperFramework> c;

        public ProvidePublisherAppProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.bh", true, "com.vungle.publisher.inject.ConfigurablePublisherModule", "providePublisherApp");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
            this.c = linker.requestBinding("com.vungle.publisher.env.WrapperFramework", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
            getBindings.add(this.c);
        }

        public final bh get() {
            return new be(((Context) this.b.get()).getPackageName(), this.a.b, (WrapperFramework) this.c.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideRequestConfigHttpTransactionProvidesAdapter extends ProvidesBinding<HttpTransaction> implements Provider<HttpTransaction> {
        private final ConfigurablePublisherModule a;
        private Binding<Factory> b;
        private Binding<RequestConfigHttpRequest.Factory> c;
        private Binding<ck> d;

        public ProvideRequestConfigHttpTransactionProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.RequestConfigHttpTransaction()/com.vungle.publisher.net.http.HttpTransaction", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideRequestConfigHttpTransaction");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.net.http.HttpTransaction$Factory", ConfigurablePublisherModule.class, getClass().getClassLoader());
            this.c = linker.requestBinding("com.vungle.publisher.protocol.RequestConfigHttpRequest$Factory", ConfigurablePublisherModule.class, getClass().getClassLoader());
            this.d = linker.requestBinding("com.vungle.publisher.ck", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
            getBindings.add(this.c);
            getBindings.add(this.d);
        }

        public final HttpTransaction get() {
            return ConfigurablePublisherModule.a((Factory) this.b.get(), (RequestConfigHttpRequest.Factory) this.c.get(), (ck) this.d.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideTelephonyManagerProvidesAdapter extends ProvidesBinding<TelephonyManager> implements Provider<TelephonyManager> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideTelephonyManagerProvidesAdapter(ConfigurablePublisherModule module) {
            super("android.telephony.TelephonyManager", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideTelephonyManager");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final TelephonyManager get() {
            return ConfigurablePublisherModule.f((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideTrackInstallHttpTransactionProvidesAdapter extends ProvidesBinding<HttpTransaction> implements Provider<HttpTransaction> {
        private final ConfigurablePublisherModule a;
        private Binding<Factory> b;
        private Binding<TrackInstallHttpRequest.Factory> c;
        private Binding<TrackInstallHttpResponseHandler> d;

        public ProvideTrackInstallHttpTransactionProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.TrackInstallHttpTransaction()/com.vungle.publisher.net.http.HttpTransaction", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideTrackInstallHttpTransaction");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.net.http.HttpTransaction$Factory", ConfigurablePublisherModule.class, getClass().getClassLoader());
            this.c = linker.requestBinding("com.vungle.publisher.protocol.TrackInstallHttpRequest$Factory", ConfigurablePublisherModule.class, getClass().getClassLoader());
            this.d = linker.requestBinding("com.vungle.publisher.protocol.TrackInstallHttpResponseHandler", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
            getBindings.add(this.c);
            getBindings.add(this.d);
        }

        public final HttpTransaction get() {
            return ConfigurablePublisherModule.a((Factory) this.b.get(), (TrackInstallHttpRequest.Factory) this.c.get(), (TrackInstallHttpResponseHandler) this.d.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideVungleBaseUrlProvidesAdapter extends ProvidesBinding<String> implements Provider<String> {
        private final ConfigurablePublisherModule a;

        public ProvideVungleBaseUrlProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.VungleBaseUrl()/java.lang.String", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideVungleBaseUrl");
            this.a = module;
            setLibrary(false);
        }

        public final String get() {
            return ConfigurablePublisherModule.c();
        }
    }

    /* compiled from: vungle */
    public static final class ProvideWindowManagerProvidesAdapter extends ProvidesBinding<WindowManager> implements Provider<WindowManager> {
        private final ConfigurablePublisherModule a;
        private Binding<Context> b;

        public ProvideWindowManagerProvidesAdapter(ConfigurablePublisherModule module) {
            super("android.view.WindowManager", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideWindowManager");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", ConfigurablePublisherModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final WindowManager get() {
            return ConfigurablePublisherModule.g((Context) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideWrapperFrameworkProvidesAdapter extends ProvidesBinding<WrapperFramework> implements Provider<WrapperFramework> {
        private final ConfigurablePublisherModule a;

        public ProvideWrapperFrameworkProvidesAdapter(ConfigurablePublisherModule module) {
            super("com.vungle.publisher.env.WrapperFramework", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideWrapperFramework");
            this.a = module;
            setLibrary(false);
        }

        public final WrapperFramework get() {
            return this.a.e;
        }
    }

    /* compiled from: vungle */
    public static final class ProvideWrapperFrameworkVersionProvidesAdapter extends ProvidesBinding<String> implements Provider<String> {
        private final ConfigurablePublisherModule a;

        public ProvideWrapperFrameworkVersionProvidesAdapter(ConfigurablePublisherModule module) {
            super("@com.vungle.publisher.inject.annotations.WrapperFrameworkVersion()/java.lang.String", false, "com.vungle.publisher.inject.ConfigurablePublisherModule", "provideWrapperFrameworkVersion");
            this.a = module;
            setLibrary(false);
        }

        public final String get() {
            return this.a.f;
        }
    }

    public final /* synthetic */ void getBindings(BindingsGroup x0, Object x1) {
        ConfigurablePublisherModule configurablePublisherModule = (ConfigurablePublisherModule) x1;
        x0.contributeProvidesBinding("android.content.Context", new ProvideApplicationContextProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.AdTempDirectory()/java.lang.String", new ProvideAdTempDirectoryProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.OldAdTempDirectory()/java.lang.String", new ProvideOldAdTempDirectoryProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("android.media.AudioManager", new ProvideAudioManagerProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.image.BitmapFactory", new ProvideBitmapFactoryProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("android.net.ConnectivityManager", new ProvideConnectivityManagerProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.bv", new ProvideGoogleAggregateDetailedLocationProviderProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.bx", new ProvideGoogleLocationClientDetailedLocationProviderProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.by", new ProvideGoogleLocationServicesDetailedLocationProviderProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.bf", new ProvideDeviceProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", new ProvideDeviceIdStrategyProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.EnvSharedPreferences()/android.content.SharedPreferences", new ProvideEnvSharedPreferencesProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.FullScreenAdActivityClass()/java.lang.Class", new ProvideFullScreenAdActivityClassProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.bz", new ProvideLocationProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.cb", new ProvideNetworkProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.bh", new ProvidePublisherAppProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.RequestConfigHttpTransaction()/com.vungle.publisher.net.http.HttpTransaction", new ProvideRequestConfigHttpTransactionProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("android.telephony.TelephonyManager", new ProvideTelephonyManagerProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.TrackInstallHttpTransaction()/com.vungle.publisher.net.http.HttpTransaction", new ProvideTrackInstallHttpTransactionProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.VungleBaseUrl()/java.lang.String", new ProvideVungleBaseUrlProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("android.view.WindowManager", new ProvideWindowManagerProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("com.vungle.publisher.env.WrapperFramework", new ProvideWrapperFrameworkProvidesAdapter(configurablePublisherModule));
        x0.contributeProvidesBinding("@com.vungle.publisher.inject.annotations.WrapperFrameworkVersion()/java.lang.String", new ProvideWrapperFrameworkVersionProvidesAdapter(configurablePublisherModule));
    }

    public final /* synthetic */ Object newModule() {
        return new ConfigurablePublisherModule();
    }

    public ConfigurablePublisherModule$$ModuleAdapter() {
        super(ConfigurablePublisherModule.class, a, b, false, c, true, false);
    }
}
