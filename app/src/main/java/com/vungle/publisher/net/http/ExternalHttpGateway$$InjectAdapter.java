package com.vungle.publisher.net.http;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.cc;
import com.vungle.publisher.cd;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ExternalHttpGateway$$InjectAdapter extends Binding<cc> implements MembersInjector<cc>, Provider<cc> {
    private Binding<TrackEventHttpTransactionFactory> a;
    private Binding<ScheduledPriorityExecutor> b;
    private Binding<cd> c;

    public ExternalHttpGateway$$InjectAdapter() {
        super("com.vungle.publisher.cc", "members/com.vungle.publisher.net.http.ExternalHttpGateway", false, cc.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.TrackEventHttpTransactionFactory", cc.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.async.ScheduledPriorityExecutor", cc.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpGateway", cc.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final cc get() {
        cc ccVar = new cc();
        injectMembers(ccVar);
        return ccVar;
    }

    public final void injectMembers(cc object) {
        object.a = (TrackEventHttpTransactionFactory) this.a.get();
        object.b = (ScheduledPriorityExecutor) this.b.get();
        this.c.injectMembers(object);
    }
}
