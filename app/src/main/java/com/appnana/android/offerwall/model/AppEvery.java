package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class AppEvery extends ArrayList<Offer> implements IOfferNetwork {
    public static final String PUBLISHER_ID = "b03e4eef52f2d3e4a18189348b3be91a";
    private static final String TAG = AppEvery.class.getSimpleName();

    public class Offer extends AbstractOffer {
        @SerializedName("app_icon_url")
        private String appIconUrl;
        @SerializedName("app_id")
        private String appId;
        @SerializedName("app_price")
        private float appPrice;
        @SerializedName("conditions")
        private String conditions;
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("nanas")
        private int nanas;
        @SerializedName("network")
        private String network;
        @SerializedName("tracking_url")
        private String trackingUrl;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getActionMessage() {
            return this.conditions;
        }

        public String getDesc() {
            return this.conditions;
        }

        public boolean isFree() {
            return this.appPrice == 0.0f;
        }

        public boolean isInstall() {
            return true;
        }

        public int getNanas() {
            return this.nanas;
        }

        public String getIconUrl() {
            return this.appIconUrl;
        }

        public String getActionUrl() {
            return this.trackingUrl;
        }

        public String getNetwork() {
            return this.network;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
