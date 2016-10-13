package com.appnana.android.giftcardrewards;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.viewpagerindicator.CirclePageIndicator;

public class HowToActivity extends SherlockFragmentActivity implements OnClickListener {
    private Button mBtnBack;
    private CirclePageIndicator mIndicator;
    private ViewPager mPager;

    class HowToFragmentAdapter extends FragmentPagerAdapter {
        protected final int[] HOWTO_STEP = new int[]{R.drawable.how_to_1, R.drawable.how_to_2, R.drawable.how_to_3, R.drawable.how_to_4};
        private int mCount = this.HOWTO_STEP.length;

        public HowToFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return HowToFragment.newInstance(this.HOWTO_STEP[position % this.HOWTO_STEP.length]);
        }

        public int getCount() {
            return this.mCount;
        }

        public void setCount(int count) {
            if (count > 0 && count <= 10) {
                this.mCount = count;
                notifyDataSetChanged();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);
        findViews();
        setListeners();
        initialize();
    }

    protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    private void findViews() {
        this.mBtnBack = (Button) findViewById(R.id.btn_back);
        this.mPager = (ViewPager) findViewById(R.id.pager);
        this.mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }

    private void setListeners() {
        this.mBtnBack.setOnClickListener(this);
    }

    private void initialize() {
        this.mPager.setAdapter(new HowToFragmentAdapter(getSupportFragmentManager()));
        this.mIndicator.setViewPager(this.mPager);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back /*2131558472*/:
                finish();
                return;
            default:
                return;
        }
    }
}
