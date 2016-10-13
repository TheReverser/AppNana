package com.appnana.android.giftcardrewards;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.Tracker;

public class TrackedSherlockDialogFragment extends SherlockDialogFragment {
    protected Tracker mGATracker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mGATracker = ((AppNanaApp) getActivity().getApplication()).getTracker();
    }

    public void onResume() {
        super.onResume();
        this.mGATracker.setScreenName(getClass().getSimpleName());
        this.mGATracker.send(new AppViewBuilder().build());
    }
}
