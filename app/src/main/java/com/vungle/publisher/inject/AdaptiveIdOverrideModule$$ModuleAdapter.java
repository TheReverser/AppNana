package com.vungle.publisher.inject;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.vungle.publisher.env.AdaptiveDeviceIdStrategy;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdaptiveIdOverrideModule$$ModuleAdapter extends ModuleAdapter<AdaptiveIdOverrideModule> {
    private static final String[] a = new String[0];
    private static final Class<?>[] b = new Class[0];
    private static final Class<?>[] c = new Class[]{ConfigurablePublisherModule.class};

    /* compiled from: vungle */
    public static final class ProvideDeviceIdStrategyProvidesAdapter extends ProvidesBinding<DeviceIdStrategy> implements Provider<DeviceIdStrategy> {
        private final AdaptiveIdOverrideModule a;
        private Binding<AdaptiveDeviceIdStrategy> b;

        public ProvideDeviceIdStrategyProvidesAdapter(AdaptiveIdOverrideModule module) {
            super("com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", true, "com.vungle.publisher.inject.AdaptiveIdOverrideModule", "provideDeviceIdStrategy");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("com.vungle.publisher.env.AdaptiveDeviceIdStrategy", AdaptiveIdOverrideModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final DeviceIdStrategy get() {
            return this.a.provideDeviceIdStrategy((AdaptiveDeviceIdStrategy) this.b.get());
        }
    }

    /* compiled from: vungle */
    public static final class ProvideWifiManagerProvidesAdapter extends ProvidesBinding<WifiManager> implements Provider<WifiManager> {
        private final AdaptiveIdOverrideModule a;
        private Binding<Context> b;

        public ProvideWifiManagerProvidesAdapter(AdaptiveIdOverrideModule module) {
            super("android.net.wifi.WifiManager", false, "com.vungle.publisher.inject.AdaptiveIdOverrideModule", "provideWifiManager");
            this.a = module;
            setLibrary(false);
        }

        public final void attach(Linker linker) {
            this.b = linker.requestBinding("android.content.Context", AdaptiveIdOverrideModule.class, getClass().getClassLoader());
        }

        public final void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> set) {
            getBindings.add(this.b);
        }

        public final WifiManager get() {
            return this.a.provideWifiManager((Context) this.b.get());
        }
    }

    public final /* synthetic */ void getBindings(BindingsGroup x0, Object x1) {
        AdaptiveIdOverrideModule adaptiveIdOverrideModule = (AdaptiveIdOverrideModule) x1;
        x0.contributeProvidesBinding("com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", new ProvideDeviceIdStrategyProvidesAdapter(adaptiveIdOverrideModule));
        x0.contributeProvidesBinding("android.net.wifi.WifiManager", new ProvideWifiManagerProvidesAdapter(adaptiveIdOverrideModule));
    }

    public final /* synthetic */ Object newModule() {
        return new AdaptiveIdOverrideModule();
    }

    public AdaptiveIdOverrideModule$$ModuleAdapter() {
        super(AdaptiveIdOverrideModule.class, a, b, true, c, true, false);
    }
}
