package com.vungle.publisher.ad;

import com.vungle.publisher.bk;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class AdManager$PlayAdEventListener$$InjectAdapter extends Binding<PlayAdEventListener> implements MembersInjector<PlayAdEventListener>, Provider<PlayAdEventListener> {
    private Binding<AdManager> a;
    private Binding<bk> b;

    public AdManager$PlayAdEventListener$$InjectAdapter() {
        super("com.vungle.publisher.ad.AdManager$PlayAdEventListener", "members/com.vungle.publisher.ad.AdManager$PlayAdEventListener", true, PlayAdEventListener.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.ad.AdManager", PlayAdEventListener.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.b = linker2.requestBinding("members/com.vungle.publisher.event.BaseEventListener", PlayAdEventListener.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
    }

    public final PlayAdEventListener get() {
        PlayAdEventListener playAdEventListener = new PlayAdEventListener();
        injectMembers(playAdEventListener);
        return playAdEventListener;
    }

    public final void injectMembers(PlayAdEventListener object) {
        object.b = (AdManager) this.a.get();
        this.b.injectMembers(object);
    }
}
