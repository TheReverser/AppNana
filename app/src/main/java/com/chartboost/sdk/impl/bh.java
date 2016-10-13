package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.vungle.log.Logger;
import com.vungle.sdk.VunglePub.Gender;

public abstract class bh extends bg {
    private boolean a = false;
    private Rect b = new Rect();

    protected abstract void a(MotionEvent motionEvent);

    public bh(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = false;
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ bh a;

            {
                this.a = r1;
            }

            public boolean onTouch(View v, MotionEvent event) {
                boolean a = a(v, event);
                switch (event.getActionMasked()) {
                    case Gender.MALE /*0*/:
                        a(a);
                        return a;
                    case Gender.FEMALE /*1*/:
                        if (this.a.getVisibility() == 0 && this.a.isEnabled() && a) {
                            this.a.a(event);
                        }
                        a(false);
                        break;
                    case Logger.VERBOSE_LOGGING_LEVEL /*2*/:
                        a(a);
                        break;
                    case Logger.DEBUG_LOGGING_LEVEL /*3*/:
                    case Logger.INFO_LOGGING_LEVEL /*4*/:
                        a(false);
                        break;
                }
                return true;
            }

            private boolean a(View view, MotionEvent motionEvent) {
                view.getLocalVisibleRect(this.a.b);
                Rect a = this.a.b;
                a.left += view.getPaddingLeft();
                a = this.a.b;
                a.top += view.getPaddingTop();
                a = this.a.b;
                a.right -= view.getPaddingRight();
                a = this.a.b;
                a.bottom -= view.getPaddingBottom();
                return this.a.b.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
            }

            private void a(boolean z) {
                if (z) {
                    if (!this.a.a) {
                        if (this.a.getDrawable() != null) {
                            this.a.getDrawable().setColorFilter(1996488704, Mode.SRC_ATOP);
                        } else if (this.a.getBackground() != null) {
                            this.a.getBackground().setColorFilter(1996488704, Mode.SRC_ATOP);
                        }
                        this.a.invalidate();
                        this.a.a = true;
                    }
                } else if (this.a.a) {
                    if (this.a.getDrawable() != null) {
                        this.a.getDrawable().clearColorFilter();
                    } else if (this.a.getBackground() != null) {
                        this.a.getBackground().clearColorFilter();
                    }
                    this.a.invalidate();
                    this.a.a = false;
                }
            }
        });
    }
}
