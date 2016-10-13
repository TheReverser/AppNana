package com.appnana.android.offerwall.model;

import com.facebook.BuildConfig;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Aarki extends ArrayList<Offer> implements IOfferNetwork {
    public static final String PLACEMENT_ID = "49D4B67277BAA3E4AA";
    private static final String TAG = Aarki.class.getSimpleName();

    public class Offer extends AbstractOffer {
        @SerializedName("ad_copy")
        String adCopy;
        @SerializedName("gross_payout")
        String grossPayout;
        @SerializedName("image_url")
        String imageUrl;
        @SerializedName("name")
        String name;
        @SerializedName("offer_id")
        String offerId;
        @SerializedName("offer_type")
        String offerType;
        @SerializedName("payout")
        String payout;
        @SerializedName("purchase")
        boolean purchase;
        @SerializedName("reward")
        String reward;
        @SerializedName("url")
        String url;

        public String getId() {
            return this.offerId;
        }

        public String getName() {
            if (this.name == null) {
                return null;
            }
            return this.name.replace(" (Free)", BuildConfig.VERSION_NAME);
        }

        public String getActionMessage() {
            return this.adCopy;
        }

        public String getDesc() {
            return this.adCopy;
        }

        public boolean isFree() {
            return !this.purchase;
        }

        public boolean isInstall() {
            return this.offerType.equals(com.appnana.android.offerwall.model.AppNana.Offer.TYPE_INSTALL);
        }

        public int getNanas() {
            return Integer.valueOf(this.reward.split(" ")[0].replace(",", BuildConfig.VERSION_NAME)).intValue();
        }

        public String getIconUrl() {
            return this.imageUrl;
        }

        public String getActionUrl() {
            return this.url;
        }

        public String getNetwork() {
            return Aarki.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this;
    }
}
