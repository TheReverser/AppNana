package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.ReportAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class ReportAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory> {
    private Binding<Factory> a;
    private Binding<MessageFactory> b;

    public ReportAd$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.protocol.message.ReportAd$Factory", false, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.ExtraInfo$Factory", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final void injectMembers(Factory object) {
        object.a = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
