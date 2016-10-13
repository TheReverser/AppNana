package com.vungle.publisher.location;

import com.vungle.publisher.bv;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AndroidLocation$$InjectAdapter extends Binding<AndroidLocation> implements MembersInjector<AndroidLocation>, Provider<AndroidLocation> {
    private Binding<bv> a;

    public AndroidLocation$$InjectAdapter() {
        super("com.vungle.publisher.location.AndroidLocation", "members/com.vungle.publisher.location.AndroidLocation", true, AndroidLocation.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bv", AndroidLocation.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final AndroidLocation get() {
        AndroidLocation androidLocation = new AndroidLocation();
        injectMembers(androidLocation);
        return androidLocation;
    }

    public final void injectMembers(AndroidLocation object) {
        object.a = (bv) this.a.get();
    }
}
