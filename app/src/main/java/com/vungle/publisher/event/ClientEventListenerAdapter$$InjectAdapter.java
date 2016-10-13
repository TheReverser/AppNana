package com.vungle.publisher.event;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.bk;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ClientEventListenerAdapter$$InjectAdapter extends Binding<ClientEventListenerAdapter> implements MembersInjector<ClientEventListenerAdapter>, Provider<ClientEventListenerAdapter> {
    private Binding<ScheduledPriorityExecutor> a;
    private Binding<AdManager> b;
    private Binding<bk> c;

    public ClientEventListenerAdapter$$InjectAdapter() {
        super("com.vungle.publisher.event.ClientEventListenerAdapter", "members/com.vungle.publisher.event.ClientEventListenerAdapter", false, ClientEventListenerAdapter.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", ClientEventListenerAdapter.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.ad.AdManager", ClientEventListenerAdapter.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", ClientEventListenerAdapter.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final ClientEventListenerAdapter get() {
        ClientEventListenerAdapter clientEventListenerAdapter = new ClientEventListenerAdapter();
        injectMembers(clientEventListenerAdapter);
        return clientEventListenerAdapter;
    }

    public final void injectMembers(ClientEventListenerAdapter object) {
        object.b = (ScheduledPriorityExecutor) this.a.get();
        object.c = (AdManager) this.b.get();
        this.c.injectMembers(object);
    }
}
