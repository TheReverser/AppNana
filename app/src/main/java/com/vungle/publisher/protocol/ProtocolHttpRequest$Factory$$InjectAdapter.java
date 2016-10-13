package com.vungle.publisher.protocol;

import com.vungle.publisher.bh;
import com.vungle.publisher.bz;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.net.http.HttpRequest.Factory;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class ProtocolHttpRequest$Factory$$InjectAdapter extends Binding<a> implements MembersInjector<a> {
    private Binding<bz> a;
    private Binding<bh> b;
    private Binding<String> c;
    private Binding<WrapperFramework> d;
    private Binding<String> e;
    private Binding<Factory> f;

    public ProtocolHttpRequest$Factory$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.protocol.ProtocolHttpRequest$Factory", false, a.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.bz", a.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.bh", a.class, getClass().getClassLoader());
        this.c = linker.requestBinding("@com.vungle.publisher.inject.annotations.VungleBaseUrl()/java.lang.String", a.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.env.WrapperFramework", a.class, getClass().getClassLoader());
        this.e = linker.requestBinding("@com.vungle.publisher.inject.annotations.WrapperFrameworkVersion()/java.lang.String", a.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.f = linker2.requestBinding("members/com.vungle.publisher.net.http.HttpRequest$Factory", a.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
    }

    public final void injectMembers(a object) {
        object.b = (bz) this.a.get();
        object.c = (bh) this.b.get();
        object.d = (String) this.c.get();
        object.e = (WrapperFramework) this.d.get();
        object.f = (String) this.e.get();
        this.f.injectMembers(object);
    }
}
