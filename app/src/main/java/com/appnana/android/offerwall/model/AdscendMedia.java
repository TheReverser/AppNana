package com.appnana.android.offerwall.model;

import com.facebook.BuildConfig;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.regex.Pattern;

public class AdscendMedia implements IOfferNetwork {
    public static final int AD_WALL_ID = 1952;
    public static final int PUBLISHER_ID = 32240;
    private static final String TAG = AdscendMedia.class.getSimpleName();
    private static final Pattern pattern = Pattern.compile(" (for )?Android( App)?$");
    private List<Offer> offers;

    public class Offer extends AbstractOffer {
        public static final int CATEGORY_APP = 18;
        public static final int CATEGORY_FREE = 17;
        public static final int CATEGORY_VIDEO = 19;
        @SerializedName("category_id")
        List<Integer> categoryId;
        @SerializedName("click_url")
        String clickUrl;
        @SerializedName("conversion_rate")
        String conversionRate;
        @SerializedName("creative_filename")
        String creativeFilename;
        @SerializedName("creative_id")
        int creativeId;
        @SerializedName("credit_delay")
        String creditDelay;
        @SerializedName("currency_count")
        int currencyCount;
        @SerializedName("description")
        String description;
        @SerializedName("epc")
        double epc;
        @SerializedName("featured_global")
        int featuredGlobal;
        @SerializedName("featured_profile")
        int featuredProfile;
        @SerializedName("image_url")
        String imageUrl;
        @SerializedName("matches_target_system_detected")
        boolean matchesTargetSystemDetected;
        @SerializedName("name")
        String name;
        @SerializedName("offer_id")
        int offerId;
        @SerializedName("payout")
        double payout;
        @SerializedName("requirements")
        String requirements;
        @SerializedName("target_system")
        int targetSystem;

        public String getId() {
            return String.valueOf(this.offerId);
        }

        public String getName() {
            if (this.name.endsWith(" Android") || this.name.endsWith(" Android App")) {
                this.name = AdscendMedia.pattern.matcher(this.name).replaceAll(BuildConfig.VERSION_NAME).trim();
            }
            return this.name;
        }

        public String getActionMessage() {
            return this.description;
        }

        public String getDesc() {
            return this.description;
        }

        public boolean isFree() {
            return this.categoryId.contains(Integer.valueOf(CATEGORY_FREE));
        }

        public boolean isInstall() {
            return this.categoryId.contains(Integer.valueOf(CATEGORY_APP));
        }

        public int getNanas() {
            return this.currencyCount;
        }

        public String getIconUrl() {
            return "http:" + this.imageUrl;
        }

        public String getActionUrl() {
            return this.clickUrl;
        }

        public String getNetwork() {
            return AdscendMedia.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }
}
