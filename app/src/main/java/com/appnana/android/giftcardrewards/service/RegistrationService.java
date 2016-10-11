package com.appnana.android.giftcardrewards.service;

import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class RegistrationService extends AppNanaService {
    protected String getUrl() {
        return this.baseUrl + "nanaer_register/";
    }

    protected int getRequestMethod() {
        return 1;
    }

    protected List<NameValuePair> getParams() {
        List<NameValuePair> nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("email", this.user.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("password", this.user.getPassword()));
        nameValuePairs.add(new BasicNameValuePair(ShareConstants.FEED_SOURCE_PARAM, this.user.getSource()));
        nameValuePairs.add(new BasicNameValuePair("signupkey", this.user.getSignUpKey()));
        nameValuePairs.add(new BasicNameValuePair("android_id", this.device.getAndroidID()));
        nameValuePairs.add(new BasicNameValuePair("system_version", this.device.getOSVersion()));
        nameValuePairs.add(new BasicNameValuePair("system_name", this.device.getType()));
        nameValuePairs.add(new BasicNameValuePair("device_type", this.device.getModel()));
        nameValuePairs.add(new BasicNameValuePair("android_imei", this.device.getDeviceID()));
        nameValuePairs.add(new BasicNameValuePair("gaid", this.device.getAdvertisingId()));
        nameValuePairs.add(new BasicNameValuePair("gaid_enabled", String.valueOf(!this.device.isLimitAdTrackingEnabled())));
        return nameValuePairs;
    }

    protected Class getClazz() {
        return null;
    }
}
