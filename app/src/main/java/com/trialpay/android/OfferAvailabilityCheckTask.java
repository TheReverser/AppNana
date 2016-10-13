package com.trialpay.android;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.facebook.BuildConfig;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.JSONObject;

public class OfferAvailabilityCheckTask implements Runnable {
    private static final int ERROR_WAIT_DEFAULT_VALUE = 10;
    private static final int ERROR_WAIT_MAX = 604800;
    private static final double ERROR_WAIT_MULTIPLIER = 2.0d;
    public static final String NO_TOUCHPOINT = "NoTouchpointAvailable";
    private static final String TAG = "Trialpay.OfferAvailabilityCheckTask";
    private int error_wait = ERROR_WAIT_DEFAULT_VALUE;
    private boolean terminate = false;
    private String touchpointName;
    private BaseTrialpayManager trialpayManager;
    private URL url = null;

    public OfferAvailabilityCheckTask(BaseTrialpayManager tpm, String touchpointName) {
        this.trialpayManager = tpm;
        this.touchpointName = touchpointName;
        try {
            this.url = new URL(UrlManager.getInstance().getInterstitialApiUrl(this.trialpayManager.getContext(), touchpointName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.trialpayManager.registerTouchpointUrl(NO_TOUCHPOINT, this.touchpointName);
        if (this.url != null) {
            do {
                try {
                    Thread.sleep((long) (queryApi() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!this.terminate);
        }
    }

    public void terminate() {
        this.terminate = true;
    }

    public void reenable() {
        this.terminate = false;
    }

    private int queryApi() {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) this.trialpayManager.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                String integrationType;
                Log.d(TAG, "AVAILABILITY URL " + this.url);
                HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                Scanner s = new Scanner(is);
                s.useDelimiter("\\A");
                String json_response = s.hasNext() ? s.next() : BuildConfig.VERSION_NAME;
                s.close();
                is.close();
                conn.disconnect();
                Log.d(TAG, "AVAILABILITY RESPONSE " + json_response);
                JSONObject obj = new JSONObject(json_response);
                if (obj.has("integration_type")) {
                    integrationType = obj.getString("integration_type");
                } else {
                    integrationType = BuildConfig.VERSION_NAME;
                }
                this.trialpayManager.setIntegrationTypeForTouchpoint(this.touchpointName, integrationType);
                if (integrationType.equals("interstitial")) {
                    String contentType;
                    String interstitialURL = BuildConfig.VERSION_NAME;
                    if (obj.has("type")) {
                        contentType = obj.getString("type");
                    } else {
                        contentType = BuildConfig.VERSION_NAME;
                    }
                    if (contentType.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB)) {
                        interstitialURL = obj.getString(NativeProtocol.WEB_DIALOG_URL);
                    } else if (contentType.equals("video_trailer")) {
                        Video.initializeVideoWithParams(obj);
                        interstitialURL = Video.videoPrefix + obj.getString("dl_url");
                    } else {
                        Log.d(TAG, "Received unexpected contentType " + contentType + " for interstitial touchpoint. Touchpoint will be unavailable");
                    }
                    String gaid = Utils.getGaid();
                    if (!(contentType.equals("video_trailer") || interstitialURL.equals(BuildConfig.VERSION_NAME) || gaid.equals(BuildConfig.VERSION_NAME))) {
                        StringBuilder append = new StringBuilder().append(interstitialURL + (interstitialURL.contains("?") ? "&" : "?"));
                        String str = "gaid=%s&gaid_en=%d";
                        Object[] objArr = new Object[2];
                        objArr[0] = gaid;
                        objArr[1] = Integer.valueOf(Utils.isGaidTrackingEnabled() ? 1 : 0);
                        interstitialURL = append.append(String.format(str, objArr)).toString();
                    }
                    this.trialpayManager.registerTouchpointUrl(interstitialURL, this.touchpointName);
                } else {
                    if (obj.has("video_trailers")) {
                        Video.initializeVideosWithParams(obj.getJSONArray("video_trailers"));
                    } else {
                        Log.d(TAG, "video_trailers was not set on response to non-interstitial touchpoint " + this.touchpointName + ". This should never happen.");
                    }
                    this.trialpayManager.registerTouchpointUrl(null, this.touchpointName);
                }
                this.error_wait = ERROR_WAIT_DEFAULT_VALUE;
                return obj.getInt("validity_time");
            }
        } catch (FileNotFoundException e) {
            Log.w(TAG, "OfferAvailabilityCheckTask's HTTP request was bad. Proceed as unavailable");
        } catch (UnknownHostException e2) {
            Log.w(TAG, "OfferAvailabilityCheckTask's failed: Server is unavailable.");
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        int seconds_valid = this.error_wait;
        this.error_wait = (int) (((double) this.error_wait) * ERROR_WAIT_MULTIPLIER);
        if (this.error_wait > ERROR_WAIT_MAX) {
            this.error_wait = ERROR_WAIT_MAX;
        }
        this.trialpayManager.registerTouchpointUrl(NO_TOUCHPOINT, this.touchpointName);
        return seconds_valid;
    }
}
