package com.appnana.android.offerwall.model;

import com.appnana.android.utils.Locale;

public abstract class AbstractOffer {
    protected final String TEXT_INSTALL_AND_OPEN = "Install and Open to earn credits.";
    protected final String TEXT_INSTALL_AND_REGISTER = "Install and Register to earn credits.";
    private Float cr;
    private Priority priority;

    public enum Priority {
        VERY_LOW,
        LOW,
        NORMAL,
        MEDIUM,
        HIGH
    }

    public abstract String getActionMessage();

    public abstract String getActionUrl();

    public abstract String getDesc();

    public abstract String getIconUrl();

    public abstract String getId();

    public abstract String getName();

    public abstract int getNanas();

    public abstract String getNetwork();

    public abstract boolean isFree();

    public abstract boolean isInstall();

    public int calcNanas(int exchangeRate) {
        return getNanas();
    }

    public String getFormattedNanas() {
        return Locale.formatNumber(getNanas());
    }

    public String getUniqueId() {
        return getNetwork().toLowerCase() + ":" + getId();
    }

    public boolean isValid() {
        return (isEmpty(getName()) || isEmpty(getActionMessage()) || isEmpty(getDesc()) || isEmpty(getIconUrl()) || isEmpty(getActionUrl())) ? false : true;
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private boolean needAdditionAction() {
        return getActionMessage() != null && getActionMessage().toLowerCase().matches(".*(level|complete|tutorial|sign up|register).*");
    }

    public Priority getPriority() {
        if (this.priority == null) {
            if (!isFree()) {
                this.priority = Priority.LOW;
            } else if (getNanas() >= 30000) {
                this.priority = Priority.LOW;
            } else if (needAdditionAction()) {
                this.priority = Priority.LOW;
            } else if (isInstall()) {
                this.priority = Priority.MEDIUM;
            } else {
                this.priority = Priority.NORMAL;
            }
        }
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Float getCR() {
        return this.cr;
    }

    public void setCR(Float cr) {
        this.cr = cr;
    }
}
