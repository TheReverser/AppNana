package com.appnana.android.offerwall.controller;

import android.os.Handler;
import android.os.Message;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AbstractOffer.Priority;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.appnana.android.offerwall.model.IOfferNetwork;
import com.appnana.android.offerwall.model.OfferFilter;
import com.appnana.android.offerwall.model.OfferListener;
import com.appnana.android.utils.Device;
import com.appnana.android.utils.MapizLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferController {
    private static final int REQUEST = 1;
    private static final int RESPONSE = 2;
    private static final String TAG = OfferController.class.getSimpleName();
    private List<String> mAppNanaHistoryName = new ArrayList();
    private List<IOfferController> mControllerList = new ArrayList();
    private Map<Class, Integer> mExchangeRateMap = new HashMap();
    private List<AbstractOffer> mFixedOffers = new ArrayList();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case OfferController.REQUEST /*1*/:
                    if (Device.getInstance().getAdvertisingId() == null) {
                        OfferController.this.sortingData();
                        return;
                    } else {
                        OfferController.this.doRequest();
                        return;
                    }
                case OfferController.RESPONSE /*2*/:
                    OfferController.this.mOfferListener.onResponse(OfferController.this.mOffers);
                    return;
                default:
                    return;
            }
        }
    };
    private OfferFilter mOfferFilter;
    private OfferListener mOfferListener;
    private Map<String, AbstractOffer> mOfferMap = new HashMap();
    private List<AbstractOffer> mOffers = new ArrayList();
    private int mRequestedOfferNetworkCount = 0;
    private ErrorListener responseErrorListener = new ErrorListener() {
        public void onErrorResponse(VolleyError error) {
            OfferController.this.readyToReturn();
            if (error.networkResponse != null) {
                MapizLog.e(OfferController.TAG, new String(error.networkResponse.data));
            } else {
                error.printStackTrace();
            }
        }
    };
    private Listener responseSuccessListener = new Listener() {
        public void onResponse(Object response) {
            if (response instanceof OfferFilter) {
                OfferController.this.mOfferFilter = (OfferFilter) response;
            } else if (response instanceof IOfferNetwork) {
                IOfferNetwork network = (IOfferNetwork) response;
                if (network.getOffers() != null) {
                    OfferController.this.mOffers.addAll(network.getOffers());
                }
            }
            OfferController.this.readyToReturn();
        }
    };

    public class OfferComparator implements Comparator<AbstractOffer> {
        public int compare(AbstractOffer offer1, AbstractOffer offer2) {
            int result = getPriority(offer2).compareTo(getPriority(offer1));
            int nanas1 = getNanas(offer1);
            int nanas2 = getNanas(offer2);
            if (result != 0) {
                return result;
            }
            if (offer1.getPriority() != Priority.VERY_LOW) {
                return nanas2 - nanas1;
            }
            if (offer2.getCR() == null || offer1.getCR() == null) {
                if (offer2.getCR() != null) {
                    return OfferController.REQUEST;
                }
                if (offer1.getCR() != null) {
                    return -1;
                }
                return nanas2 - nanas1;
            } else if (offer2.getCR().floatValue() - offer1.getCR().floatValue() > 0.0f) {
                return OfferController.REQUEST;
            } else {
                if (offer2.getCR().floatValue() - offer1.getCR().floatValue() < 0.0f) {
                    return -1;
                }
                return nanas2 - nanas1;
            }
        }

        private Priority getPriority(AbstractOffer offer) {
            if (OfferController.this.mOfferFilter != null && !(offer instanceof Offer) && offer.isValid() && OfferController.this.mOfferFilter.needDecreasePriority(offer)) {
                offer.setPriority(Priority.VERY_LOW);
            }
            return offer.getPriority();
        }

        private int getNanas(AbstractOffer offer) {
            if (OfferController.this.mExchangeRateMap.containsKey(offer.getClass())) {
                offer.calcNanas(((Integer) OfferController.this.mExchangeRateMap.get(offer.getClass())).intValue());
            }
            int offerNanas = offer.getNanas();
            if ((offer instanceof Offer) && ((Offer) offer).getIdAsInt() == OfferController.REQUEST && offerNanas < 500) {
                return 500;
            }
            return offerNanas;
        }
    }

    public OfferController(String ndid, OfferListener listener, Map<Class, Integer> mapExchangeRate) {
        this.mOfferListener = listener;
        this.mExchangeRateMap = mapExchangeRate;
        initController(ndid);
    }

    public void reset() {
        if (this.mOffers != null) {
            this.mOffers.clear();
        }
        this.mRequestedOfferNetworkCount = 0;
    }

    private void initController(String ndid) {
        this.mControllerList.add(new OfferKeywordController());
        this.mControllerList.add(new FyberController(ndid));
        this.mControllerList.add(new NativeXController(ndid));
        this.mControllerList.add(new AarkiController(ndid));
        this.mControllerList.add(new WoobiController(ndid));
        this.mControllerList.add(new AdscendMediaController(ndid));
        this.mControllerList.add(new DisplayIOController(ndid));
        this.mControllerList.add(new AppThisController(ndid));
        this.mControllerList.add(new HangMyAdsController(ndid));
        this.mControllerList.add(new AppEveryController(ndid));
    }

    public void requestOffers() {
        this.mOfferListener.onRequest();
        sendRequest();
    }

    private void sendRequest() {
        new Thread(new Runnable() {
            public void run() {
                if (Device.getInstance().getAdvertisingId() == null) {
                    Device.getInstance().initAdvertisingInfo();
                }
                OfferController.this.mHandler.sendEmptyMessage(OfferController.REQUEST);
            }
        }).start();
    }

    private void doRequest() {
        for (IOfferController controller : this.mControllerList) {
            controller.requestOffers(this.responseSuccessListener, this.responseErrorListener);
        }
    }

    public void addOffers(List<? extends AbstractOffer> newOffers) {
        this.mOffers.addAll(newOffers);
    }

    public void addOffer(AbstractOffer newOffer) {
        this.mOffers.add(newOffer);
    }

    public void addFixedOffer(AbstractOffer newOffer) {
        this.mFixedOffers.add(newOffer);
    }

    public void addOffersToTop(List<? extends AbstractOffer> newOffers) {
        this.mOffers.addAll(0, newOffers);
        sortFixedOffer();
    }

    public void setAppNanaHistory(List<String> historyName) {
        if (historyName != null) {
            this.mAppNanaHistoryName = historyName;
        }
        readyToReturn();
    }

    private void readyToReturn() {
        this.mRequestedOfferNetworkCount += REQUEST;
        if (this.mRequestedOfferNetworkCount == this.mControllerList.size() + REQUEST) {
            sortingData();
        }
    }

    private void sortingData() {
        new Thread(new Runnable() {
            public void run() {
                OfferController.this.sortAndFilter();
                OfferController.this.mHandler.sendEmptyMessage(OfferController.RESPONSE);
            }
        }).start();
    }

    public List<AbstractOffer> getOffers() {
        return this.mOffers;
    }

    public Map<String, AbstractOffer> getOfferMap() {
        return this.mOfferMap;
    }

    private void sortAndFilter() {
        Collections.sort(this.mOffers, new OfferComparator());
        if (this.mOfferFilter == null) {
            this.mOfferFilter = new OfferFilter();
        }
        List<AbstractOffer> offersWillBeDeleted = new ArrayList();
        List<String> distinctOfferNames = new ArrayList();
        for (AbstractOffer offer : this.mOffers) {
            if (!(offer instanceof Offer)) {
                if (offer.isValid()) {
                    this.mOfferMap.put(offer.getUniqueId(), offer);
                    if (this.mAppNanaHistoryName.contains(offer.getName()) || this.mOfferFilter.needDelete(offer)) {
                        offersWillBeDeleted.add(offer);
                    } else {
                        String keyword = this.mOfferFilter.checkAndGetDuplicatedKeyword(offer.getName());
                        if (keyword != null) {
                            if (distinctOfferNames.contains(keyword)) {
                                offersWillBeDeleted.add(offer);
                            } else {
                                distinctOfferNames.add(keyword);
                            }
                        } else if (distinctOfferNames.contains(offer.getName())) {
                            offersWillBeDeleted.add(offer);
                        } else {
                            distinctOfferNames.add(offer.getName());
                        }
                    }
                } else {
                    offersWillBeDeleted.add(offer);
                }
            }
        }
        for (AbstractOffer offer2 : offersWillBeDeleted) {
            this.mOffers.remove(offer2);
        }
    }

    private void sortFixedOffer() {
        int offerCount = this.mOffers.size();
        int fixedOfferCount = this.mFixedOffers.size();
        if (offerCount <= 10 - fixedOfferCount) {
            this.mOffers.addAll(this.mFixedOffers);
            return;
        }
        for (int i = 0; i < fixedOfferCount; i += REQUEST) {
            this.mOffers.add((10 - fixedOfferCount) + i, this.mFixedOffers.get(i));
        }
    }
}
