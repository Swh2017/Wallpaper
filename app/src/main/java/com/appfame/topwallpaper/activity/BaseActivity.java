package com.appfame.topwallpaper.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        loadData();
        initUI();
        initData();
        initListener();
    }

    public abstract int setLayout();
    public abstract void initUI();
    public abstract void initData();
    public void  initListener(){

    }
    public void  loadData(){

    }


    public void setLoading(){
        
    }

    public void stopLoading(){

    }

}
