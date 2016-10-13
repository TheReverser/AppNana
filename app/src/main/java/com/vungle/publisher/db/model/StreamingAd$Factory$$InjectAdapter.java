package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.StreamingAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<Provider<StreamingAd>> a;
    private Binding<StreamingVideo.Factory> b;
    private Binding<Ad.Factory> c;

    public StreamingAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingAd$Factory", "members/com.vungle.publisher.db.model.StreamingAd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.db.model.StreamingAd>", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.StreamingVideo$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.Ad$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.d = (Provider) this.a.get();
        object.e = (StreamingVideo.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
