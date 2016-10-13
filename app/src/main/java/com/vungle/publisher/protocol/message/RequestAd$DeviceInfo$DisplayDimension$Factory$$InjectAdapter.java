package com.vungle.publisher.protocol.message;

import com.vungle.publisher.bf;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestAd$DeviceInfo$DisplayDimension$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<bf> a;
    private Binding<MessageFactory> b;

    public RequestAd$DeviceInfo$DisplayDimension$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory", "members/com.vungle.publisher.protocol.message.RequestAd$DeviceInfo$DisplayDimension$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bf", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.protocol.message.MessageFactory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (bf) this.a.get();
        this.b.injectMembers(object);
    }
}
