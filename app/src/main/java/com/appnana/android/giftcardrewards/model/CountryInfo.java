package com.appnana.android.giftcardrewards.model;

public class CountryInfo {
    private String code;
    private int iconRes;
    private int nameRes;
    private int place;
    private int shortNameRes;

    public CountryInfo(String code, int nameRes, int shortNameRes, int iconRes, int place) {
        this.code = code;
        this.nameRes = nameRes;
        this.shortNameRes = shortNameRes;
        this.iconRes = iconRes;
        this.place = place;
    }

    public String getCode() {
        return this.code;
    }

    public int getNameRes() {
        return this.nameRes;
    }

    public int getShortNameRes() {
        return this.shortNameRes;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public int getPlace() {
        return this.place;
    }
}
