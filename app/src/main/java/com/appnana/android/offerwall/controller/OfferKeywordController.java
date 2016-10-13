package com.appnana.android.offerwall.controller;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.appnana.android.offerwall.service.OfferKeywordService;

public class OfferKeywordController implements IOfferController {
    private static final String TAG = OfferKeywordController.class.getSimpleName();
    private OfferKeywordService service = new OfferKeywordService();

    public void requestOffers(Listener listener, ErrorListener errorListener) {
        this.service.fetchData(TAG, listener, errorListener);
    }
}
