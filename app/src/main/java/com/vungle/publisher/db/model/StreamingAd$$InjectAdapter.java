package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.StreamingAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingAd$$InjectAdapter extends Binding<StreamingAd> implements MembersInjector<StreamingAd>, Provider<StreamingAd> {
    private Binding<Factory> a;
    private Binding<Ad> b;

    public StreamingAd$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingAd", "members/com.vungle.publisher.db.model.StreamingAd", false, StreamingAd.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAd$Factory", StreamingAd.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.db.model.Ad", StreamingAd.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final StreamingAd get() {
        StreamingAd streamingAd = new StreamingAd();
        injectMembers(streamingAd);
        return streamingAd;
    }

    public final void injectMembers(StreamingAd object) {
        object.u = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
