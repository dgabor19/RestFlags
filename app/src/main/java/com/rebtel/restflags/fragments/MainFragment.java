package com.rebtel.restflags.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rebtel.restflags.R;
import com.rebtel.restflags.adapters.FlagAdapter;
import com.rebtel.restflags.interfaces.OnItemClickListener;
import com.rebtel.restflags.models.Country;
import com.rebtel.restflags.retrofit.ServerRequests;
import com.rebtel.restflags.utils.Constants;
import com.rebtel.restflags.utils.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private FlagAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private List<Country> mCountries = new ArrayList<>();

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerMain);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshMain);

        mSwipeRefresh.setOnRefreshListener(this);

        mAdapter = new FlagAdapter(mActivity, mCountries, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        setToolbar(mActivity, getString(R.string.app_name));

        ActionBar actionBar = mActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);
                ServerRequests.sendRequest(mActivity, Constants.BASE_COUNTRIES_URL, null, TAG, RequestType.COUNTRIES);
            }
        });
    }

    public void setCountries(List<Country> countries) {
        mSwipeRefresh.setRefreshing(false);

        mCountries = countries;

        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null) {
                    mAdapter.setCountries(mCountries);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        ServerRequests.sendRequest(mActivity, Constants.BASE_COUNTRIES_URL, null, TAG, null);
    }

    @Override
    public void onItemClick(View view, FlagAdapter.ViewHolder holder, int position) {
//        Toast.makeText(mActivity, "Click on " + position, Toast.LENGTH_SHORT).show();
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_up, 0,
                        0, R.anim.slide_out_down)
                .replace(
                        R.id.container,
                        DetailFragment.newInstance(mCountries.get(position), position),
                        DetailFragment.TAG)
                .addToBackStack(DetailFragment.TAG)
                .commit();

    }
}
