package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingAdReportEvent$$InjectAdapter extends Binding<StreamingAdReportEvent> implements MembersInjector<StreamingAdReportEvent>, Provider<StreamingAdReportEvent> {
    private Binding<Factory> a;
    private Binding<AdReportEvent> b;

    public StreamingAdReportEvent$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingAdReportEvent", "members/com.vungle.publisher.db.model.StreamingAdReportEvent", false, StreamingAdReportEvent.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdReportEvent$Factory", StreamingAdReportEvent.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.AdReportEvent", StreamingAdReportEvent.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final StreamingAdReportEvent get() {
        StreamingAdReportEvent streamingAdReportEvent = new StreamingAdReportEvent();
        injectMembers(streamingAdReportEvent);
        return streamingAdReportEvent;
    }

    public final void injectMembers(StreamingAdReportEvent object) {
        object.e = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
