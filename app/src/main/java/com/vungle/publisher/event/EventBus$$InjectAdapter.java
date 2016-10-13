package com.vungle.publisher.event;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class EventBus$$InjectAdapter extends Binding<EventBus> implements Provider<EventBus> {
    public EventBus$$InjectAdapter() {
        super("com.vungle.publisher.event.EventBus", "members/com.vungle.publisher.event.EventBus", true, EventBus.class);
    }

    public final EventBus get() {
        return new EventBus();
    }
}
