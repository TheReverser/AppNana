package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class AppThis extends ArrayList<Offer> implements IOfferNetwork {
    public static final String API_KEY = "545a168be2f0f810dec05b72f46df268";
    private static final String TAG = AppThis.class.getSimpleName();

    public class Offer extends AbstractOffer {
        @SerializedName("android_package_name")
        private String androidPackageName;
        @SerializedName("click_url")
        private String clickUrl;
        @SerializedName("description")
        private String description;
        @SerializedName("icon_url")
        private String iconUrl;
        @SerializedName("id")
        private String id;
        private int nanas;
        @SerializedName("payout")
        private float payout;
        @SerializedName("stars")
        private String stars;
        @SerializedName("title")
        private String title;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.title;
        }

        public String getActionMessage() {
            return "Install and Open to earn credits.";
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
            this.nanas = (int) (this.payout * ((float) exchangeRate));
            return this.nanas;
        }

        public String getIconUrl() {
            if (this.iconUrl.startsWith("//")) {
                this.iconUrl = "http:" + this.iconUrl;
            }
            return this.iconUrl;
        }

        public String getActionUrl() {
            return this.clickUrl;
        }

        public String getNetwork() {
            return AppThis.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
