package com.chartboost.sdk;

import com.chartboost.sdk.Chartboost.CBAgeGateConfirmation;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

public abstract class ChartboostDefaultDelegate implements ChartboostDelegate {
    public boolean shouldRequestInterstitial(String location) {
        return true;
    }

    public boolean shouldDisplayInterstitial(String location) {
        return true;
    }

    public void didCacheInterstitial(String location) {
    }

    public void didFailToLoadInterstitial(String location, CBImpressionError error) {
    }

    public void didDismissInterstitial(String location) {
    }

    public void didCloseInterstitial(String location) {
    }

    public void didClickInterstitial(String location) {
    }

    public void didShowInterstitial(String location) {
    }

    public boolean shouldDisplayLoadingViewForMoreApps() {
        return true;
    }

    public boolean shouldRequestMoreApps() {
        return true;
    }

    public void didCacheMoreApps() {
    }

    public boolean shouldDisplayMoreApps() {
        return true;
    }

    public void didFailToLoadMoreApps(CBImpressionError error) {
    }

    public void didDismissMoreApps() {
    }

    public void didCloseMoreApps() {
    }

    public void didClickMoreApps() {
    }

    public void didShowMoreApps() {
    }

    public boolean shouldRequestInterstitialsInFirstSession() {
        return true;
    }

    public void didFailToRecordClick(String uri, CBClickError error) {
    }

    public boolean shouldPauseClickForConfirmation(CBAgeGateConfirmation callback) {
        return false;
    }
}
