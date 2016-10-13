package com.vungle.publisher.db;

import android.content.Context;
import com.vungle.publisher.bh;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DatabaseBroadcastReceiver$$InjectAdapter extends Binding<DatabaseBroadcastReceiver> implements MembersInjector<DatabaseBroadcastReceiver>, Provider<DatabaseBroadcastReceiver> {
    private Binding<Context> a;
    private Binding<DatabaseHelper> b;
    private Binding<bh> c;

    public DatabaseBroadcastReceiver$$InjectAdapter() {
        super("com.vungle.publisher.db.DatabaseBroadcastReceiver", "members/com.vungle.publisher.db.DatabaseBroadcastReceiver", true, DatabaseBroadcastReceiver.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.content.Context", DatabaseBroadcastReceiver.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", DatabaseBroadcastReceiver.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.bh", DatabaseBroadcastReceiver.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final DatabaseBroadcastReceiver get() {
        DatabaseBroadcastReceiver databaseBroadcastReceiver = new DatabaseBroadcastReceiver();
        injectMembers(databaseBroadcastReceiver);
        return databaseBroadcastReceiver;
    }

    public final void injectMembers(DatabaseBroadcastReceiver object) {
        object.a = (Context) this.a.get();
        object.b = (DatabaseHelper) this.b.get();
        object.c = (bh) this.c.get();
    }
}
