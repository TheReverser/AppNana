package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.CrunchieService;

public class CrunchieController implements IOfferController {
    private static final String TAG = CrunchieController.class.getSimpleName();
    private CrunchieService service;

    public CrunchieController(String ndid) {
        this.service = new CrunchieService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
