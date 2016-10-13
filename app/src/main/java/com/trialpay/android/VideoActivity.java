package com.trialpay.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import com.facebook.BuildConfig;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.trialpay.android.UrlManager.Url;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(13)
public class VideoActivity extends Activity {
    private static final String TAG = "Trialpay.VideoActivity";
    private View closeButtonView;
    private TextView countdownView;
    private String endcapClickId = null;
    private WebView endcapWebView;
    private Boolean isVideoClickSent = Boolean.valueOf(false);
    private Boolean isVideoCompletionSent = Boolean.valueOf(false);
    private Boolean isVideoImpressionSent = Boolean.valueOf(false);
    private boolean isVideoStopped = false;
    private boolean is_primary;
    private int pausePosition;
    private String touchpointName;
    private ViewGroup videoContainerLayout;
    private String videoDownloadUrl;
    private VideoView videoHolder;
    private VideoTickerTask videoTickerTask;
    private Thread videoTickerThread;

    private class VideoTickerTask implements Runnable {
        private Activity activity;
        private View closeButtonView;
        private String countdownText;
        private TextView countdownView;
        private int duration = 0;
        private boolean isCancelled = false;
        private boolean isCompletionSent = false;
        final /* synthetic */ VideoActivity this$0;
        private boolean useCountdownFlag;
        private String videoCompletionAPIUrl;
        private VideoView videoHolder;
        private int videoInfoCompletionTime;
        private int videoInfoDelayBeforeShowingCloseButton;

        public VideoTickerTask(VideoActivity videoActivity, Activity activity, VideoView videoHolder, TextView countdownView, View closeButtonView, Map<String, String> videoTrailerInfo) {
            int i = 0;
            this.this$0 = videoActivity;
            this.activity = activity;
            this.videoHolder = videoHolder;
            this.countdownView = countdownView;
            this.closeButtonView = closeButtonView;
            this.videoInfoCompletionTime = Integer.parseInt((String) videoTrailerInfo.get("completion_time"));
            String useCountdownFlagString = (String) videoTrailerInfo.get("use_cd");
            boolean z = (useCountdownFlagString == null || useCountdownFlagString.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) ? false : true;
            this.useCountdownFlag = z;
            this.videoInfoDelayBeforeShowingCloseButton = Integer.parseInt((String) videoTrailerInfo.get("exit_delay"));
            this.videoCompletionAPIUrl = (String) videoTrailerInfo.get("cn_url");
            this.countdownText = (String) videoTrailerInfo.get("cd_text");
            String durationString = (String) videoTrailerInfo.get("duration");
            if (durationString != null) {
                i = Integer.parseInt(durationString);
            }
            this.duration = i * 1000;
        }

        public boolean isCompletionSent() {
            return this.isCompletionSent;
        }

        public void setCompletionSent(boolean isCompletionSent) {
            this.isCompletionSent = isCompletionSent;
        }

        public void cancel() {
            this.isCancelled = true;
        }

