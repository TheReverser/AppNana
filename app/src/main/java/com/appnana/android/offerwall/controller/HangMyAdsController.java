package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.HangMyAdsService;

public class HangMyAdsController implements IOfferController {
    private static final String TAG = HangMyAdsController.class.getSimpleName();
    private HangMyAdsService service;

    public HangMyAdsController(String ndid) {
        this.service = new HangMyAdsService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
