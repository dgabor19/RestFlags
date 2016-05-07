package com.rebtel.restflags.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rebtel.restflags.MainActivity;
import com.rebtel.restflags.interfaces.OnFragmentInteractionListener;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();

    protected Toolbar mToolbar;
    protected MainActivity mActivity;
    protected OnFragmentInteractionListener mListener;
    protected boolean mIsOnTop = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity) getActivity();
        mToolbar = mActivity.getToolbar();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsOnTop = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsOnTop = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
