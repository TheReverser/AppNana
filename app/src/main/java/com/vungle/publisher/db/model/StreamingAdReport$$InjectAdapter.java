package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.StreamingAdReport.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingAdReport$$InjectAdapter extends Binding<StreamingAdReport> implements MembersInjector<StreamingAdReport>, Provider<StreamingAdReport> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<AdReport> c;

    public StreamingAdReport$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingAdReport", "members/com.vungle.publisher.db.model.StreamingAdReport", false, StreamingAdReport.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdReport$Factory", StreamingAdReport.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.StreamingAdPlay$Factory", StreamingAdReport.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.AdReport", StreamingAdReport.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final StreamingAdReport get() {
        StreamingAdReport streamingAdReport = new StreamingAdReport();
        injectMembers(streamingAdReport);
        return streamingAdReport;
    }

    public final void injectMembers(StreamingAdReport object) {
        object.p = (Factory) this.a.get();
        object.q = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
