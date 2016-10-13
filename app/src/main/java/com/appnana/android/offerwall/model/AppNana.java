package com.appnana.android.offerwall.model;

import com.appnana.android.utils.Device;
import com.appnana.android.utils.StringTemplate;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class AppNana implements IOfferNetwork {
    public static final String TAG = AppNana.class.getSimpleName();
    @SerializedName("offers")
    private List<Offer> offers;

    public static class Offer extends AbstractOffer {
        public static final int ORDER_TRIALPAY = 1;
        public static final int RFN_ID = 1;
        public static final int TRIALPAY_ID = 3;
        public static final String TYPE_INSTALL = "install";
        public static final String TYPE_SEARCH = "search";
        public static final int VIDEO_ID = 2;
        @SerializedName("app_id")
        protected String appId;
        @SerializedName("desc")
        protected String desc;
        @SerializedName("icon")
        protected String icon;
        protected int iconRes;
        @SerializedName("id")
        protected int id;
        protected boolean isFixed;
        protected boolean isLocal;
        @SerializedName("keyword")
        protected String keyword;
        @SerializedName("link")
        protected String link;
        @SerializedName("name")
        protected String name;
        @SerializedName("nanas")
        protected int nanas;
        @SerializedName("offer_type")
        protected String offerType;
        @SerializedName("service")
        protected String service;
        @SerializedName("service_offer_id")
        protected String serviceOfferId;
        @SerializedName("short_desc")
        protected String shortDesc;

        public Offer(int id, String name, int nanas, int iconRes, String shortDesc, String desc) {
            this.id = id;
            this.name = name;
            this.nanas = nanas;
            this.iconRes = iconRes;
            this.shortDesc = shortDesc;
            this.desc = desc;
            this.service = AppNana.TAG;
            this.isLocal = true;
        }

        public Offer(int id, String name, int nanas, int iconRes, String shortDesc, String desc, boolean isFixed) {
            this(id, name, nanas, iconRes, shortDesc, desc);
            this.isFixed = isFixed;
        }

        public String getId() {
            return String.valueOf(this.id);
        }

        public int getIdAsInt() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getActionMessage() {
            return this.shortDesc;
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

        public static String setTrackingParams(String link, String ndid) {
            StringTemplate stringTemplate = new StringTemplate(link);
            stringTemplate.setBlankNull();
            Map<String, String> mapInfo = Device.getInstance().getAsMap();
            mapInfo.put("ndid", ndid);
            return stringTemplate.substitute(mapInfo);
        }

        public String getNetwork() {
            return AppNana.TAG;
        }

        public String getOfferType() {
            return this.offerType;
        }

        public String getKeyword() {
            return this.keyword;
        }

        public String getAppId() {
            return this.appId;
        }

        public boolean isSearchOffer() {
            return this.offerType != null && this.offerType.equals(TYPE_SEARCH);
        }

        public boolean needTracking() {
            return (this.link == null || this.link.isEmpty()) ? false : true;
        }

        public int getIconRes() {
            return this.iconRes;
        }

        public boolean isLocal() {
            return this.isLocal;
        }

        public boolean isFixed() {
            return this.isFixed;
        }
    }

    public List<Offer> getOffers() {
        return this.offers;
    }
}
