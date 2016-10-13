package com.vungle.publisher.protocol.message;

import com.vungle.publisher.AdConfig;
import com.vungle.publisher.bf;
import com.vungle.publisher.bh;
import com.vungle.publisher.cb;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestAd$DeviceInfo$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<AdConfig> a;
    private Binding<bf> b;
    private Binding<DisplayDimension.Factory> c;
    private Binding<cb> d;
    private Binding<bh> e;
    private Binding<MessageFactory> f;

    public RequestAd$DeviceInfo$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory", "members/com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.AdConfig", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.bf", Factory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory", Factory.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.cb", Factory.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.bh", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.f = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (AdConfig) this.a.get();
        object.b = (bf) this.b.get();
        object.c = (DisplayDimension.Factory) this.c.get();
        object.d = (cb) this.d.get();
        object.e = (bh) this.e.get();
        this.f.injectMembers(object);
    }
}
