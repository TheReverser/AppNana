package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class Ad$$InjectAdapter extends Binding<Ad> implements MembersInjector<Ad> {
    private Binding<EventBus> a;
    private Binding<as> b;

    public Ad$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.db.model.Ad", false, Ad.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", Ad.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", Ad.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(Ad object) {
        object.q = (EventBus) this.a.get();
        this.b.injectMembers(object);
    }
}
