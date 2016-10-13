package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.RequestAdResponse.CallToActionOverlay.Factory;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking;
import com.vungle.publisher.protocol.message.RequestAdResponse.a;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class RequestAdResponse$Factory$$InjectAdapter extends Binding<a> implements MembersInjector<a> {
    private Binding<Factory> a;
    private Binding<ThirdPartyAdTracking.Factory> b;
    private Binding<JsonDeserializationFactory> c;

    public RequestAdResponse$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.protocol.message.RequestAdResponse$Factory", false, a.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAdResponse$CallToActionOverlay$Factory", a.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$Factory", a.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.protocol.message.JsonDeserializationFactory", a.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final void injectMembers(a object) {
        object.a = (Factory) this.a.get();
        object.b = (ThirdPartyAdTracking.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
