package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.NativeX;
import com.appnana.android.utils.Device;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;

public class NativeXSessionService extends OfferService {

    private class SessionRequest {
        @SerializedName("AppId")
        private int appId;
        @SerializedName("ClientIp")
        private String clientIp;
        @SerializedName("DeviceGenerationInfo")
        private String deviceGenerationInfo;
        @SerializedName("IsAdvertiserTrackingEnabled")
        private boolean isAdvertiserTrackingEnabled;
        @SerializedName("IsHacked")
        private boolean isHacked;
        @SerializedName("IsOnWifi")
        private boolean isOnWifi;
        @SerializedName("IsUsingSdk")
        private boolean isUsingSdk;
        @SerializedName("OSVersion")
        private String osVersion;
        @SerializedName("PublisherSDKVersion")
        private String publisherSDKVersion;
        @SerializedName("PublisherUserId")
        private String publisherUserId;
        @SerializedName("UDIDs")
        private List<UDID> udids;
        @SerializedName("WebViewUserAgent")
        private String webViewUserAgent;

        private class UDID {
            @SerializedName("Type")
            private int typeId;
            @SerializedName("Value")
            private String value;

            private UDID() {
                this.value = Device.getInstance().getAdvertisingId();
                this.typeId = 12;
            }
        }

        public SessionRequest(String publisherUserId) {
            this.isAdvertiserTrackingEnabled = !NativeXSessionService.this.device.isLimitAdTrackingEnabled();
            this.appId = NativeX.APP_ID;
            this.publisherUserId = publisherUserId;
            this.deviceGenerationInfo = NativeXSessionService.this.device.getModel();
            this.publisherSDKVersion = "API";
            this.udids = new ArrayList();
            this.udids.add(new UDID());
            this.osVersion = Device.getInstance().getOSVersion();
            this.isOnWifi = Device.getInstance().isOnWifi();
            this.isHacked = Device.getInstance().isHacked();
            this.isUsingSdk = false;
            this.webViewUserAgent = Device.getInstance().getWebViewUserAgent();
        }
    }

    public NativeXSessionService(String ndid) {
        super(ndid);
    }

    protected String getUrl() {
        return "http://appclick.co/PublicServices/MobileTrackingApiRestV1.svc/CreateSessionV2";
    }

    protected int getRequestMethod() {
        return 1;
    }

    protected List<NameValuePair> getParams() {
        return null;
    }

    protected Class getClazz() {
        return NativeX.Device.class;
    }

    protected String getRequestBody() {
        return new Gson().toJson(new SessionRequest(this.ndid), SessionRequest.class);
    }
}
