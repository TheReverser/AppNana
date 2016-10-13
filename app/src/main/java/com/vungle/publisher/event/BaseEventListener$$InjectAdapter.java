package com.vungle.publisher.event;

import com.vungle.publisher.bk;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class BaseEventListener$$InjectAdapter extends Binding<bk> implements MembersInjector<bk>, Provider<bk> {
    private Binding<EventBus> a;

    public BaseEventListener$$InjectAdapter() {
        super("com.vungle.publisher.bk", "members/com.vungle.publisher.event.BaseEventListener", false, bk.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.event.EventBus", bk.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final bk get() {
        bk bkVar = new bk();
        injectMembers(bkVar);
        return bkVar;
    }

    public final void injectMembers(bk object) {
        object.f = (EventBus) this.a.get();
    }
}
