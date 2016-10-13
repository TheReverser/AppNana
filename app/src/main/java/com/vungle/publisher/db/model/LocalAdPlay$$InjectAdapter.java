package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAdPlay$$InjectAdapter extends Binding<LocalAdPlay> implements MembersInjector<LocalAdPlay>, Provider<LocalAdPlay> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<AdPlay> c;

    public LocalAdPlay$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAdPlay", "members/com.vungle.publisher.db.model.LocalAdPlay", false, LocalAdPlay.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAdPlay$Factory", LocalAdPlay.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalAdReportEvent$Factory", LocalAdPlay.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.AdPlay", LocalAdPlay.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final LocalAdPlay get() {
        LocalAdPlay localAdPlay = new LocalAdPlay();
        injectMembers(localAdPlay);
        return localAdPlay;
    }

    public final void injectMembers(LocalAdPlay object) {
        object.e = (Factory) this.a.get();
        object.f = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
