package com.appnana.android.offerwall.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

public class AppNanaHistory implements IOfferNetwork {
    public static final int LIMIT = 5;
    @SerializedName("offer_names")
    private List<String> offerNames;
    @SerializedName("offers")
    private List<Offer> offers;

    public static class Offer extends AbstractOffer {
        @SerializedName("app_id")
        private String appId;
        @SerializedName("create_time")
        private Date createTime;
        @SerializedName("desc")
        private String desc;
        @SerializedName("icon")
        private String icon;
        @SerializedName("id")
        private int id;
        @SerializedName("keyword")
        private String keyword;
        @SerializedName("link")
        private String link;
        @SerializedName("name")
        private String name;
        @SerializedName("nanas")
        private int nanas;
        @SerializedName("offer_type")
        private String offerType;
        @SerializedName("service")
        private String service;
        @SerializedName("service_offer_id")
        private String serviceOfferId;
        @SerializedName("status")
        private Status status;

        public enum Status {
            Pending,
            Completed,
            Expired
        }

        public String getId() {
            return this.serviceOfferId;
        }

        public String getName() {
            return this.name;
        }

        public String getActionMessage() {
            return this.desc;
        }

        public String getDesc() {
            return this.desc;
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

        public String getIconUrl() {
            return this.icon;
        }

        public String getActionUrl() {
            return this.link;
        }

        public void setActionUrl(String link) {
            this.link = link;
        }

        public String getNetwork() {
            return this.service;
        }

        public boolean isInHouseOffer() {
            return this.service.equals("appnana");
        }

        public Date getCreateTime() {
            return this.createTime;
        }

        public Status getStatus() {
            return this.status;
        }

        public boolean isSearchOffer() {
            return this.offerType.equals(com.appnana.android.offerwall.model.AppNana.Offer.TYPE_SEARCH);
        }

        public boolean needTracking() {
            return (this.link == null || this.link.isEmpty()) ? false : true;
        }

        public String getOfferType() {
            return this.offerType;
        }

        public String getAppId() {
            return this.appId;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public void update(AbstractOffer offer) {
            this.name = offer.getName();
            this.desc = offer.getActionMessage();
            this.nanas = offer.getNanas();
            this.icon = offer.getIconUrl();
            this.link = offer.getActionUrl();
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }

    public List<String> getOfferNames() {
        return this.offerNames;
    }
}
