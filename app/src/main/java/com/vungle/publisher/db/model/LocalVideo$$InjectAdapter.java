package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.LocalAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class LocalVideo$$InjectAdapter extends Binding<LocalVideo> implements MembersInjector<LocalVideo>, Provider<LocalVideo> {
    private Binding<Factory> a;
    private Binding<LocalVideo.Factory> b;
    private Binding<Video> c;

    public LocalVideo$$InjectAdapter() {
        super("com.vungle.publisher.db.model.LocalVideo", "members/com.vungle.publisher.db.model.LocalVideo", false, LocalVideo.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.LocalAd$Factory", LocalVideo.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.LocalVideo$Factory", LocalVideo.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.Video", LocalVideo.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final LocalVideo get() {
        LocalVideo localVideo = new LocalVideo();
        injectMembers(localVideo);
        return localVideo;
    }

    public final void injectMembers(LocalVideo object) {
        object.c = (Factory) this.a.get();
        object.d = (LocalVideo.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
