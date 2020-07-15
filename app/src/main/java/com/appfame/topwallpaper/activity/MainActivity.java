package com.appfame.topwallpaper.activity;

import com.appfame.topwallpaper.R;
import com.appfame.topwallpaper.fragment.MainFragment;

public class MainActivity extends BaseFragmentActivity {

    @Override
    public int setLayout() {
        return R.layout.main_activity;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.container, MainFragment.newInstance());
        }
    }
}