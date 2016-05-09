package com.rebtel.restflags.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rebtel.restflags.MainApplication;
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

/**
 * Detail view for selected country
 */
public class DetailsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = DetailsFragment.class.getSimpleName();

    private static final String PARAMS_COUNTRY = "country";
    private static final String PARAMS_POSITION = "position";

    private Country mCountry;
    private CountryDetails mCountryDetails;
    private int mPosition;

    private SwipeRefreshLayout mSwipeRefresh;
    private ImageView mImage;
    private TextView mCountryText;
    private TextView mCapitalText;
    private TextView mRegionText;
    private TextView mSubregionText;
    private TextView mPopulationText;
    private TextView mLatlngText;
    private TextView mAreaText;
    private TextView mTimezonesText;
    private TextView mBordersText;
    private TextView mCallingCodesText;
    private TextView mCurrenciesText;

    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(Country country, int position) {
        DetailsFragment fragment = new DetailsFragment();

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
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshDetail);
        mImage = (ImageView) view.findViewById(R.id.imageDetail);
        mCountryText = (TextView) view.findViewById(R.id.textCountryDetails);
        mCapitalText = (TextView) view.findViewById(R.id.textCapitalDetails);
        mRegionText = (TextView) view.findViewById(R.id.textRegionDetails);
        mSubregionText = (TextView) view.findViewById(R.id.textSubregionDetails);
        mPopulationText = (TextView) view.findViewById(R.id.textPopulaionDetails);
        mLatlngText = (TextView) view.findViewById(R.id.textLatlngDetails);
        mAreaText = (TextView) view.findViewById(R.id.textAreaDetails);
        mTimezonesText = (TextView) view.findViewById(R.id.textTimezonesDetails);
        mBordersText = (TextView) view.findViewById(R.id.textBordersDetails);
        mCallingCodesText = (TextView) view.findViewById(R.id.textCallingCodesDetails);
        mCurrenciesText = (TextView) view.findViewById(R.id.textCurrenciesDetails);

        setToolbar(mActivity, mCountry.getCountryName());
        mActivity.getAppBar().setExpanded(true, true);

        ActionBar actionBar = mActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
        }

        mSwipeRefresh.setOnRefreshListener(this);

        ImageLoader.getInstance().displayImage(
                Constants.BASE_COUNTRIES_URL + String.format(Constants.IMAGE_COUNTRY_ENDPOINT, mCountry.getCountryCode().toLowerCase()),
                mImage,
                MainApplication.getDisplayImageLoaderOptions(mActivity));

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefresh.setRefreshing(true);

                ServerRequests.sendRequest(mActivity, Constants.BASE_URL, null, TAG, RequestType.COUNTRY_DETAILS, mCountry.getCountryName());
            }
        });
    }

    /**
     * Populating views with content from response
     * @param countryDetails
     */
    public void setCountryDetails(CountryDetails countryDetails) {
        mSwipeRefresh.setRefreshing(false);

        mCountryDetails = countryDetails;

        if (mCountryDetails != null) {
            mCountryText.setText(String.format("%s (%s)", mCountryDetails.getName(), mCountryDetails.getNativeName()));
            mCapitalText.setText(mCountryDetails.getCapital());
            mRegionText.setText(mCountryDetails.getRegion());
            mSubregionText.setText(mCountryDetails.getSubregion());
            mPopulationText.setText(String.valueOf(mCountryDetails.getPopulation()));
            mLatlngText.setText(mCountryDetails.getLatlng() != null ? TextUtils.join(" ", mCountryDetails.getLatlng()) : "");
            mAreaText.setText(String.valueOf(mCountryDetails.getArea()));
            mTimezonesText.setText(mCountryDetails.getTimezones() != null ? TextUtils.join("\n", mCountryDetails.getTimezones()) : "");
            mBordersText.setText(mCountryDetails.getBorders() != null ? TextUtils.join("\n", mCountryDetails.getBorders()) : "");
            mCallingCodesText.setText(mCountryDetails.getCallingCodes() != null ? TextUtils.join("\n", mCountryDetails.getCallingCodes()) : "");
            mCurrenciesText.setText(mCountryDetails.getCurrencies() != null ? TextUtils.join("\n", mCountryDetails.getCurrencies()) : "");
        }
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
