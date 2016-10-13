package com.vungle.publisher.db.model;

import com.vungle.publisher.as.a;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class EventTrackingHttpLogEntry$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<EventTrackingHttpLogEntry>> a;
    private Binding<a> b;

    public EventTrackingHttpLogEntry$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", "members/com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.db.model.EventTrackingHttpLogEntry>", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (Provider) this.a.get();
        this.b.injectMembers(object);
    }
}
