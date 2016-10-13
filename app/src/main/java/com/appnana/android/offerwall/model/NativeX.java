package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NativeX implements IOfferNetwork {
    public static final int APP_ID = 11677;
    private static final String TAG = "W3i";
    @SerializedName("Offers")
    private List<Offer> offers;
    @SerializedName("TotalOfferCount")
    private int totalOfferCount;

    public static class Device {
        @SerializedName("ApiDeviceId")
        private String id;
        @SerializedName("IsAfppOfferwallEnabled")
        private boolean isAfppOfferwallEnabled;
        @SerializedName("Session")
        private Session session;

        public class Session {
            @SerializedName("SessionId")
            private String id;
            @SerializedName("SessionIdAsString")
            private String idString;
        }

        public String getId() {
            return this.id;
        }

        public Session getSession() {
            return this.session;
        }
    }

    private class Offer extends AbstractOffer {
        @SerializedName("Currencies")
        private List<Currency> currencies;
        @SerializedName("DisplayName")
        private String displayName;
        @SerializedName("ExternalId")
        private String externalId;
        @SerializedName("FullConversionActionMessage")
        private String fullConversionActionMessage;
        @SerializedName("Id")
        private int id;
        @SerializedName("IsFeatured")
        private boolean isFeatured;
        @SerializedName("PublisherPayoutAmount")
        private double publisherPayoutAmount;
        @SerializedName("PurchasePrice")
        private double purchasePrice;
        @SerializedName("SaveOfferClickUrl")
        private String saveOfferClickUrl;
        @SerializedName("ShortConversionActionMessage")
        private String shortConversionActionMessage;
        @SerializedName("SmallIconUrl")
        private String smallIconUrl;

        private class Currency {
            @SerializedName("DevicePayoutAmount")
            private int devicePayoutAmount;
            @SerializedName("DisplayName")
            private String displayName;
            @SerializedName("ExternalCurrencyId")
            private String externalCurrencyId;
            @SerializedName("Id")
            private int id;
            @SerializedName("IsDefault")
            private boolean isDefault;

            private Currency() {
            }
        }

        private Offer() {
        }

        public String getId() {
            return String.valueOf(this.id);
        }

        public String getName() {
            return this.displayName;
        }

        public String getActionMessage() {
            return this.shortConversionActionMessage;
        }

        public String getDesc() {
            return this.shortConversionActionMessage;
        }

        public boolean isFree() {
            return this.purchasePrice == 0.0d;
        }

        public boolean isInstall() {
            return true;
        }

        public int getNanas() {
            return ((Currency) this.currencies.get(0)).devicePayoutAmount;
        }

        public String getIconUrl() {
            return this.smallIconUrl;
        }

        public String getActionUrl() {
            return this.saveOfferClickUrl;
        }

        public String getNetwork() {
            return NativeX.TAG;
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }
}
