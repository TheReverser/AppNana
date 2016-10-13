package com.appnana.android.offerwall.model;

import com.facebook.BuildConfig;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Supersonic implements IOfferNetwork {
    public static final String APP_KEY = "39649ad9";
    private static final String TAG = Supersonic.class.getSimpleName();
    private Response response;

    private class Response {
        @SerializedName("generalInformation")
        private Information generalInformation;
        @SerializedName("items")
        private int items;
        @SerializedName("offers")
        private List<Offer> offers;
        @SerializedName("page")
        private int page;
        @SerializedName("total")
        private int total;

        private class Information {
            String applicationName;
            String countryCode;
            String currencyName;
            String languageCode;
            String readingOrder;
            String statusPageUrl;
            String totalEarnedRewards;
            String totalEarnedRewardsText;

            private Information() {
            }
        }

        public class Offer extends AbstractOffer {
            List<Creative> creatives;
            String disclaimer;
            int offerId;
            int rewards;
            String rewardsText;

            private class Creative {
                String creativeId;
                String description;
                Image image;
                String title;
                String url;

                private class Image {
                    int height;
                    String url;
                    int width;

                    private Image() {
                    }
                }

                private Creative() {
                }
            }

            public String getId() {
                return String.valueOf(this.offerId);
            }

            public String getName() {
                if (this.creatives.isEmpty()) {
                    return BuildConfig.VERSION_NAME;
                }
                return ((Creative) this.creatives.get(0)).title;
            }

            public String getActionMessage() {
                return this.disclaimer;
            }

            public String getDesc() {
                return getActionMessage();
            }

            public boolean isFree() {
                return true;
            }

            public boolean isInstall() {
                return true;
            }

            public int getNanas() {
                return this.rewards;
            }

            public String getIconUrl() {
                if (this.creatives.isEmpty() || this.creatives.get(0) == null || ((Creative) this.creatives.get(0)).image == null) {
                    return BuildConfig.VERSION_NAME;
                }
                return ((Creative) this.creatives.get(0)).image.url;
            }

            public String getActionUrl() {
                if (this.creatives.isEmpty()) {
                    return BuildConfig.VERSION_NAME;
                }
                return ((Creative) this.creatives.get(0)).url + "&custom_offer_id=" + getId();
            }

            public String getNetwork() {
                return Supersonic.TAG;
            }
        }

        private Response() {
        }
    }

    public List<Offer> getOffers() {
        return this.response.offers;
    }
}
