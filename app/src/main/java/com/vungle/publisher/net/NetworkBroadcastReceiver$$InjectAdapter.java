package com.vungle.publisher.net;

import android.content.Context;
import com.vungle.publisher.cb;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class NetworkBroadcastReceiver$$InjectAdapter extends Binding<NetworkBroadcastReceiver> implements MembersInjector<NetworkBroadcastReceiver>, Provider<NetworkBroadcastReceiver> {
    private Binding<Context> a;
    private Binding<cb> b;
    private Binding<EventBus> c;

    public NetworkBroadcastReceiver$$InjectAdapter() {
        super("com.vungle.publisher.net.NetworkBroadcastReceiver", "members/com.vungle.publisher.net.NetworkBroadcastReceiver", true, NetworkBroadcastReceiver.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", NetworkBroadcastReceiver.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.cb", NetworkBroadcastReceiver.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.event.EventBus", NetworkBroadcastReceiver.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final NetworkBroadcastReceiver get() {
        NetworkBroadcastReceiver networkBroadcastReceiver = new NetworkBroadcastReceiver();
        injectMembers(networkBroadcastReceiver);
        return networkBroadcastReceiver;
    }

    public final void injectMembers(NetworkBroadcastReceiver object) {
        object.b = (Context) this.a.get();
        object.c = (cb) this.b.get();
        object.d = (EventBus) this.c.get();
    }
}
