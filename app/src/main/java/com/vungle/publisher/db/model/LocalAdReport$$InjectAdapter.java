package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.LocalAdReport.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalAdReport$$InjectAdapter extends Binding<LocalAdReport> implements MembersInjector<LocalAdReport>, Provider<LocalAdReport> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<AdReport> c;

    public LocalAdReport$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalAdReport", "members/com.vungle.publisher.db.model.LocalAdReport", false, LocalAdReport.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAdReport$Factory", LocalAdReport.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalAdPlay$Factory", LocalAdReport.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.AdReport", LocalAdReport.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final LocalAdReport get() {
        LocalAdReport localAdReport = new LocalAdReport();
        injectMembers(localAdReport);
        return localAdReport;
    }

    public final void injectMembers(LocalAdReport object) {
        object.q = (Factory) this.a.get();
        object.u = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
