package com.vungle.publisher.env;

import android.content.Context;
import android.net.wifi.WifiManager;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdaptiveDeviceIdStrategy$$InjectAdapter extends Binding<AdaptiveDeviceIdStrategy> implements MembersInjector<AdaptiveDeviceIdStrategy>, Provider<AdaptiveDeviceIdStrategy> {
    private Binding<Context> a;
    private Binding<WifiManager> b;
    private Binding<AdvertisingDeviceIdStrategy> c;

    public AdaptiveDeviceIdStrategy$$InjectAdapter() {
        super("com.vungle.publisher.env.AdaptiveDeviceIdStrategy", "members/com.vungle.publisher.env.AdaptiveDeviceIdStrategy", true, AdaptiveDeviceIdStrategy.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", AdaptiveDeviceIdStrategy.class, getClass().getClassLoader());
        this.b = linker.requestBinding("android.net.wifi.WifiManager", AdaptiveDeviceIdStrategy.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.env.AdvertisingDeviceIdStrategy", AdaptiveDeviceIdStrategy.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final AdaptiveDeviceIdStrategy get() {
        AdaptiveDeviceIdStrategy adaptiveDeviceIdStrategy = new AdaptiveDeviceIdStrategy();
        injectMembers(adaptiveDeviceIdStrategy);
        return adaptiveDeviceIdStrategy;
    }

    public final void injectMembers(AdaptiveDeviceIdStrategy object) {
        object.a = (Context) this.a.get();
        object.b = (WifiManager) this.b.get();
        this.c.injectMembers(object);
    }
}
