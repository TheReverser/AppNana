package com.appnana.android.giftcardrewards.model;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class Reward {
    private int cost;
    private String fullType;
    private String icon;
    private int id;
    private String key;
    private CountryInfo locale;
    private String name;
    private int place;
    private double refundPercent;
    private String type;

    private Reward(int id, int place, int cost, double refundPercent, String name, String icon, String key, CountryInfo locale, String type, String fullType) {
        this.id = id;
        this.place = place;
        this.cost = cost;
        this.refundPercent = refundPercent;
        this.name = name;
        this.icon = icon;
        this.key = key;
        this.locale = locale;
        this.type = type;
        this.fullType = fullType;
    }

    public static Reward create(JSONObject json, String type, String fullType) throws JSONException {
        int id = json.getInt(ShareConstants.WEB_DIALOG_PARAM_ID);
        int place = json.getInt("place");
        int cost = json.getInt("cost");
        String name = json.getString(ShareConstants.WEB_DIALOG_PARAM_NAME);
        String icon = json.getString("icon");
        String key = json.getString("key");
        return new Reward(id, place, cost, json.getDouble("refund_percent"), name, icon, key, CountryInfoModel.getInstance().getCountryInfoByCode(json.getString("locale")), type, fullType);
    }

    public int getID() {
        return this.id;
    }

    public int getPlace() {
        return this.place;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRealCost() {
        return this.cost - getRefundNanas();
    }

    public double getRefundPercent() {
        return this.refundPercent;
    }

    public String getDisplayRefundPercent() {
        return ((int) (this.refundPercent * 100.0d)) + "%";
    }

    public boolean hasRefundNanas() {
        return this.refundPercent != 0.0d;
    }

    public int getRefundNanas() {
        return (int) (this.refundPercent * ((double) this.cost));
    }

    public String getDisplayRefundNanas() {
        return UserModel.getInstance().getPointsShow(getRefundNanas());
    }

    public String getName() {
        return this.name;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getKey() {
        return this.key;
    }

    public CountryInfo getLocale() {
        return this.locale;
    }

    public String getType() {
        return this.type;
    }

    public String getFullType() {
        return this.fullType;
    }

    public boolean isAvailableWorldwide() {
        return this.locale.getCode().equals("ww");
    }

    public boolean isPayPal() {
        return this.type.equals("paypal");
    }

    public boolean isBitcoin() {
        return this.type.equals("bitcoin");
    }
}
