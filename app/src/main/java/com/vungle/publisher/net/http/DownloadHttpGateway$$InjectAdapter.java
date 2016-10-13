package com.vungle.publisher.net.http;

import com.vungle.publisher.cd;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DownloadHttpGateway$$InjectAdapter extends Binding<DownloadHttpGateway> implements MembersInjector<DownloadHttpGateway>, Provider<DownloadHttpGateway> {
    private Binding<DownloadHttpTransactionFactory> a;
    private Binding<cd> b;

    public DownloadHttpGateway$$InjectAdapter() {
        super("com.vungle.publisher.net.http.DownloadHttpGateway", "members/com.vungle.publisher.net.http.DownloadHttpGateway", true, DownloadHttpGateway.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.DownloadHttpTransactionFactory", DownloadHttpGateway.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpGateway", DownloadHttpGateway.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final DownloadHttpGateway get() {
        DownloadHttpGateway downloadHttpGateway = new DownloadHttpGateway();
        injectMembers(downloadHttpGateway);
        return downloadHttpGateway;
    }

    public final void injectMembers(DownloadHttpGateway object) {
        object.a = (DownloadHttpTransactionFactory) this.a.get();
        this.b.injectMembers(object);
    }
}
