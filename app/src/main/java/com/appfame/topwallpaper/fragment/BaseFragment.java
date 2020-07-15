package com.appfame.topwallpaper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment extends SupportFragment {


    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getContext()).inflate(setLayout(), container,false);
        loadData();
        initUI(mView);
        initData();
        return mView;
    }

    public abstract int setLayout();

    public abstract void initData();

    public abstract void initUI(View view);

    public void loadData() {

    }

    public void initListener() {

    }

}
