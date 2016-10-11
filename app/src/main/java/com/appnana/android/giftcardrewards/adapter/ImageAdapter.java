package com.appnana.android.giftcardrewards.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import com.appnana.android.giftcardrewards.R;
import com.appnana.android.giftcardrewards.view.ImageWithTextView;

public class ImageAdapter extends BaseAdapter {
    private static final String IMAGE_URL = "http://s.mapiz.com/appnana/image/promotion/share/insta_%d.jpg";
    private Context mContext;
    private int mCount;
    private ImageView[] mViews;

    public ImageAdapter(Context context, int count, String text) {
        this.mContext = context;
        this.mCount = count;
        this.mViews = new ImageView[count];
        for (int i = 0; i < count; i++) {
            this.mViews[i] = generateView(i + 1, text);
        }
    }

    private ImageView generateView(int number, String text) {
        ImageWithTextView imageView = new ImageWithTextView(this.mContext, String.format(IMAGE_URL, new Object[]{Integer.valueOf(number)}), text);
        int px = (int) this.mContext.getResources().getDimension(R.dimen.instagram_size);
        imageView.setLayoutParams(new LayoutParams(px, px));
        return imageView;
    }

    public ImageView getItem(int position) {
        return this.mViews[position];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getCount() {
        return this.mCount;
    }

    public View getView(int position, View view, ViewGroup parent) {
        return getItem(position);
    }
}
