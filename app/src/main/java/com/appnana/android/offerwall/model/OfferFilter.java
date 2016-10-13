package com.appnana.android.offerwall.model;

import com.facebook.BuildConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferFilter {
    private Filter byId;
    private Filter byName;
    private Map<String, Map<String, Float>> posteriorityFilterMap;

    private class Filter {
        private List<String> deletion;
        private List<String> duplication;
        private List<String> posteriority;

        private Filter() {
        }
    }

    public String checkAndGetDuplicatedKeyword(String name) {
        if (name == null || this.byName == null || this.byName.duplication == null || this.byName.duplication.isEmpty()) {
            return null;
        }
        name = name.toLowerCase().replace(" ", BuildConfig.VERSION_NAME);
        for (String keyword : this.byName.duplication) {
            if (name.contains(keyword)) {
                return keyword;
            }
        }
        return null;
    }

    public boolean needDecreasePriority(AbstractOffer offer) {
        if (this.byName != null && isInList(offer.getName(), this.byName.posteriority)) {
            return true;
        }
        Float cr = getCRById(offer.getNetwork().toLowerCase(), offer.getId());
        if (cr == null) {
            return false;
        }
        offer.setCR(cr);
        return true;
    }

    public boolean needDelete(AbstractOffer offer) {
        if (this.byId == null || this.byId.deletion == null) {
            return false;
        }
        if (this.byId.deletion.contains(offer.getUniqueId()) || isInList(offer.getName(), this.byName.deletion)) {
            return true;
        }
        return false;
    }

    private boolean isInList(String str, List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        str = str.toLowerCase().replace(" ", BuildConfig.VERSION_NAME);
        for (String s : list) {
            if (str.contains(s)) {
                return true;
            }
        }
        return false;
    }

    private Float getCRById(String network, String id) {
        initPosteriorityFilterMap();
        if (this.posteriorityFilterMap.containsKey(network) && ((Map) this.posteriorityFilterMap.get(network)).containsKey(id)) {
            return (Float) ((Map) this.posteriorityFilterMap.get(network)).get(id);
        }
        return null;
    }

    private void initPosteriorityFilterMap() {
        if (this.posteriorityFilterMap == null) {
            this.posteriorityFilterMap = new HashMap();
            if (this.byId != null) {
                for (String str : this.byId.posteriority) {
                    String[] strArray = str.split(":");
                    String network = strArray[0];
                    String offerId = strArray[1];
                    float cr = Float.valueOf(strArray[2]).floatValue();
                    if (!this.posteriorityFilterMap.containsKey(network)) {
                        this.posteriorityFilterMap.put(network, new HashMap());
                    }
                    ((Map) this.posteriorityFilterMap.get(network)).put(offerId, Float.valueOf(cr));
                }
            }
        }
    }
}
