package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.AdscendMediaService;

public class AdscendMediaController implements IOfferController {
    private static final String TAG = AdscendMediaController.class.getSimpleName();
    private AdscendMediaService service;

    public AdscendMediaController(String ndid) {
        this.service = new AdscendMediaService(ndid);
    }

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
