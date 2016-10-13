package com.vungle.publisher.net.http;

import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DownloadHttpTransactionFactory$$InjectAdapter extends Binding<DownloadHttpTransactionFactory> implements MembersInjector<DownloadHttpTransactionFactory>, Provider<DownloadHttpTransactionFactory> {
    private Binding<Factory> a;
    private Binding<Factory> b;
    private Binding<Factory> c;

    public DownloadHttpTransactionFactory$$InjectAdapter() {
        super("com.vungle.publisher.net.http.DownloadHttpTransactionFactory", "members/com.vungle.publisher.net.http.DownloadHttpTransactionFactory", true, DownloadHttpTransactionFactory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.net.http.DownloadHttpRequest$Factory", DownloadHttpTransactionFactory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.net.http.DownloadHttpResponseHandler$Factory", DownloadHttpTransactionFactory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpTransaction$Factory", DownloadHttpTransactionFactory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final DownloadHttpTransactionFactory get() {
        DownloadHttpTransactionFactory downloadHttpTransactionFactory = new DownloadHttpTransactionFactory();
        injectMembers(downloadHttpTransactionFactory);
        return downloadHttpTransactionFactory;
    }

    public final void injectMembers(DownloadHttpTransactionFactory object) {
        object.a = (Factory) this.a.get();
        object.b = (Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
