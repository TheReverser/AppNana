package com.vungle.publisher.audio;

import android.media.AudioManager;
import com.vungle.publisher.audio.VolumeChangeContentObserver.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VolumeChangeContentObserver$Factory$$InjectAdapter extends Binding<Factory> implements MembersInjector<Factory>, Provider<Factory> {
    private Binding<AudioManager> a;

    public VolumeChangeContentObserver$Factory$$InjectAdapter() {
        super("com.vungle.publisher.audio.VolumeChangeContentObserver$Factory", "members/com.vungle.publisher.audio.VolumeChangeContentObserver$Factory", true, Factory.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("android.media.AudioManager", Factory.class, getClass().getClassLoader());
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
    }

    public final Factory get() {
        Factory factory = new Factory();
        injectMembers(factory);
        return factory;
    }

    public final void injectMembers(Factory object) {
        object.a = (AudioManager) this.a.get();
    }
}
