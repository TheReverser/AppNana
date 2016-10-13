package com.vungle.publisher.env;

import com.vungle.publisher.bk;
import com.vungle.publisher.env.SdkState.EndAdEventListener;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class SdkState$EndAdEventListener$$InjectAdapter extends Binding<EndAdEventListener> implements MembersInjector<EndAdEventListener>, Provider<EndAdEventListener> {
    private Binding<SdkState> a;
    private Binding<bk> b;

    public SdkState$EndAdEventListener$$InjectAdapter() {
        super("com.vungle.publisher.env.SdkState$EndAdEventListener", "members/com.vungle.publisher.env.SdkState$EndAdEventListener", true, EndAdEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.env.SdkState", EndAdEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", EndAdEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final EndAdEventListener get() {
        EndAdEventListener endAdEventListener = new EndAdEventListener();
        injectMembers(endAdEventListener);
        return endAdEventListener;
    }

    public final void injectMembers(EndAdEventListener object) {
        object.a = (SdkState) this.a.get();
        this.b.injectMembers(object);
    }
}
