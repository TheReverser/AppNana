package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAdReportEvent$$InjectAdapter extends Binding<LocalAdReportEvent> implements MembersInjector<LocalAdReportEvent>, Provider<LocalAdReportEvent> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<AdReportEvent> c;

    public LocalAdReportEvent$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAdReportEvent", "members/com.vungle.publisher.db.model.LocalAdReportEvent", false, LocalAdReportEvent.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAdReportEvent$Factory", LocalAdReportEvent.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalAdPlay$Factory", LocalAdReportEvent.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.AdReportEvent", LocalAdReportEvent.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final LocalAdReportEvent get() {
        LocalAdReportEvent localAdReportEvent = new LocalAdReportEvent();
        injectMembers(localAdReportEvent);
        return localAdReportEvent;
    }

    public final void injectMembers(LocalAdReportEvent object) {
        object.e = (Factory) this.a.get();
        object.f = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
