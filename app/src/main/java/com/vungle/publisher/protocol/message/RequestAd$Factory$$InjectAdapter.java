package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import com.vungle.publisher.protocol.message.RequestAd.Demographic.Factory;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo;
import com.vungle.publisher.protocol.message.RequestAd.a;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class RequestAd$Factory$$InjectAdapter extends Binding<a> implements MembersInjector<a> {
    private Binding<Factory> a;
    private Binding<bf> b;
    private Binding<DeviceInfo.Factory> c;
    private Binding<bh> d;
    private Binding<MessageFactory> e;

    public RequestAd$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.protocol.message.RequestAd$Factory", false, a.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAd$Demographic$Factory", a.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.bf", a.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory", a.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.bh", a.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.e = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", a.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
    }

    public final void injectMembers(a object) {
        object.a = (Factory) this.a.get();
        object.b = (bf) this.b.get();
        object.c = (DeviceInfo.Factory) this.c.get();
        object.d = (bh) this.d.get();
        this.e.injectMembers(object);
    }
}
