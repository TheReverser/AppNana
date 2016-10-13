package com.vungle.publisher.db.model;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingAdPlay$$InjectAdapter extends Binding<StreamingAdPlay> implements MembersInjector<StreamingAdPlay>, Provider<StreamingAdPlay> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<AdPlay> c;

    public StreamingAdPlay$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingAdPlay", "members/com.vungle.publisher.db.model.StreamingAdPlay", false, StreamingAdPlay.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdPlay$Factory", StreamingAdPlay.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdReportEvent$Factory", StreamingAdPlay.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.AdPlay", StreamingAdPlay.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final StreamingAdPlay get() {
        StreamingAdPlay streamingAdPlay = new StreamingAdPlay();
        injectMembers(streamingAdPlay);
        return streamingAdPlay;
    }

    public final void injectMembers(StreamingAdPlay object) {
        object.e = (Factory) this.a.get();
        object.f = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
