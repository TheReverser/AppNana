package com.appnana.android.offerwall.service;

public abstract class OfferService extends RequestService {
    public static final int OFFER_NUMBER_PER_REQUEST = 30;
    protected String ndid;

    public OfferService(String ndid) {
        this.ndid = ndid;
    }
}
