package com.vungle.publisher.db.model;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.AdReport.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdReport$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<DatabaseHelper> a;
    private Binding<LocalAdReport.Factory> b;
    private Binding<StreamingAdReport.Factory> c;

    public AdReport$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.AdReport$Factory", "members/com.vungle.publisher.db.model.AdReport$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalAdReport$Factory", Factory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdReport$Factory", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (DatabaseHelper) this.a.get();
        object.b = (LocalAdReport.Factory) this.b.get();
        object.c = (StreamingAdReport.Factory) this.c.get();
    }
}
