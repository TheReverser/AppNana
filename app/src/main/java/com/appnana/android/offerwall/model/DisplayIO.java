package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class DisplayIO extends ArrayList<Offer> implements IOfferNetwork {
    public static final String PLACEMENT_ID = "1";
    private static final String TAG = DisplayIO.class.getSimpleName();

    public class Offer extends AbstractOffer {
        @SerializedName("bundleId")
        String bundleId;
        @SerializedName("categories")
        List categories;
        @SerializedName("clickurl")
        String clickUrl;
        @SerializedName("countries")
        List countries;
        @SerializedName("countryCodes")
        List countryCodes;
        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;
        private int nanas;
        @SerializedName("os")
        String os;
        @SerializedName("payout")
        float payout;
        @SerializedName("previewUrl")
        String previewUrl;
        @SerializedName("storeTitle")
        String storeTitle;
        @SerializedName("thumbnail")
        String thumbnail;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.storeTitle;
        }

        public String getActionMessage() {
            if (needRegistration()) {
                return null;
            }
            return "Install and Open to earn credits.";
        }

        public String getDesc() {
            return getActionMessage();
        }

        public boolean isFree() {
            return true;
        }

        public boolean isInstall() {
            return !needRegistration();
        }

        public int getNanas() {
            return this.nanas;
        }

        public int calcNanas(int exchangeRate) {
            this.nanas = (int) (this.payout * ((float) exchangeRate));
            return this.nanas;
        }

        public String getIconUrl() {
            return this.thumbnail;
        }

        public String getActionUrl() {
            return this.clickUrl;
        }

        public String getNetwork() {
            return DisplayIO.TAG;
        }

        private boolean needRegistration() {
            return this.categories.contains("Registration");
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