        public void run() {
            while (!this.isCancelled) {
                final Point displaySize = new Point();
                this.this$0.getWindowManager().getDefaultDisplay().getSize(displaySize);
                final float videoHolderMarginX = (float) ((displaySize.x - this.videoHolder.getWidth()) / 2);
                final float videoHolderMarginY = (float) ((displaySize.y - this.videoHolder.getHeight()) / 2);
                if (this.this$0.getVideoStopFlag()) {
                    this.this$0.onVideoCompletion();
                }
                int currentPosition = this.videoHolder.getCurrentPosition();
                if (this.duration <= 0) {
                    this.duration = this.videoHolder.getDuration();
                }
                final int timeLeft = (this.duration - currentPosition) / 1000;
                final boolean isVideoCorrectlyDownloaded = this.duration != -1;
                int delayBeforeVideoCompletionSending;
                if (this.videoInfoCompletionTime >= 0) {
                    delayBeforeVideoCompletionSending = this.videoInfoCompletionTime;
                } else {
                    try {
                        delayBeforeVideoCompletionSending = (this.duration / 1000) + this.videoInfoCompletionTime;
                    } catch (InterruptedException e) {
                        if (!isCompletionSent()) {
                            return;
                        }
                        return;
                    }
                }
                if (this.useCountdownFlag) {
                    this.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (isVideoCorrectlyDownloaded) {
                                VideoTickerTask.this.countdownView.setText(VideoTickerTask.this.countdownText.replace("%time%", String.valueOf(timeLeft)));
                                VideoTickerTask.this.countdownView.setX(videoHolderMarginX);
                                VideoTickerTask.this.countdownView.setY((((float) displaySize.y) - videoHolderMarginY) - ((float) VideoTickerTask.this.countdownView.getHeight()));
                                return;
                            }
                            VideoTickerTask.this.countdownView.setText(BuildConfig.VERSION_NAME);
                        }
                    });
                }
                int timePassed = currentPosition / 1000;
                if (isVideoCorrectlyDownloaded && timePassed >= this.videoInfoDelayBeforeShowingCloseButton) {
                    this.activity.runOnUiThread(new Runnable() {
                        public void run() {
                            VideoTickerTask.this.closeButtonView.setX((((float) displaySize.x) - videoHolderMarginX) - ((float) VideoTickerTask.this.closeButtonView.getWidth()));
                            VideoTickerTask.this.closeButtonView.setY(videoHolderMarginY);
                            VideoTickerTask.this.closeButtonView.setVisibility(0);
                        }
                    });
                }
                if (isVideoCorrectlyDownloaded && !this.isCompletionSent && timePassed >= delayBeforeVideoCompletionSending) {
                    sendCompletionPixel();
                }
                Thread.sleep(1000);
            }
        }

        public void sendCompletionPixel() {
            Log.d(VideoActivity.TAG, "fire video completion!");
            Video.fireVideoPixel(this.videoCompletionAPIUrl);
            setCompletionSent(true);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    private VideoView createVideoHolder(Uri videoUri) {
        LayoutParams videoLayoutParams = new LayoutParams(-1, -1);
        videoLayoutParams.addRule(13);
        VideoView videoHolder = new VideoView(this);
        videoHolder.setLayoutParams(videoLayoutParams);
        MediaController controller = new MediaController(this);
        videoHolder.setMediaController(controller);
        controller.setVisibility(8);
        videoHolder.setVideoURI(videoUri);
        videoHolder.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (!VideoActivity.this.videoTickerTask.isCompletionSent()) {
                    VideoActivity.this.videoTickerTask.sendCompletionPixel();
                    VideoActivity.this.isVideoCompletionSent = Boolean.valueOf(true);
                }
                VideoActivity.this.onVideoCompletion();
            }
        });
        return videoHolder;
    }

    private WebView createEndCapView(final String appPackageName) {
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                VideoActivity.this.finish();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(VideoActivity.TAG, "shouldOverrideUrlLoading " + url);
                if (url.startsWith("tp://")) {
                    if (url.equals(Strings.COMMAND_PREFIX_CLOSE_ENDCAP)) {
                        if (VideoActivity.this.is_primary) {
                            BaseTrialpayManager.getInstance().handleCloseOfferwallView(VideoActivity.this.touchpointName);
                        }
                        VideoActivity.this.finish();
                        return true;
                    } else if (url.equals(Strings.COMMAND_PREFIX_SHOW_APP_STORE)) {
                        try {
                            VideoActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                            return true;
                        } catch (ActivityNotFoundException e) {
                            VideoActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                            return true;
                        }
                    } else if (!url.startsWith(Strings.COMMAND_PREFIX_PRECREDIT)) {
                        return true;
                    } else {
                        BaseTrialpayManager trialpayManager = BaseTrialpayManager.getInstance();
                        String[] params = Url.getDecodedString(url.substring(Strings.COMMAND_PREFIX_PRECREDIT.length())).split("/");
                        if (!Utils.toSHA1(params[0] + '/' + params[1] + '/' + trialpayManager.getSid()).equals(params[2])) {
                            return true;
                        }
                        Integer amount = Integer.valueOf(0);
                        try {
                            amount = Integer.valueOf(Integer.parseInt(params[0]));
                        } catch (NumberFormatException e2) {
                        }
                        trialpayManager.addToPrecredit(trialpayManager.getVic(VideoActivity.this.touchpointName), amount.intValue());
                        return true;
                    }
                } else if (url.startsWith("http")) {
                    return false;
                } else {
                    if (url.startsWith("tpbowhttp")) {
                        url = url.substring(5);
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    intent.addFlags(268435456);
                    view.getContext().startActivity(intent);
                    return true;
                }
            }
        });
        return webView;
    }

    private TextView createCountdownView(String textColor) {
        TextView countdownView = new TextView(this);
        countdownView.setPadding(20, 20, 20, 20);
        countdownView.setTextSize(20.0f);
        countdownView.setTextColor(getColorByName(textColor));
        return countdownView;
    }

    private View createCloseButton(String textColor) {
        TextView closeButton = new TextView(this);
        if (Video.isFontAwesomeDownloaded()) {
            closeButton.setTypeface(Typeface.createFromFile(Video.getFontAwesomeFile()));
            closeButton.setText(Html.fromHtml("&#xf00d;"));
        } else {
            closeButton.setText("X");
        }
        closeButton.setTextSize(30.0f);
        closeButton.setShadowLayer(8.0f, 0.0f, 0.0f, -16777216);
        closeButton.setLayoutParams(new LayoutParams(80, 80));
        closeButton.setVisibility(8);
        closeButton.setTextColor(getColorByName(textColor));
        closeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                synchronized (this) {
                    VideoActivity.this.setVideoStopFlag(true);
                }
            }
        });
        return closeButton;
    }

    private ViewGroup createLayout(View videoHolder, View countdownView, View closeButtonView) {
        ViewGroup layout = new RelativeLayout(this);
        layout.addView(videoHolder);
        layout.addView(countdownView);
        layout.addView(closeButtonView);
        return layout;
    }

    private void generateEndcapClick(String endcapClickUrl) {
        Video.fireEndcapPixel(endcapClickUrl, new Callback() {
            public boolean handleMessage(Message message) {
                try {
                    JSONObject endcapAPIAnswer = new JSONObject(message.obj.toString());
                    if (endcapAPIAnswer.has("subid")) {
                        VideoActivity.this.endcapClickId = endcapAPIAnswer.getString("subid");
                        VideoActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                VideoActivity.this.endcapWebView.loadUrl("javascript:storeEndcapSID('" + VideoActivity.this.endcapClickId + "')");
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setRequestedOrientation(6);
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.isEmpty()) {
            Log.e(TAG, "No parameters were sent");
            finish();
            return;
        }
        this.touchpointName = extras.getString(Strings.EXTRA_KEY_TOUCHPOINT_NAME);
        this.videoDownloadUrl = extras.getString(Strings.EXTRA_KEY_VIDEO_DOWNLOAD_URL);
        this.is_primary = extras.getBoolean(Strings.EXTRA_KEY_IS_PRIMARY);
        if (this.is_primary) {
            BaseTrialpayManager.getInstance().handleOpenOfferwallView(this.touchpointName);
        }
        Map<String, String> videoTrailerInfo = Video.getVideoInfoByDownloadUrl(this.videoDownloadUrl);
        if (videoTrailerInfo == null) {
            Log.e(TAG, "No info for the video");
            finish();
            return;
        }
        String videoFileName = this.videoDownloadUrl.substring(this.videoDownloadUrl.lastIndexOf("/") + 1);
        getWindow().setFormat(-3);
        this.endcapWebView = createEndCapView((String) videoTrailerInfo.get(ServerProtocol.FALLBACK_DIALOG_PARAM_APP_ID));
        if (savedInstanceState == null || savedInstanceState.getBundle("endcapWebView") == null) {
            this.endcapWebView.loadUrl((String) videoTrailerInfo.get("ec_url"));
        }
        this.videoHolder = createVideoHolder(Uri.parse(Video.getTpRawFile(videoFileName).getAbsolutePath()));
        this.countdownView = createCountdownView((String) videoTrailerInfo.get("tc"));
        this.closeButtonView = createCloseButton((String) videoTrailerInfo.get("tc"));
        this.videoContainerLayout = createLayout(this.videoHolder, this.countdownView, this.closeButtonView);
        if (this.endcapClickId == null) {
            generateEndcapClick((String) videoTrailerInfo.get("ec_ck_url"));
        }
    }

    private int getColorByName(String textColorString) {
        Map<String, Integer> colorsMap = new HashMap();
        colorsMap.put("white", Integer.valueOf(-1));
        colorsMap.put("gray", Integer.valueOf(-7829368));
        colorsMap.put("darkGray", Integer.valueOf(-12303292));
        colorsMap.put("lightGray", Integer.valueOf(-3355444));
        colorsMap.put("red", Integer.valueOf(-65536));
        colorsMap.put("green", Integer.valueOf(-16711936));
        colorsMap.put("blue", Integer.valueOf(-16776961));
        colorsMap.put("cyan", Integer.valueOf(-16711681));
        colorsMap.put("yellow", Integer.valueOf(-256));
        colorsMap.put("magenta", Integer.valueOf(-65281));
        colorsMap.put("orange", Integer.valueOf(-23296));
        colorsMap.put("purple", Integer.valueOf(-8388480));
        colorsMap.put("brown", Integer.valueOf(-5952982));
        if (colorsMap.containsKey(textColorString)) {
            return ((Integer) colorsMap.get(textColorString)).intValue();
        }
        return -1;
    }

    private synchronized void onVideoCompletion() {
        this.videoTickerThread.interrupt();
        runOnUiThread(new Runnable() {
            public void run() {
                VideoActivity.this.setContentView(VideoActivity.this.endcapWebView);
                VideoActivity.this.videoHolder = null;
                if (VideoActivity.this.videoTickerTask.isCompletionSent()) {
                    VideoActivity.this.endcapWebView.loadUrl("javascript:firePrecreditURL()");
                }
            }
        });
        Video.setVideoUnavailable(this.videoDownloadUrl);
    }

    private void setVideoStopFlag(boolean value) {
        this.isVideoStopped = value;
    }

    private boolean getVideoStopFlag() {
        return this.isVideoStopped;
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState video:" + this.touchpointName);
        Bundle endcapWebViewBundle = new Bundle();
        this.endcapWebView.saveState(endcapWebViewBundle);
        outState.putBundle("endcapWebView", endcapWebViewBundle);
        outState.putBoolean("isVideoCompletionSent", this.isVideoCompletionSent.booleanValue());
        outState.putString("endcapClickId", this.endcapClickId);
        outState.putBoolean("isVideoImpressionSent", this.isVideoImpressionSent.booleanValue());
        outState.putBoolean("isVideoClickSent", this.isVideoClickSent.booleanValue());
        outState.putString("touchpointName", this.touchpointName);
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        this.touchpointName = savedInstanceState.getString("touchpointName");
        Log.d(TAG, "restoreInstanceState video:" + this.touchpointName);
        this.endcapWebView.restoreState(savedInstanceState.getBundle("endcapWebView"));
        this.isVideoCompletionSent = Boolean.valueOf(savedInstanceState.getBoolean("isVideoCompletionSent"));
        this.endcapClickId = savedInstanceState.getString("endcapClickId");
        this.isVideoImpressionSent = Boolean.valueOf(savedInstanceState.getBoolean("isVideoImpressionSent"));
        this.isVideoClickSent = Boolean.valueOf(savedInstanceState.getBoolean("isVideoClickSent"));
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    protected void onStart() {
        Log.d(TAG, "onStart");
        Map<String, String> videoTrailerInfo = Video.getVideoInfoByDownloadUrl(this.videoDownloadUrl);
        if (this.isVideoCompletionSent.booleanValue() || this.videoHolder == null) {
            setContentView(this.endcapWebView);
        } else {
            setContentView(this.videoContainerLayout);
            this.videoTickerTask = new VideoTickerTask(this, this, this.videoHolder, this.countdownView, this.closeButtonView, videoTrailerInfo);
            this.videoTickerThread = new Thread(this.videoTickerTask);
            this.videoTickerThread.start();
            if (!this.isVideoImpressionSent.booleanValue()) {
                String impressionURL = (String) videoTrailerInfo.get("toi_url");
                if (!(impressionURL == null || BuildConfig.VERSION_NAME.equals(impressionURL))) {
                    Video.fireVideoPixel(impressionURL);
                }
                this.isVideoImpressionSent = Boolean.valueOf(true);
            }
            if (!this.isVideoClickSent.booleanValue()) {
                Video.fireVideoPixel((String) videoTrailerInfo.get("ck_url"));
                this.isVideoClickSent = Boolean.valueOf(true);
            }
            this.videoHolder.start();
        }
        if (this.endcapClickId == null) {
            generateEndcapClick((String) videoTrailerInfo.get("ec_ck_url"));
        }
        super.onStart();
    }

    protected void onResume() {
        Log.d(TAG, "onResume");
        if (this.videoHolder != null) {
            this.videoHolder.seekTo(this.pausePosition);
            this.videoHolder.start();
        }
        super.onStart();
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        if (this.videoHolder != null) {
            this.videoHolder.pause();
            this.pausePosition = this.videoHolder.getCurrentPosition();
            this.videoTickerTask.cancel();
        }
        super.onStop();
    }

    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
