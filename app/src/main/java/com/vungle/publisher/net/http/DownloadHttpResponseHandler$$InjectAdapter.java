package com.vungle.publisher.net.http;

import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class DownloadHttpResponseHandler$$InjectAdapter extends Binding<DownloadHttpResponseHandler> implements MembersInjector<DownloadHttpResponseHandler>, Provider<DownloadHttpResponseHandler> {
    private Binding<AdPreparer> a;
    private Binding<EventBus> b;
    private Binding<MaxRetryAgeHttpResponseHandler> c;

    public DownloadHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.net.http.DownloadHttpResponseHandler", "members/com.vungle.publisher.net.http.DownloadHttpResponseHandler", false, DownloadHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdPreparer", DownloadHttpResponseHandler.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.event.EventBus", DownloadHttpResponseHandler.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler", DownloadHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final DownloadHttpResponseHandler get() {
        DownloadHttpResponseHandler downloadHttpResponseHandler = new DownloadHttpResponseHandler();
        injectMembers(downloadHttpResponseHandler);
        return downloadHttpResponseHandler;
    }

    public final void injectMembers(DownloadHttpResponseHandler object) {
        object.d = (AdPreparer) this.a.get();
        object.e = (EventBus) this.b.get();
        this.c.injectMembers(object);
    }
}
