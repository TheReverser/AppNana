package com.vungle.publisher.db.model;

import com.vungle.publisher.bf;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalViewableDelegate$$InjectAdapter extends Binding<LocalViewableDelegate> implements MembersInjector<LocalViewableDelegate>, Provider<LocalViewableDelegate> {
    private Binding<DownloadHttpGateway> a;
    private Binding<bf> b;
    private Binding<SdkState> c;

    public LocalViewableDelegate$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalViewableDelegate", "members/com.vungle.publisher.db.model.LocalViewableDelegate", false, LocalViewableDelegate.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.DownloadHttpGateway", LocalViewableDelegate.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.bf", LocalViewableDelegate.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.env.SdkState", LocalViewableDelegate.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final LocalViewableDelegate get() {
        LocalViewableDelegate localViewableDelegate = new LocalViewableDelegate();
        injectMembers(localViewableDelegate);
        return localViewableDelegate;
    }

    public final void injectMembers(LocalViewableDelegate object) {
        object.e = (DownloadHttpGateway) this.a.get();
        object.f = (bf) this.b.get();
        object.g = (SdkState) this.c.get();
    }
}
