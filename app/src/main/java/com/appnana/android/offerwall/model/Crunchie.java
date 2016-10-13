package com.appnana.android.offerwall.model;

import com.facebook.BuildConfig;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Crunchie extends ArrayList<Offer> implements IOfferNetwork {
    private static final String TAG = Crunchie.class.getSimpleName();
    public static final String TOKEN = "ff8046cf900e0314f2b4e3de49385c32";

    class Creative {
        @SerializedName("CreativeURL")
        String creativeURL;
        @SerializedName("Height")
        String height;
        @SerializedName("Width")
        String width;

        Creative() {
        }
    }

    public class Offer extends AbstractOffer {
        @SerializedName("AppDescription")
        String appDescription;
        @SerializedName("AppID")
        String appID;
        @SerializedName("AppName")
        String appName;
        @SerializedName("CampaignID")
        String campaignID;
        @SerializedName("Category")
        String category;
        @SerializedName("Creatives")
        Creative[] creatives;
        @SerializedName("IncentAllowed")
        String incentAllowed;
        @SerializedName("Nanas")
        int nanas;
        @SerializedName("Payout")
        float payout;
        @SerializedName("PayoutType")
        String payoutType;
        @SerializedName("TrackingLink")
        String trackingLink;
        @SerializedName("UserRating")
        float userRating;
        @SerializedName("UserRatingCount")
        int userRatingCount;

        public String getId() {
            return this.campaignID;
        }

        public String getName() {
            if (this.appName != null) {
                return this.appName.replaceAll(" *(\\(Android\\).*)|(Android .* INCENT$)", BuildConfig.VERSION_NAME);
            }
            return BuildConfig.VERSION_NAME;
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
            return this.category.toLowerCase().contains("app");
        }

        public int getNanas() {
            return this.nanas;
        }

        public String getIconUrl() {
            if (this.creatives == null || this.creatives.length == 0) {
                return BuildConfig.VERSION_NAME;
            }
            return this.creatives[0].creativeURL;
        }

        public String getActionUrl() {
            return this.trackingLink;
        }

        public String getNetwork() {
            return Crunchie.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
