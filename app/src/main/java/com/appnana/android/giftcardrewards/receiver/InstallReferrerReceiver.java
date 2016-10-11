package com.appnana.android.giftcardrewards.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appnana.android.giftcardrewards.model.AppNanaPrefrences;
import com.appnana.android.utils.MapizLog;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import java.net.URLDecoder;
import java.util.HashMap;

public class InstallReferrerReceiver extends BroadcastReceiver {
    private static final String SOURCE_KEY = "utm_source";
    private static final String TAG = "InstallReferrerReceiver";

    public void onReceive(Context context, Intent intent) {
        HashMap<String, String> values = new HashMap();
        try {
            if (intent.hasExtra("referrer")) {
                for (String referrerValue : intent.getStringExtra("referrer").split("&")) {
                    String[] keyValue = referrerValue.split("=");
                    values.put(URLDecoder.decode(keyValue[0]), URLDecoder.decode(keyValue[1]));
                }
                if (values.containsKey(SOURCE_KEY)) {
                    context.getSharedPreferences(AppNanaPrefrences.PREFERENCE, 0).edit().putString(AppNanaPrefrences.PREF_SOURCE, (String) values.get(SOURCE_KEY)).apply();
                }
            }
        } catch (Exception e) {
        }
        new CampaignTrackingReceiver().onReceive(context, intent);
        MapizLog.d(TAG, "referrer: " + values);
    }
}
