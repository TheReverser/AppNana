package com.vungle.publisher.db.model;

import com.vungle.publisher.db.model.StreamingAd.Factory;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: vungle */
public final class StreamingVideo$$InjectAdapter extends Binding<StreamingVideo> implements MembersInjector<StreamingVideo>, Provider<StreamingVideo> {
    private Binding<Factory> a;
    private Binding<StreamingVideo.Factory> b;
    private Binding<Video> c;

    public StreamingVideo$$InjectAdapter() {
        super("com.vungle.publisher.db.model.StreamingVideo", "members/com.vungle.publisher.db.model.StreamingVideo", false, StreamingVideo.class);
    }

    public final void attach(Linker linker) {
        this.a = linker.requestBinding("com.vungle.publisher.db.model.StreamingAd$Factory", StreamingVideo.class, getClass().getClassLoader());
        this.b = linker.requestBinding("com.vungle.publisher.db.model.StreamingVideo$Factory", StreamingVideo.class, getClass().getClassLoader());
        Linker linker2 = linker;
        this.c = linker2.requestBinding("members/com.vungle.publisher.db.model.Video", StreamingVideo.class, getClass().getClassLoader(), false, true);
    }

    public final void getDependencies(Set<Binding<?>> set, Set<Binding<?>> injectMembersBindings) {
        injectMembersBindings.add(this.a);
        injectMembersBindings.add(this.b);
        injectMembersBindings.add(this.c);
    }

    public final StreamingVideo get() {
        StreamingVideo streamingVideo = new StreamingVideo();
        injectMembers(streamingVideo);
        return streamingVideo;
    }

    public final void injectMembers(StreamingVideo object) {
        object.a = (Factory) this.a.get();
        object.b = (StreamingVideo.Factory) this.b.get();
        this.c.injectMembers(object);
    }
}
