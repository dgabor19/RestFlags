package com.rebtel.restflags;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rebtel.restflags.interfaces.OnFragmentInteractionListener;

import java.util.Map;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    public static final String TAG = BaseActivity.class.getSimpleName();

    public static int sHeight;
    public static int sWidth;
    protected Handler mHandler = new Handler();
    protected FragmentManager mFragmentManager;
    protected Toolbar mToolbar;
    protected Gson mGson;

    public enum InteractionType {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        sHeight = displaymetrics.heightPixels;
        sWidth = displaymetrics.widthPixels;

        mGson = new Gson();

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    /**
     * Use this method to set the toolbar
     * @param title
     * @param logoResId
     */
    public void setToolbar(String title, int logoResId) {
        if (logoResId != 0) {
            mToolbar.setLogo(logoResId);
        }
        mToolbar.setTitle(title);


        setSupportActionBar(mToolbar);
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
