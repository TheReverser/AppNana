package com.vungle.publisher.ad;

import com.vungle.publisher.ad.AdManager.AdAvailabilityEventListener;
import com.vungle.publisher.bk;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdManager$AdAvailabilityEventListener$$InjectAdapter extends Binding<AdAvailabilityEventListener> implements MembersInjector<AdAvailabilityEventListener>, Provider<AdAvailabilityEventListener> {
    private Binding<AdManager> a;
    private Binding<bk> b;

    public AdManager$AdAvailabilityEventListener$$InjectAdapter() {
        super("com.vungle.publisher.ad.AdManager$AdAvailabilityEventListener", "members/com.vungle.publisher.ad.AdManager$AdAvailabilityEventListener", true, AdAvailabilityEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", AdAvailabilityEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", AdAvailabilityEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final AdAvailabilityEventListener get() {
        AdAvailabilityEventListener adAvailabilityEventListener = new AdAvailabilityEventListener();
        injectMembers(adAvailabilityEventListener);
        return adAvailabilityEventListener;
    }

    public final void injectMembers(AdAvailabilityEventListener object) {
        object.a = (AdManager) this.a.get();
        this.b.injectMembers(object);
    }
}
