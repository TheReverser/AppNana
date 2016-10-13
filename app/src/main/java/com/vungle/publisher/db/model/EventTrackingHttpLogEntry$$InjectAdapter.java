package com.vungle.publisher.db.model;

import com.vungle.publisher.as;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class EventTrackingHttpLogEntry$$InjectAdapter extends Binding<EventTrackingHttpLogEntry> implements MembersInjector<EventTrackingHttpLogEntry>, Provider<EventTrackingHttpLogEntry> {
    private Binding<Factory> a;
    private Binding<as> b;

    public EventTrackingHttpLogEntry$$InjectAdapter() {
        super("com.vungle.publisher.db.model.EventTrackingHttpLogEntry", "members/com.vungle.publisher.db.model.EventTrackingHttpLogEntry", false, EventTrackingHttpLogEntry.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", EventTrackingHttpLogEntry.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.BaseModel", EventTrackingHttpLogEntry.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final EventTrackingHttpLogEntry get() {
        EventTrackingHttpLogEntry eventTrackingHttpLogEntry = new EventTrackingHttpLogEntry();
        injectMembers(eventTrackingHttpLogEntry);
        return eventTrackingHttpLogEntry;
    }

    public final void injectMembers(EventTrackingHttpLogEntry object) {
        object.h = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
