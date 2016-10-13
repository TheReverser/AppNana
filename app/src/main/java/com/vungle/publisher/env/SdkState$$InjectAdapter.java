package com.vungle.publisher.env;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.bf;
import com.vungle.publisher.db.DatabaseBroadcastReceiver;
import com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.NetworkBroadcastReceiver;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SdkState$$InjectAdapter extends Binding<SdkState> implements MembersInjector<SdkState>, Provider<SdkState> {
    private Binding<AdManager> a;
    private Binding<Context> b;
    private Binding<DatabaseBroadcastReceiver> c;
    private Binding<bf> d;
    private Binding<EventBus> e;
    private Binding<ExternalStorageStateBroadcastReceiver> f;
    private Binding<NetworkBroadcastReceiver> g;
    private Binding<ScheduledPriorityExecutor> h;
    private Binding<AdThrottleEndRunnable> i;
    private Binding<ProtocolHttpGateway> j;
    private Binding<Lazy<EndAdEventListener>> k;
    private Binding<SharedPreferences> l;

    public SdkState$$InjectAdapter() {
        super("com.vungle.publisher.env.SdkState", "members/com.vungle.publisher.env.SdkState", true, SdkState.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", SdkState.class, getClass().getClassLoader());
        this.b = linker.requestBinding("android.content.Context", SdkState.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.db.DatabaseBroadcastReceiver", SdkState.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.bf", SdkState.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.event.EventBus", SdkState.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver", SdkState.class, getClass().getClassLoader());
        this.g = linker.requestBinding("com.vungle.publisher.net.NetworkBroadcastReceiver", SdkState.class, getClass().getClassLoader());
        this.h = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", SdkState.class, getClass().getClassLoader());
        this.i = linker.requestBinding("com.vungle.publisher.env.SdkState$AdThrottleEndRunnable", SdkState.class, getClass().getClassLoader());
        this.j = linker.requestBinding("com.vungle.publisher.protocol.ProtocolHttpGateway", SdkState.class, getClass().getClassLoader());
        this.k = linker.requestBinding("dagger.Lazy<com.vungle.publisher.env.SdkState$EndAdEventListener>", SdkState.class, getClass().getClassLoader());
        this.l = linker.requestBinding("@com.vungle.publisher.inject.annotations.EnvSharedPreferences()/android.content.SharedPreferences", SdkState.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
        injectMembersBindings.add(this.g);
        injectMembersBindings.add(this.h);
        injectMembersBindings.add(this.i);
        injectMembersBindings.add(this.j);
        injectMembersBindings.add(this.k);
        injectMembersBindings.add(this.l);
    }

    public final SdkState get() {
        SdkState sdkState = new SdkState();
        injectMembers(sdkState);
        return sdkState;
    }

    public final void injectMembers(SdkState object) {
        object.a = (AdManager) this.a.get();
        object.b = (Context) this.b.get();
        object.c = (DatabaseBroadcastReceiver) this.c.get();
        object.d = (bf) this.d.get();
        object.e = (EventBus) this.e.get();
        object.f = (ExternalStorageStateBroadcastReceiver) this.f.get();
        object.g = (NetworkBroadcastReceiver) this.g.get();
        object.h = (ScheduledPriorityExecutor) this.h.get();
        object.i = (AdThrottleEndRunnable) this.i.get();
        object.j = (ProtocolHttpGateway) this.j.get();
        object.k = (Lazy) this.k.get();
        object.o = (SharedPreferences) this.l.get();
    }
}
