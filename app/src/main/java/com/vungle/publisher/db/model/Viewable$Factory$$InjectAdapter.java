package com.vungle.publisher.db.model;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.Viewable.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class Viewable$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<DatabaseHelper> a;

    public Viewable$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.Viewable$Factory", "members/com.vungle.publisher.db.model.Viewable$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (DatabaseHelper) this.a.get();
    }
}
