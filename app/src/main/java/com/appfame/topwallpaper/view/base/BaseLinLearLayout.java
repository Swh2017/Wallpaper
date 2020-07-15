package com.appfame.topwallpaper.view.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BaseLinLearLayout extends LinearLayout {

    public BaseLinLearLayout(Context context) {
        super(context);
    }

    public BaseLinLearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLinLearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
