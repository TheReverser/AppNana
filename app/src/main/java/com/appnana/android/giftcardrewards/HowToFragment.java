package com.appnana.android.giftcardrewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.actionbarsherlock.app.SherlockFragment;

public class HowToFragment extends SherlockFragment {
    private static final String KEY_CONTENT = "HowToFragment:Step";
    private int mStep = 1;

    public static HowToFragment newInstance(int step) {
        HowToFragment fragment = new HowToFragment();
        fragment.mStep = step;
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CONTENT)) {
            this.mStep = savedInstanceState.getInt(KEY_CONTENT);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(this.mStep);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(-1, -1));
        layout.setGravity(17);
        layout.addView(imageView);
        return layout;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTENT, this.mStep);
    }
}
