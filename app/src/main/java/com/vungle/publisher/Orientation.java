package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: vungle */
public enum Orientation implements Parcelable {
    autoRotate,
    matchVideo;
    
    public static final Creator<Orientation> CREATOR = null;

    static {
        CREATOR = new Creator<Orientation>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return Orientation.values()[parcel.readInt()];
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int x0) {
                return new Orientation[x0];
            }
        };
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
