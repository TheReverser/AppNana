package com.appnana.android.giftcardrewards.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.appnana.android.giftcardrewards.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

public class ImageWithTextView extends ImageView {
    private Picasso mPicasso;
    private boolean mSuccess;
    private String mText;
    private Target target = new Target() {
        public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
            Bitmap newBitmap = bitmap.copy(bitmap.getConfig(), true);
            Canvas canvas = new Canvas(newBitmap);
            Paint mPaint = new Paint();
            mPaint.setColor(-1);
            mPaint.setTextSize(ImageWithTextView.this.getResources().getDimension(R.dimen.instagram_text_size));
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            float textWidth = mPaint.measureText(ImageWithTextView.this.mText);
            canvas.drawText(ImageWithTextView.this.mText, ((float) (newBitmap.getWidth() / 2)) - (textWidth / 2.0f), ImageWithTextView.this.getResources().getDimension(R.dimen.instagram_y), mPaint);
            ImageWithTextView.this.setImageBitmap(newBitmap);
            ImageWithTextView.this.mSuccess = true;
        }

        public void onBitmapFailed(Drawable errorDrawable) {
        }

        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    public ImageWithTextView(Context context) {
        super(context);
    }

    public ImageWithTextView(Context context, String url, String text) {
        super(context);
        this.mText = text;
        this.mPicasso = Picasso.with(context);
        setImageResource(R.drawable.ig_loading);
        loadUrl(url);
    }

    private void loadUrl(String url) {
        this.mPicasso.load(url).into(this.target);
    }

    public boolean success() {
        return this.mSuccess;
    }

    protected void onDetachedFromWindow() {
        if (this.mPicasso != null) {
            this.mPicasso.cancelRequest(this.target);
        }
        super.onDetachedFromWindow();
    }
}
