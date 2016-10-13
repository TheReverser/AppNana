package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.display.view.VideoFragment.Factory;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportEventListener;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class FullScreenAdActivity$$InjectAdapter extends Binding<FullScreenAdActivity> implements MembersInjector<FullScreenAdActivity>, Provider<FullScreenAdActivity> {
    private Binding<AdManager> a;
    private Binding<AdReportEventListener> b;
    private Binding<bf> c;
    private Binding<EventBus> d;
    private Binding<Factory> e;
    private Binding<SdkState> f;

    public FullScreenAdActivity$$InjectAdapter() {
        super("com.vungle.publisher.FullScreenAdActivity", "members/com.vungle.publisher.FullScreenAdActivity", false, FullScreenAdActivity.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", FullScreenAdActivity.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.reporting.AdReportEventListener", FullScreenAdActivity.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.bf", FullScreenAdActivity.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.event.EventBus", FullScreenAdActivity.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.display.view.VideoFragment$Factory", FullScreenAdActivity.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.env.SdkState", FullScreenAdActivity.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
        injectMembersBindings.add(this.d);
        injectMembersBindings.add(this.e);
        injectMembersBindings.add(this.f);
    }

    public final FullScreenAdActivity get() {
        FullScreenAdActivity fullScreenAdActivity = new FullScreenAdActivity();
        injectMembers(fullScreenAdActivity);
        return fullScreenAdActivity;
    }

    public final void injectMembers(FullScreenAdActivity object) {
        object.a = (AdManager) this.a.get();
        object.b = (AdReportEventListener) this.b.get();
        object.c = (bf) this.c.get();
        object.d = (EventBus) this.d.get();
        object.e = (Factory) this.e.get();
        object.f = (SdkState) this.f.get();
    }
}
