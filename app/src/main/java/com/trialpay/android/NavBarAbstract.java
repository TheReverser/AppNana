package com.trialpay.android;

import com.trialpay.android.NavBar.SimpleCommandListener;
import com.trialpay.android.NavBar.UrlCommandListener;

public abstract class NavBarAbstract implements NavBar {
    protected SimpleCommandListener backCommandListener;
    protected SimpleCommandListener closeCommandListener;
    protected UrlCommandListener offerCommandListener;
    protected UrlCommandListener offerwallCommandListener;
    protected SimpleCommandListener refreshCommandListener;
    protected SimpleCommandListener reloadCommandListener;
    protected SimpleCommandListener upCommandListener;

    public void setCloseCommandListener(SimpleCommandListener commandListener) {
        this.closeCommandListener = commandListener;
    }

    public void setUpCommandListener(SimpleCommandListener commandListener) {
        this.upCommandListener = commandListener;
    }

    public void setBackCommandListener(SimpleCommandListener commandListener) {
        this.backCommandListener = commandListener;
    }

    public void setReloadCommandListener(SimpleCommandListener commandListener) {
        this.reloadCommandListener = commandListener;
    }

    public void setRefreshCommandListener(SimpleCommandListener commandListener) {
        this.refreshCommandListener = commandListener;
    }

    public void setOfferwallCommandListener(UrlCommandListener commandListener) {
        this.offerwallCommandListener = commandListener;
    }

    public void setOfferCommandListener(UrlCommandListener commandListener) {
        this.offerCommandListener = commandListener;
    }
}
