package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.WoobiService;

public class WoobiController implements IOfferController {
    private static final String TAG = WoobiController.class.getSimpleName();
    private WoobiService service;

    public WoobiController(String ndid) {
        this.service = new WoobiService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
