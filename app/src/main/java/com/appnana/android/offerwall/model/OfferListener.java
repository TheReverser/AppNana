package com.appnana.android.offerwall.model;

import java.util.List;

public interface OfferListener {
    void onRequest();

    void onResponse(List<AbstractOffer> list);
}
