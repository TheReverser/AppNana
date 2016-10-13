package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class HangMyAds extends ArrayList<Offer> implements IOfferNetwork {
    public static final String PUB_ID = "767";
    private static final String TAG = HangMyAds.class.getSimpleName();

    public class Offer extends AbstractOffer {
        @SerializedName("conditions")
        String conditions;
        @SerializedName("daily_cap")
        int dailyCap;
        @SerializedName("daily_convs")
        int dailyConvs;
        @SerializedName("description")
        String description;
        @SerializedName("first_name")
        String firstName;
        @SerializedName("id")
        String id;
        @SerializedName("installed")
        int installed;
        @SerializedName("last_name")
        String lastName;
        int nanas;
        @SerializedName("pay_curr")
        String payCurr;
        @SerializedName("pay_type")
        String payType;
        @SerializedName("payout_cents")
        int payoutCents;
        @SerializedName("preview_URL")
        String previewUrl;
        @SerializedName("thumbnail")
        String thumbnail;
        @SerializedName("tracking_url")
        String trackingUrl;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.lastName;
        }

        public String getActionMessage() {
            return this.description;
        }

        public String getDesc() {
            return getActionMessage();
        }

        public boolean isFree() {
            return true;
        }

        public boolean isInstall() {
            return true;
        }

        public int getNanas() {
            return this.nanas;
        }

        public int calcNanas(int exchangeRate) {
            this.nanas = (int) (((float) exchangeRate) * (((float) this.payoutCents) / 100.0f));
            return this.nanas;
        }

        public String getIconUrl() {
            return this.thumbnail;
        }

        public String getActionUrl() {
            return this.trackingUrl;
        }

        public String getNetwork() {
            return HangMyAds.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
