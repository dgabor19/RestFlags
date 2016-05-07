package com.rebtel.restflags.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rebtel.restflags.MainActivity;
import com.rebtel.restflags.R;
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
    protected Handler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity) getActivity();
        mToolbar = mActivity.getToolbar();
        mHandler = new Handler();
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

    public static AlertDialog.Builder getAlertDialog(Context context, String title, String message,
                                                     boolean isCancelable, int positiveButtonTextRes,
                                                     DialogInterface.OnClickListener onPositiveButtonListener,
                                                     int negativeButtonTextRes,
                                                     DialogInterface.OnClickListener onNegativeButtonListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(message);
        }

        builder.setCancelable(isCancelable);

        if (positiveButtonTextRes != 0 && onPositiveButtonListener != null) {
            builder.setPositiveButton(positiveButtonTextRes, onPositiveButtonListener);
        }

        if (negativeButtonTextRes != 0 && onNegativeButtonListener != null) {
            builder.setNegativeButton(negativeButtonTextRes, onNegativeButtonListener);
        }
        return builder;
    }
}
