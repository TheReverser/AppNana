package com.appnana.android.offerwall.model;

import java.util.List;

public class Woobi implements IOfferNetwork {
    public static final int APPLICATION_ID = 15120;
    private static final String TAG = Woobi.class.getSimpleName();
    private int appId;
    private String clientId;
    private String country;
    private String creditsPlural;
    private String creditsSingular;
    private boolean isForAccumulation;
    private List<Offer> offers;
    private int offersCount;
    private long offersUTC;
    private String payoutCurrency;

    public class Offer extends AbstractOffer {
        private int adId;
        private String artworkSqr;
        private String brandName;
        private String clickURL;
        private double credits;
        private String description;
        private String language;
        private long offerId;
        private double payout;
        private String priceTerm;
        private String subtitle;
        private String thumbnail;
        private String title;
        private int videoHeight;
        private int videoLength;
        private int videoWidth;

        public String getId() {
            return String.valueOf(this.adId);
        }

        public String getName() {
            return this.title;
        }

        public String getActionMessage() {
            return this.subtitle;
        }

        public String getDesc() {
            return this.subtitle;
        }

        public boolean isFree() {
            return this.priceTerm != null && this.priceTerm.equals("Free");
        }

        public boolean isInstall() {
            return this.videoLength == 0;
        }

        public int getNanas() {
            return (int) this.credits;
        }

        public String getIconUrl() {
            return "http:" + (this.thumbnail == null ? this.artworkSqr : this.thumbnail);
        }

        public String getActionUrl() {
            return "http:" + this.clickURL;
        }

        public String getNetwork() {
            return Woobi.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }
}
