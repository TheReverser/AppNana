package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.ReportLocalAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ReportLocalAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<PlayFactory> a;
    private Binding<RequestLocalAd.Factory> b;
    private Binding<ReportAd.Factory> c;

    public ReportLocalAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.ReportLocalAd$Factory", "members/com.vungle.publisher.protocol.message.ReportLocalAd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.ReportLocalAd$Factory$PlayFactory", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestLocalAd$Factory", Factory.class, getClass().getClassLoader());
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
        object.c = (RequestLocalAd.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
