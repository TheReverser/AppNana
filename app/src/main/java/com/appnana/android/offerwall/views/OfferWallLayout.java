package com.appnana.android.offerwall.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.appnana.android.net.HttpClient;
import com.appnana.android.net.PingRequest;
import com.appnana.android.offerwall.R;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.appnana.android.offerwall.model.TrialPay;
import java.util.ArrayList;
import java.util.List;

public class OfferWallLayout extends LinearLayout {
    public static final int DISPLAY_UPPER_LIMIT = 10;
    private int fixedOfferCount = 0;
    private List<AbstractOffer> mNotDisplayedOffers;
    private final List<OfferLayout> mOfferLayoutList = new ArrayList();

    public OfferWallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {
        Context context = getContext();
        for (int i = 0; i < DISPLAY_UPPER_LIMIT; i++) {
            OfferLayout layout = (OfferLayout) inflate(context, R.layout.view_offer, null);
            this.mOfferLayoutList.add(layout);
            addView(layout);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mOfferLayoutList.isEmpty()) {
            initView();
        }
    }

    public void reset() {
        if (this.mNotDisplayedOffers != null) {
            this.mNotDisplayedOffers.clear();
        }
        if (this.mOfferLayoutList != null) {
            this.mOfferLayoutList.clear();
        }
        this.fixedOfferCount = 0;
        removeAllViews();
        initView();
    }

    private void showEmptyView() {
        removeAllViews();
        inflate(getContext(), R.layout.view_empty_offer, this);
    }

    public void setData(List<AbstractOffer> offerList, OnClickListener itemClickListener) {
        this.mNotDisplayedOffers = offerList;
        int offerCount = this.mNotDisplayedOffers.size();
        if (offerCount == 0) {
            showEmptyView();
            return;
        }
        int viewCount = this.mOfferLayoutList.size();
        for (int i = 0; i < viewCount; i++) {
            OfferLayout offerView = (OfferLayout) this.mOfferLayoutList.get(i);
            if (i < offerCount) {
                offerView.setOnClickListener(itemClickListener);
                showNextOffer(offerView);
                AbstractOffer offer = offerView.getModel();
                if ((offer instanceof Offer) && ((Offer) offer).isFixed()) {
                    this.fixedOfferCount++;
                }
            } else {
                removeView(offerView);
            }
        }
    }

    private void showNextOffer(OfferLayout view) {
        view.setModel((AbstractOffer) this.mNotDisplayedOffers.get(0));
        this.mNotDisplayedOffers.remove(0);
        if (view.getModel() instanceof TrialPay.Offer) {
            TrialPay.Offer trialPayOffer = (TrialPay.Offer) view.getModel();
            HttpClient.getInstance().addToRequestQueue(new PingRequest(trialPayOffer.getImpressionUrl()), "ping TrialPay " + trialPayOffer.getName());
        }
    }

    public void removeAndShowNextOfferView(OfferLayout view) {
        removeView(view);
        AbstractOffer offer = view.getModel();
        if ((offer instanceof Offer) && ((Offer) offer).getIdAsInt() == 1) {
            this.fixedOfferCount--;
        }
        if (this.mNotDisplayedOffers.size() > 0) {
            showNextOffer(view);
            addView(view);
        }
        if (getChildCount() == 0) {
            showEmptyView();
        }
    }

    public void addView(View child) {
        if (this.fixedOfferCount == 0) {
            super.addView(child);
        } else {
            addView(child, getChildCount() - this.fixedOfferCount);
        }
    }
}
