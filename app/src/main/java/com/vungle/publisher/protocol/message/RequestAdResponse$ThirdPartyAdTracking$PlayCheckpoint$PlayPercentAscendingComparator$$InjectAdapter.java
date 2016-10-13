package com.vungle.publisher.protocol.message;

import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking.PlayCheckpoint.PlayPercentAscendingComparator;
import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: vungle */
public final class RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator$$InjectAdapter extends Binding<PlayPercentAscendingComparator> implements Provider<PlayPercentAscendingComparator> {
    public RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator$$InjectAdapter() {
        super("com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator", "members/com.vungle.publisher.protocol.message.RequestAdResponse$ThirdPartyAdTracking$PlayCheckpoint$PlayPercentAscendingComparator", true, PlayPercentAscendingComparator.class);
    }

    public final PlayPercentAscendingComparator get() {
        return new PlayPercentAscendingComparator();
    }
}
