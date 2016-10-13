package com.appnana.android.giftcardrewards;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public class CountrySpinner extends Spinner {
    private Boolean isUsersAction = Boolean.valueOf(false);

    public CountrySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean performClick() {
        this.isUsersAction = Boolean.valueOf(true);
        return super.performClick();
    }

    public Boolean isUsersAction() {
        return this.isUsersAction;
    }

    public void finishClick() {
        this.isUsersAction = Boolean.valueOf(false);
    }
}
