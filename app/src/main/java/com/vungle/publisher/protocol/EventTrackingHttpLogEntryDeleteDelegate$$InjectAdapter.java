package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class EventTrackingHttpLogEntryDeleteDelegate$$InjectAdapter extends Binding<EventTrackingHttpLogEntryDeleteDelegate> implements MembersInjector<EventTrackingHttpLogEntryDeleteDelegate>, Provider<EventTrackingHttpLogEntryDeleteDelegate> {
    private Binding<Factory> a;

    public EventTrackingHttpLogEntryDeleteDelegate$$InjectAdapter() {
        super("com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate", "members/com.vungle.publisher.protocol.EventTrackingHttpLogEntryDeleteDelegate", true, EventTrackingHttpLogEntryDeleteDelegate.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", EventTrackingHttpLogEntryDeleteDelegate.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final EventTrackingHttpLogEntryDeleteDelegate get() {
        EventTrackingHttpLogEntryDeleteDelegate eventTrackingHttpLogEntryDeleteDelegate = new EventTrackingHttpLogEntryDeleteDelegate();
        injectMembers(eventTrackingHttpLogEntryDeleteDelegate);
        return eventTrackingHttpLogEntryDeleteDelegate;
    }

    public final void injectMembers(EventTrackingHttpLogEntryDeleteDelegate object) {
        object.a = (Factory) this.a.get();
    }
}
