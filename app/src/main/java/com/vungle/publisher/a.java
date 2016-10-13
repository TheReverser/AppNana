package com.vungle.publisher;

import java.util.Map;

/* compiled from: vungle */
public interface a {
    Map<String, String> getExtras();

    String getIncentivizedCancelDialogBodyText();

    String getIncentivizedCancelDialogCloseButtonText();

    String getIncentivizedCancelDialogKeepWatchingButtonText();

    String getIncentivizedCancelDialogTitle();

    String getIncentivizedUserId();

    Orientation getOrientation();

    String getPlacement();

    boolean isBackButtonImmediatelyEnabled();

    boolean isImmersiveMode();

    boolean isIncentivized();

    boolean isSoundEnabled();
}
