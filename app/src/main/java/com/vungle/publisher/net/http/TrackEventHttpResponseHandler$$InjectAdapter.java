package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class TrackEventHttpResponseHandler$$InjectAdapter extends Binding<TrackEventHttpResponseHandler> implements MembersInjector<TrackEventHttpResponseHandler>, Provider<TrackEventHttpResponseHandler> {
    private Binding<Factory> a;
    private Binding<MaxRetryAgeHttpResponseHandler> b;

    public TrackEventHttpResponseHandler$$InjectAdapter() {
        super("com.vungle.publisher.net.http.TrackEventHttpResponseHandler", "members/com.vungle.publisher.net.http.TrackEventHttpResponseHandler", false, TrackEventHttpResponseHandler.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", TrackEventHttpResponseHandler.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler", TrackEventHttpResponseHandler.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final TrackEventHttpResponseHandler get() {
        TrackEventHttpResponseHandler trackEventHttpResponseHandler = new TrackEventHttpResponseHandler();
        injectMembers(trackEventHttpResponseHandler);
        return trackEventHttpResponseHandler;
    }

    public final void injectMembers(TrackEventHttpResponseHandler object) {
        object.b = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
