package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;

/* compiled from: vungle */
public final class VunglePubBase$$InjectAdapter extends Binding<VunglePubBase> implements MembersInjector<VunglePubBase> {
    private Binding<AdManager> a;
    private Binding<AsyncInitEventListener> b;
    private Binding<CacheManager> c;
    private Binding<DatabaseHelper> d;
    private Binding<Demographic> e;
    private Binding<bf> f;
    private Binding<EventBus> g;
    private Binding<AdConfig> h;
    private Binding<SafeBundleAdConfigFactory> i;
    private Binding<SdkConfig> j;
    private Binding<SdkState> k;

    public VunglePubBase$$InjectAdapter() {
        super(null, "members/com.vungle.publisher.VunglePubBase", false, VunglePubBase.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", VunglePubBase.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.VunglePubBase$AsyncInitEventListener", VunglePubBase.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.file.CacheManager", VunglePubBase.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.db.DatabaseHelper", VunglePubBase.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.Demographic", VunglePubBase.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.bf", VunglePubBase.class, getClass().getClassLoader());
        this.g = linker.requestBinding("com.vungle.publisher.event.EventBus", VunglePubBase.class, getClass().getClassLoader());
        this.h = linker.requestBinding("com.vungle.publisher.AdConfig", VunglePubBase.class, getClass().getClassLoader());
        this.i = linker.requestBinding("com.vungle.publisher.SafeBundleAdConfigFactory", VunglePubBase.class, getClass().getClassLoader());
        this.j = linker.requestBinding("com.vungle.publisher.env.SdkConfig", VunglePubBase.class, getClass().getClassLoader());
        this.k = linker.requestBinding("com.vungle.publisher.env.SdkState", VunglePubBase.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
        injectMembersBindings.add(this.g);
        injectMembersBindings.add(this.h);
        injectMembersBindings.add(this.i);
        injectMembersBindings.add(this.j);
        injectMembersBindings.add(this.k);
    }

    public final void injectMembers(VunglePubBase object) {
        object.a = (AdManager) this.a.get();
        object.b = (AsyncInitEventListener) this.b.get();
        object.c = (CacheManager) this.c.get();
        object.d = (DatabaseHelper) this.d.get();
        object.e = (Demographic) this.e.get();
        object.f = (bf) this.f.get();
        object.g = (EventBus) this.g.get();
        object.h = (AdConfig) this.h.get();
        object.i = (SafeBundleAdConfigFactory) this.i.get();
        object.j = (SdkConfig) this.j.get();
        object.k = (SdkState) this.k.get();
    }
}
