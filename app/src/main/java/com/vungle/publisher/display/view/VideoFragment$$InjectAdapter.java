package com.vungle.publisher.display.view;

import android.media.AudioManager;
import com.vungle.publisher.audio.VolumeChangeContentObserver.Factory;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.image.BitmapFactory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VideoFragment$$InjectAdapter extends Binding<VideoFragment> implements MembersInjector<VideoFragment>, Provider<VideoFragment> {
    private Binding<AlertDialogFactory> a;
    private Binding<AudioManager> b;
    private Binding<BitmapFactory> c;
    private Binding<Factory> d;
    private Binding<DisplayUtils> e;
    private Binding<EventBus> f;
    private Binding<Factory> g;
    private Binding<VideoFragment.Factory> h;
    private Binding<AdFragment> i;

    public VideoFragment$$InjectAdapter() {
        super("com.vungle.publisher.display.view.VideoFragment", "members/com.vungle.publisher.display.view.VideoFragment", false, VideoFragment.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.display.view.AlertDialogFactory", VideoFragment.class, getClass().getClassLoader());
        this.b = linker.requestBinding("android.media.AudioManager", VideoFragment.class, getClass().getClassLoader());
        this.c = linker.requestBinding("com.vungle.publisher.image.BitmapFactory", VideoFragment.class, getClass().getClassLoader());
        this.d = linker.requestBinding("com.vungle.publisher.display.view.CountdownProgressView$Factory", VideoFragment.class, getClass().getClassLoader());
        this.e = linker.requestBinding("com.vungle.publisher.display.view.DisplayUtils", VideoFragment.class, getClass().getClassLoader());
        this.f = linker.requestBinding("com.vungle.publisher.event.EventBus", VideoFragment.class, getClass().getClassLoader());
        this.g = linker.requestBinding("com.vungle.publisher.audio.VolumeChangeContentObserver$Factory", VideoFragment.class, getClass().getClassLoader());
        this.h = linker.requestBinding("com.vungle.publisher.display.view.VideoFragment$Factory", VideoFragment.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.i = linker2.requestBinding("members/com.vungle.publisher.display.view.AdFragment", VideoFragment.class, getClass().getClassLoader(), false, true);
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
    }

    public final VideoFragment get() {
        VideoFragment videoFragment = new VideoFragment();
        injectMembers(videoFragment);
        return videoFragment;
    }

    public final void injectMembers(VideoFragment object) {
        object.k = (AlertDialogFactory) this.a.get();
        object.l = (AudioManager) this.b.get();
        object.m = (BitmapFactory) this.c.get();
        object.n = (Factory) this.d.get();
        object.o = (DisplayUtils) this.e.get();
        object.p = (EventBus) this.f.get();
        object.q = (Factory) this.g.get();
        object.r = (VideoFragment.Factory) this.h.get();
        this.i.injectMembers(object);
    }
}
