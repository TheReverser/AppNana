package com.appnana.android.giftcardrewards;

import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.Tracker;

public class TrackedSherlockFragment extends SherlockFragment {
    private AdView mAdView;
    private Tracker mGATracker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mGATracker = ((AppNanaApp) getActivity().getApplication()).getTracker();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdView(view);
    }

    public void onResume() {
        super.onResume();
        this.mGATracker.setScreenName(getClass().getSimpleName());
        this.mGATracker.send(new AppViewBuilder().build());
        if (this.mAdView != null) {
            this.mAdView.resume();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mAdView != null) {
            this.mAdView.pause();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
    }

    private void initAdView(View view) {
        this.mAdView = (AdView) view.findViewById(R.id.ad_view);
        this.mAdView.loadAd(new Builder().build());
    }

    public MainActivity getSherlockActivity() {
        return (MainActivity) super.getSherlockActivity();
    }
}
