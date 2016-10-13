package com.vungle.publisher.device;

import android.content.Context;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ExternalStorageStateBroadcastReceiver$$InjectAdapter extends Binding<ExternalStorageStateBroadcastReceiver> implements MembersInjector<ExternalStorageStateBroadcastReceiver>, Provider<ExternalStorageStateBroadcastReceiver> {
    private Binding<Context> a;
    private Binding<EventBus> b;

    public ExternalStorageStateBroadcastReceiver$$InjectAdapter() {
        super("com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver", "members/com.vungle.publisher.device.ExternalStorageStateBroadcastReceiver", true, ExternalStorageStateBroadcastReceiver.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", ExternalStorageStateBroadcastReceiver.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.event.EventBus", ExternalStorageStateBroadcastReceiver.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final ExternalStorageStateBroadcastReceiver get() {
        ExternalStorageStateBroadcastReceiver externalStorageStateBroadcastReceiver = new ExternalStorageStateBroadcastReceiver();
        injectMembers(externalStorageStateBroadcastReceiver);
        return externalStorageStateBroadcastReceiver;
    }

    public final void injectMembers(ExternalStorageStateBroadcastReceiver object) {
        object.a = (Context) this.a.get();
        object.b = (EventBus) this.b.get();
    }
}
