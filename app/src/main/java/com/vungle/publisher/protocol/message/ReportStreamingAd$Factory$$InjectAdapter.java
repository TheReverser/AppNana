package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.ReportStreamingAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportStreamingAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<PlayFactory> a;
    private Binding<RequestStreamingAd.Factory> b;
    private Binding<ReportAd.Factory> c;

    public ReportStreamingAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.ReportStreamingAd$Factory", "members/com.vungle.publisher.protocol.message.ReportStreamingAd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.ReportStreamingAd$Factory$PlayFactory", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestStreamingAd$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.protocol.message.ReportAd$Factory", Factory.class, getClass().getClassLoader(), false, true);
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
        object.b = (PlayFactory) this.a.get();
        object.c = (RequestStreamingAd.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
