package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.VideoView;

public class al extends VideoView {
    private int a = 0;
    private int b = 0;

    public al(Context context) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int defaultSize = getDefaultSize(0, widthMeasureSpec);
        int defaultSize2 = getDefaultSize(0, heightMeasureSpec);
        if (this.a <= 0 || this.b <= 0) {
            i = defaultSize2;
            defaultSize2 = defaultSize;
        } else {
            i = Math.min(defaultSize2, Math.round((((float) this.b) / ((float) this.a)) * ((float) defaultSize)));
            defaultSize2 = Math.min(defaultSize, Math.round(((float) defaultSize2) * (((float) this.a) / ((float) this.b))));
        }
        setMeasuredDimension(defaultSize2, i);
    }

    public void a(MediaPlayer mediaPlayer) {
        this.a = mediaPlayer.getVideoWidth();
        this.b = mediaPlayer.getVideoHeight();
    }
}
