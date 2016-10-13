package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VunglePubBase$AsyncInitEventListener$$InjectAdapter extends Binding<AsyncInitEventListener> implements MembersInjector<AsyncInitEventListener>, Provider<AsyncInitEventListener> {
    private Binding<AdManager> a;
    private Binding<ScheduledPriorityExecutor> b;
    private Binding<ProtocolHttpGateway> c;
    private Binding<AdReportManager> d;
    private Binding<SdkState> e;
    private Binding<bk> f;

    public VunglePubBase$AsyncInitEventListener$$InjectAdapter() {
        super("com.vungle.publisher.VunglePubBase$AsyncInitEventListener", "members/com.vungle.publisher.VunglePubBase$AsyncInitEventListener", true, AsyncInitEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", AsyncInitEventListener.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", AsyncInitEventListener.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.protocol.ProtocolHttpGateway", AsyncInitEventListener.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.reporting.AdReportManager", AsyncInitEventListener.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.env.SdkState", AsyncInitEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.f = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", AsyncInitEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
    }

    public final AsyncInitEventListener get() {
        AsyncInitEventListener asyncInitEventListener = new AsyncInitEventListener();
        injectMembers(asyncInitEventListener);
        return asyncInitEventListener;
    }

    public final void injectMembers(AsyncInitEventListener object) {
        object.a = (AdManager) this.a.get();
        object.b = (ScheduledPriorityExecutor) this.b.get();
        object.c = (ProtocolHttpGateway) this.c.get();
        object.d = (AdReportManager) this.d.get();
        object.e = (SdkState) this.e.get();
        this.f.injectMembers(object);
    }
}
