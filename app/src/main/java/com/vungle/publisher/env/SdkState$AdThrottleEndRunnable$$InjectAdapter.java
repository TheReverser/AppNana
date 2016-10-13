package com.vungle.publisher.env;

import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SdkState$AdThrottleEndRunnable$$InjectAdapter extends Binding<AdThrottleEndRunnable> implements MembersInjector<AdThrottleEndRunnable>, Provider<AdThrottleEndRunnable> {
    private Binding<EventBus> a;

    public SdkState$AdThrottleEndRunnable$$InjectAdapter() {
        super("com.vungle.publisher.env.SdkState$AdThrottleEndRunnable", "members/com.vungle.publisher.env.SdkState$AdThrottleEndRunnable", true, AdThrottleEndRunnable.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", AdThrottleEndRunnable.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final AdThrottleEndRunnable get() {
        AdThrottleEndRunnable adThrottleEndRunnable = new AdThrottleEndRunnable();
        injectMembers(adThrottleEndRunnable);
        return adThrottleEndRunnable;
    }

    public final void injectMembers(AdThrottleEndRunnable object) {
        object.a = (EventBus) this.a.get();
    }
}
