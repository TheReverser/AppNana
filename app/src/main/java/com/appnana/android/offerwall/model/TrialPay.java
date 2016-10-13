package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class TrialPay extends ArrayList<Offer> implements IOfferNetwork {
    private static final String TAG = TrialPay.class.getSimpleName();
    public static final String VIC = "014f576207de747e80ec0e508c38e738";

    public class Offer extends AbstractOffer {
        @SerializedName("button_label")
        private String buttonLabel;
        @SerializedName("description")
        private String description;
        @SerializedName("help")
        private String help;
        @SerializedName("id")
        private String id;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("impression_url")
        private String impressionUrl;
        @SerializedName("instructions")
        private String instructions;
        @SerializedName("link")
        private String link;
        @SerializedName("reward_name")
        private String rewardName;
        @SerializedName("title")
        private String title;
        @SerializedName("vc_amount")
        private int vcAmount;

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.title;
        }

        public String getActionMessage() {
            return this.instructions;
        }

        public String getDesc() {
            return this.instructions;
        }

        public boolean isFree() {
            return true;
        }

        public boolean isInstall() {
            return true;
        }

        public int getNanas() {
            return this.vcAmount;
        }

        public String getIconUrl() {
            return this.imageUrl;
        }

        public String getActionUrl() {
            return this.link + "&platform=android";
        }

        public String getNetwork() {
            return TrialPay.TAG;
        }

        public String getImpressionUrl() {
            return this.impressionUrl;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
