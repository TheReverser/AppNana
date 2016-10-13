package com.appnana.android.giftcardrewards;

import android.content.Context;
import com.appnana.android.giftcardrewards.GameCenterBaseActivity.GameCenterJavascriptInterface;
import com.appnana.android.giftcardrewards.model.Settings;
import org.xwalk.core.JavascriptInterface;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;

public class GameCenterXWalkActivity extends GameCenterBaseActivity {

    private class GameCenterJavascriptInterfaceForXWalk extends GameCenterJavascriptInterface {
        public GameCenterJavascriptInterfaceForXWalk(Context context) {
            super(context);
        }

        @JavascriptInterface
        public String getGamingIntervals() {
            return super.getGamingIntervals();
        }

        @JavascriptInterface
        public String getNanaerInfo() {
            return super.getNanaerInfo();
        }

        @JavascriptInterface
        public String getNanaerSessionId() {
            return super.getNanaerSessionId();
        }

        @JavascriptInterface
        public void showAnonymousNanaerDialog() {
            super.showAnonymousNanaerDialog(GameCenterXWalkActivity.this.loginButtonClickListener, GameCenterXWalkActivity.this.registerButtonClickListener);
        }
    }

    private class MyXWalkResourceClient extends XWalkResourceClient {
        public MyXWalkResourceClient(XWalkView view) {
            super(view);
        }

        public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
            GameCenterXWalkActivity.this.setFullscreenMode(GameCenterXWalkActivity.this.isInGamePage(url));
            return super.shouldOverrideUrlLoading(view, url);
        }

        public void onProgressChanged(XWalkView view, int progressInPercent) {
            super.onProgressChanged(view, progressInPercent);
            GameCenterXWalkActivity.this.changeProgress(progressInPercent);
        }
    }

    protected void setup() {
        setContentView(R.layout.activity_gamecenter_xwalk);
        XWalkPreferences.setValue("remote-debugging", false);
        XWalkView xWalkView = (XWalkView) findViewById(R.id.xwalk_view);
        xWalkView.addJavascriptInterface(new GameCenterJavascriptInterfaceForXWalk(this), "android");
        xWalkView.setResourceClient(new MyXWalkResourceClient(xWalkView));
        xWalkView.load(Settings.GAME_CENTER_URL, null);
    }
}
