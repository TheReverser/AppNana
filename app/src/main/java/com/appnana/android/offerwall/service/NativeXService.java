package com.appnana.android.offerwall.service;

import com.appnana.android.offerwall.model.NativeX;
import com.appnana.android.offerwall.model.NativeX.Device.Session;
import com.facebook.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.http.NameValuePair;

public class NativeXService extends OfferService {
    private Session session;

    private class NativeXRequest {
        @SerializedName("GetQualifiedOffersInputs")
        private OffersInputs offersInputs;
        @SerializedName("Session")
        private Session session;

        private class OffersInputs {
            @SerializedName("OfferIndexStart")
            private int offerIndexStart;
            @SerializedName("OfferIndexStop")
            private int offerIndexStop;
            @SerializedName("SortColumn")
            private int sortColumn;
            @SerializedName("SortDirection")
            private int sortDirection;

            private OffersInputs() {
                this.offerIndexStart = 0;
                this.offerIndexStop = 30;
                this.sortColumn = 0;
                this.sortDirection = 1;
            }
        }

        private NativeXRequest(Session session) {
            this.session = session;
            this.offersInputs = new OffersInputs();
        }
    }

    public NativeXService(Session session) {
        super(BuildConfig.VERSION_NAME);
        this.session = session;
    }

    protected String getUrl() {
        return "http://appclick.co/PublicServices/AfppApiRestV1.svc/Offer/Qualified/Get";
    }

    protected int getRequestMethod() {
        return 1;
    }

    protected List<NameValuePair> getParams() {
        return null;
    }

    protected Class getClazz() {
        return NativeX.class;
    }

    protected String getRequestBody() {
        return new Gson().toJson(new NativeXRequest(this.session), NativeXRequest.class);
    }
}
