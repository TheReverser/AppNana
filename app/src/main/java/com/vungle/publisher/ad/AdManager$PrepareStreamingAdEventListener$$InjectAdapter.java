package com.vungle.publisher.ad;

import com.vungle.publisher.bk;
import com.vungle.publisher.db.model.StreamingAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdManager$PrepareStreamingAdEventListener$$InjectAdapter extends Binding<PrepareStreamingAdEventListener> implements MembersInjector<PrepareStreamingAdEventListener>, Provider<PrepareStreamingAdEventListener> {
    private Binding<Factory> a;
    private Binding<bk> b;

    public AdManager$PrepareStreamingAdEventListener$$InjectAdapter() {
        super("com.vungle.publisher.ad.AdManager$PrepareStreamingAdEventListener", "members/com.vungle.publisher.ad.AdManager$PrepareStreamingAdEventListener", false, PrepareStreamingAdEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAd$Factory", PrepareStreamingAdEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", PrepareStreamingAdEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final PrepareStreamingAdEventListener get() {
        PrepareStreamingAdEventListener prepareStreamingAdEventListener = new PrepareStreamingAdEventListener();
        injectMembers(prepareStreamingAdEventListener);
        return prepareStreamingAdEventListener;
    }

    public final void injectMembers(PrepareStreamingAdEventListener object) {
        object.e = (Factory) this.a.get();
        this.b.injectMembers(object);
    }
}
