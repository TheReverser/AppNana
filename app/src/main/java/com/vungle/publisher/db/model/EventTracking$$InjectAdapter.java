package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class EventTracking$$InjectAdapter extends Binding<EventTracking> implements MembersInjector<EventTracking>, Provider<EventTracking> {
    private Binding<Factory> a;
    private Binding<as> b;

    public EventTracking$$InjectAdapter() {
        super("com.vungle.publisher.db.model.EventTracking", "members/com.vungle.publisher.db.model.EventTracking", false, EventTracking.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTracking$Factory", EventTracking.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", EventTracking.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final EventTracking get() {
        EventTracking eventTracking = new EventTracking();
        injectMembers(eventTracking);
        return eventTracking;
    }

    public final void injectMembers(EventTracking object) {
        object.d = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
