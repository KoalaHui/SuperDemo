package com.android.core.control;

import android.support.v4.view.PagerAdapter;

public interface CustomInterface {
    void updateIndicatorView(int size, int resid);

    void setAdapter(PagerAdapter adapter);

    void startScorll();

    void endScorll();
}
