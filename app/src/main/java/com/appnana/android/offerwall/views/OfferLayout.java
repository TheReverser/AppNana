package com.appnana.android.offerwall.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appnana.android.offerwall.R;
import com.appnana.android.offerwall.model.AbstractOffer;
import com.appnana.android.offerwall.model.AppNana.Offer;
import com.facebook.BuildConfig;
import com.squareup.picasso.Picasso;

public class OfferLayout extends RelativeLayout {
    private ImageView mImgIcon;
    private LinearLayout mLayoutNanas;
    private AbstractOffer mOffer;
    private TextView mTxtDesc;
    private TextView mTxtName;
    private TextView mTxtNanas;

    public OfferLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void findViews() {
        this.mImgIcon = (ImageView) findViewById(R.id.img_icon);
        this.mTxtName = (TextView) findViewById(R.id.txt_name);
        this.mTxtDesc = (TextView) findViewById(R.id.txt_desc);
        this.mLayoutNanas = (LinearLayout) findViewById(R.id.layout_nanas);
        this.mTxtNanas = (TextView) this.mLayoutNanas.findViewById(R.id.txt_nanas);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        findViews();
    }

    public void setModel(AbstractOffer offer) {
        this.mOffer = offer;
        if ((offer instanceof Offer) && ((Offer) offer).isLocal()) {
            this.mImgIcon.setImageResource(((Offer) offer).getIconRes());
        } else {
            Picasso.with(getContext()).load(offer.getIconUrl()).placeholder(R.drawable.bkgd_loading_offer).into(this.mImgIcon);
        }
        this.mTxtName.setText(offer.getName());
        LayoutParams nameTextViewLayoutParams = new LayoutParams(-2, -2);
        nameTextViewLayoutParams.addRule(1, R.id.img_icon);
        if (offer.getActionMessage().equals(BuildConfig.VERSION_NAME)) {
            nameTextViewLayoutParams.addRule(15);
            nameTextViewLayoutParams.addRule(0, R.id.layout_nanas);
        }
        this.mTxtName.setLayoutParams(nameTextViewLayoutParams);
        this.mTxtDesc.setText(offer.getActionMessage());
        if (offer.getNanas() == 0) {
            this.mLayoutNanas.setVisibility(8);
            return;
        }
        this.mLayoutNanas.setVisibility(0);
        this.mTxtNanas.setText("+ " + offer.getFormattedNanas());
    }

    public void setNanasViewVisibility(int visibility) {
        this.mLayoutNanas.setVisibility(visibility);
    }

    public AbstractOffer getModel() {
        return this.mOffer;
    }
}
