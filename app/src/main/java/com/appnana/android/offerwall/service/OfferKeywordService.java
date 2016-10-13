package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.OfferFilter;
import java.util.List;
import org.apache.http.NameValuePair;

public class OfferKeywordService extends RequestService {
    protected String getUrl() {
        return "http://s.mapiz.com/appnana/android/offer_filters.json";
    }

    protected int getRequestMethod() {
        return 0;
    }

    protected List<NameValuePair> getParams() {
        return null;
    }

    protected Class getClazz() {
        return OfferFilter.class;
    }
}
