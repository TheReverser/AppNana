package com.vungle.publisher.protocol;

import com.vungle.publisher.bk;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ProtocolHttpGateway$PrepareLocalAdEventListener$$InjectAdapter extends Binding<PrepareLocalAdEventListener> implements MembersInjector<PrepareLocalAdEventListener>, Provider<PrepareLocalAdEventListener> {
    private Binding<Provider<ProtocolHttpGateway>> a;
    private Binding<bk> b;

    public ProtocolHttpGateway$PrepareLocalAdEventListener$$InjectAdapter() {
        super("com.vungle.publisher.protocol.ProtocolHttpGateway$PrepareLocalAdEventListener", "members/com.vungle.publisher.protocol.ProtocolHttpGateway$PrepareLocalAdEventListener", true, PrepareLocalAdEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("javax.inject.Provider<com.vungle.publisher.protocol.ProtocolHttpGateway>", PrepareLocalAdEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", PrepareLocalAdEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final PrepareLocalAdEventListener get() {
        PrepareLocalAdEventListener prepareLocalAdEventListener = new PrepareLocalAdEventListener();
        injectMembers(prepareLocalAdEventListener);
        return prepareLocalAdEventListener;
    }

    public final void injectMembers(PrepareLocalAdEventListener object) {
        object.a = (Provider) this.a.get();
        this.b.injectMembers(object);
    }
}
