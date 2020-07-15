package com.appfame.topwallpaper.fragment;

import android.os.Bundle;
import android.view.View;

import com.appfame.topwallpaper.R;


public class MainFragment extends BaseMFragment {

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initData() {

    }
    @Override
    public void initUI(View view) {

    }
}
