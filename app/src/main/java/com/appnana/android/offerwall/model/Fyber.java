package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Fyber implements IOfferNetwork {
    public static final String API_KEY = "2d0207283ce019fcc844d3efdd01a895603c5b21";
    public static final String APP_ID = "6047";
    private static final String TAG = "SponsorPay";
    private int count;
    private Information information;
    private List<Offer> offers;
    private int pages;

    private class Information {
        @SerializedName("country")
        String country;
        @SerializedName("appid")
        int id;
        @SerializedName("language")
        String language;
        @SerializedName("app_name")
        String name;
        @SerializedName("support_url")
        String supportUrl;
        @SerializedName("virtual_currency")
        String virtualCurrency;

        private Information() {
        }
    }

    public class Offer extends AbstractOffer {
        @SerializedName("offer_id")
        private int id;
        @SerializedName("link")
        private String link;
        @SerializedName("payout")
        private int payout;
        @SerializedName("required_actions")
        private String requiredActions;
        @SerializedName("teaser")
        private String teaser;
        @SerializedName("thumbnail")
        private Thumbnail thumbnail;
        @SerializedName("title")
        private String title;
        @SerializedName("offer_types")
        private List<Type> types;

        private class Thumbnail {
            @SerializedName("hires")
            private String highResUrl;
            @SerializedName("lowres")
            private String lowResUrl;

            private Thumbnail() {
            }
        }

        public class Type {
            public static final int DOWNLOAD_ID = 101;
            public static final int FREE_ID = 112;
            public static final int VIDEO_ID = 113;
            @SerializedName("offer_type_id")
            private int id;
            @SerializedName("readable")
            private String name;
        }

        public String getId() {
            return String.valueOf(this.id);
        }

        public String getName() {
            return this.title;
        }

        public String getActionMessage() {
            return this.requiredActions;
        }

        public String getDesc() {
            return this.requiredActions;
        }

        public boolean isFree() {
            for (Type type : this.types) {
                if (type.id == Type.FREE_ID) {
                    return true;
                }
            }
            return false;
        }

        public boolean isInstall() {
            for (Type type : this.types) {
                if (type.id == Type.DOWNLOAD_ID) {
                    return true;
                }
            }
            return false;
        }

        public int getNanas() {
            return this.payout;
        }

        public String getIconUrl() {
            return this.thumbnail.highResUrl;
        }

        public String getActionUrl() {
            return this.link;
        }

        public String getNetwork() {
            return Fyber.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }
}
