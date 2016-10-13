package com.appnana.android.giftcardrewards;

import android.app.Application;
import android.content.Context;
import com.appnana.android.giftcardrewards.model.OfferModel;
import com.appnana.android.net.HttpClient;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import java.util.Map;

public class AppNanaApp extends Application {
    Map<String, AbstractOffer> mOfferMap;
    Tracker mTracker;

    public Tracker getTracker() {
        if (this.mTracker == null) {
            this.mTracker = GoogleAnalytics.getInstance(this).newTracker(R.xml.analytics);
        }
        return this.mTracker;
    }

    public void onCreate() {
        super.onCreate();
        HttpClient.newInstance(getApplicationContext());
        Device.newInstance(getApplicationContext());
        MapizLog.enableLogging(false);
        FlurryAgent.setLogEnabled(false);
        FlurryAgent.init(this, OfferModel.FLURRY_API_KEY);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public void setOfferMap(Map<String, AbstractOffer> map) {
        this.mOfferMap = map;
    }

    public Map<String, AbstractOffer> getOfferMap() {
        return this.mOfferMap;
    }
}
