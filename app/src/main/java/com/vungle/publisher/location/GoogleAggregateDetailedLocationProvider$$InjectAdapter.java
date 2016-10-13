package com.vungle.publisher.location;

import com.vungle.publisher.bw;
import com.vungle.publisher.bx;
import com.vungle.publisher.by;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class GoogleAggregateDetailedLocationProvider$$InjectAdapter extends Binding<bw> implements MembersInjector<bw>, Provider<bw> {
    private Binding<Lazy<bx>> a;
    private Binding<by> b;

    public GoogleAggregateDetailedLocationProvider$$InjectAdapter() {
        super("com.vungle.publisher.bw", "members/com.vungle.publisher.location.GoogleAggregateDetailedLocationProvider", false, bw.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("dagger.Lazy<com.vungle.publisher.location.IGoogleLocationClientDetailedLocationProvider>", bw.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.by", bw.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final bw get() {
        bw bwVar = new bw();
        injectMembers(bwVar);
        return bwVar;
    }

    public final void injectMembers(bw object) {
        object.c = (Lazy) this.a.get();
        object.d = (by) this.b.get();
    }
}
