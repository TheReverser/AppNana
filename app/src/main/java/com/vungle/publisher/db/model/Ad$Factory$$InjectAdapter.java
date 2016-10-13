package com.vungle.publisher.db.model;

import com.vungle.publisher.as.a;
import com.vungle.publisher.db.model.Ad.Factory;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class Ad$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory> {
    private Binding<Factory> a;
    private Binding<EventBus> b;
    private Binding<a> c;

    public Ad$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.Ad$Factory", false, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTracking$Factory", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.event.EventBus", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final void injectMembers(Factory object) {
        object.a = (Factory) this.a.get();
        object.b = (EventBus) this.b.get();
        this.c.injectMembers(object);
    }
}
