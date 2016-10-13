package com.vungle.publisher.env;

import android.content.Context;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.env.AndroidDevice.DeviceIdStrategy;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdvertisingDeviceIdStrategy$$InjectAdapter extends Binding<AdvertisingDeviceIdStrategy> implements MembersInjector<AdvertisingDeviceIdStrategy>, Provider<AdvertisingDeviceIdStrategy> {
    private Binding<Context> a;
    private Binding<EventBus> b;
    private Binding<ScheduledPriorityExecutor> c;
    private Binding<DeviceIdStrategy> d;

    public AdvertisingDeviceIdStrategy$$InjectAdapter() {
        super("com.vungle.publisher.env.AdvertisingDeviceIdStrategy", "members/com.vungle.publisher.env.AdvertisingDeviceIdStrategy", true, AdvertisingDeviceIdStrategy.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", AdvertisingDeviceIdStrategy.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.event.EventBus", AdvertisingDeviceIdStrategy.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", AdvertisingDeviceIdStrategy.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.d = linker2.requestBinding("members/com.vungle.publisher.env.AndroidDevice$DeviceIdStrategy", AdvertisingDeviceIdStrategy.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
    }

    public final AdvertisingDeviceIdStrategy get() {
        AdvertisingDeviceIdStrategy advertisingDeviceIdStrategy = new AdvertisingDeviceIdStrategy();
        injectMembers(advertisingDeviceIdStrategy);
        return advertisingDeviceIdStrategy;
    }

    public final void injectMembers(AdvertisingDeviceIdStrategy object) {
        object.c = (Context) this.a.get();
        object.d = (EventBus) this.b.get();
        object.e = (ScheduledPriorityExecutor) this.c.get();
        this.d.injectMembers(object);
    }
}
