package com.vungle.publisher.async;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ScheduledPriorityExecutor$$InjectAdapter extends Binding<ScheduledPriorityExecutor> implements Provider<ScheduledPriorityExecutor> {
    public ScheduledPriorityExecutor$$InjectAdapter() {
        super("com.vungle.publisher.async.ScheduledPriorityExecutor", "members/com.vungle.publisher.async.ScheduledPriorityExecutor", true, ScheduledPriorityExecutor.class);
    }

    public final ScheduledPriorityExecutor get() {
        return new ScheduledPriorityExecutor();
    }
}
