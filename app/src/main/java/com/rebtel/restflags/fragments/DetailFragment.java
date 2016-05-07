package com.rebtel.restflags.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rebtel.restflags.R;
import com.rebtel.restflags.models.Country;
import com.rebtel.restflags.models.CountryDetails;
import com.rebtel.restflags.retrofit.ServerRequests;
import com.rebtel.restflags.utils.Constants;
import com.rebtel.restflags.utils.RequestType;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class DetailFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = DetailFragment.class.getSimpleName();

    private static final String PARAMS_COUNTRY = "country";
    private static final String PARAMS_POSITION = "position";

    private Country mCountry;
    private CountryDetails mCountryDetails;
    private int mPosition;

    private SwipeRefreshLayout mSwipeRefresh;

    public DetailFragment() {

    }

    public static DetailFragment newInstance(Country country, int position) {
        DetailFragment fragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putParcelable(PARAMS_COUNTRY, country);
        args.putInt(PARAMS_POSITION, position);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mCountry = args.getParcelable(PARAMS_COUNTRY);
            mPosition = args.getInt(PARAMS_POSITION);
        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshDetail);

        setToolbar(mActivity, mCountry.getCountryName());

        ActionBar actionBar = mActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);

                ServerRequests.sendRequest(mActivity, Constants.BASE_URL, null, TAG, RequestType.COUNTRY_DETAILS, mCountry.getCountryName());
            }
        });
    }

    public void setCountryDetails(CountryDetails countryDetails) {
        mSwipeRefresh.setRefreshing(false);

        mCountryDetails = countryDetails;

        Log.d(TAG, mCountryDetails.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mFragmentManager.popBackStack();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(true);

        ServerRequests.sendRequest(mActivity, Constants.BASE_URL, null, TAG, RequestType.COUNTRY_DETAILS, mCountry.getCountryName());
    }
}
