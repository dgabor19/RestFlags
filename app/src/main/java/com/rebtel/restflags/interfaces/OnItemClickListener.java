package com.rebtel.restflags.interfaces;

import android.view.View;

import com.rebtel.restflags.adapters.FlagAdapter;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public interface OnItemClickListener {
    void onItemClick(View view, FlagAdapter.ViewHolder holder, int position);
}
