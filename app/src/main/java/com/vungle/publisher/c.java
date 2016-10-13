package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: vungle */
public class c extends b implements Parcelable {
    public static final Creator<c> CREATOR = new Creator<c>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new c(new b[0]).a(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int x0) {
            return new c[x0];
        }
    };
    static final Orientation c = Orientation.matchVideo;

    public c(b... bVarArr) {
        if (bVarArr != null) {
            for (b bVar : bVarArr) {
                if (bVar != null) {
                    this.a.putAll(bVar.a);
                    this.b.putAll(bVar.b);
                }
            }
        }
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.getBoolean("isBackButtonEnabled", false);
    }

    public boolean isImmersiveMode() {
        return this.a.getBoolean("isImmersiveMode", false);
    }

    public boolean isIncentivized() {
        return this.a.getBoolean("isIncentivized", false);
    }

    public String getIncentivizedCancelDialogBodyText() {
        String string = this.a.getString("incentivizedCancelDialogBodyText");
        if (string == null) {
            return "Closing this video early will prevent you from earning your reward. Are you sure?";
        }
        return string;
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        String string = this.a.getString("incentivizedCancelDialogNegativeButtonText");
        if (string == null) {
            return "Close video";
        }
        return string;
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        String string = this.a.getString("incentivizedCancelDialogPositiveButtonText");
        if (string == null) {
            return "Keep watching";
        }
        return string;
    }

    public String getIncentivizedCancelDialogTitle() {
        String string = this.a.getString("incentivizedCancelDialogTitle");
        if (string == null) {
            return "Close video?";
        }
        return string;
    }

    public Orientation getOrientation() {
        Orientation orientation = (Orientation) this.a.getParcelable("orientation");
        return orientation == null ? c : orientation;
    }

    public boolean isSoundEnabled() {
        return this.a.getBoolean("isSoundEnabled", true);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
        parcel.writeBundle(this.b);
    }

    protected final c a(Parcel parcel) {
        ClassLoader classLoader = c.class.getClassLoader();
        this.a = parcel.readBundle(classLoader);
        this.b = parcel.readBundle(classLoader);
        return this;
    }
}
