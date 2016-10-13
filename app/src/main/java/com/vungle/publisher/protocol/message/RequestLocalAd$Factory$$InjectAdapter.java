package com.vungle.publisher.protocol.message;

import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.protocol.message.RequestAd.a;
import com.vungle.publisher.protocol.message.RequestLocalAd.Factory;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestLocalAd$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<EventTrackingHttpLogEntry.Factory> a;
    private Binding<Factory> b;
    private Binding<Lazy<SdkState>> c;
    private Binding<a> d;

    public RequestLocalAd$Factory$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestLocalAd$Factory", "members/com.vungle.publisher.protocol.message.RequestLocalAd$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.EventTrackingHttpLogEntry$Factory", Factory.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestLocalAd$HttpLogEntry$Factory", Factory.class, getClass().getClassLoader());
        this.c = linker.requestBinding("dagger.Lazy<com.vungle.publisher.env.SdkState>", Factory.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.d = linker2.requestBinding("members/com.vungle.publisher.protocol.message.RequestAd$Factory", Factory.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.e = (EventTrackingHttpLogEntry.Factory) this.a.get();
        object.f = (Factory) this.b.get();
        object.g = (Lazy) this.c.get();
        this.d.injectMembers(object);
    }
}
