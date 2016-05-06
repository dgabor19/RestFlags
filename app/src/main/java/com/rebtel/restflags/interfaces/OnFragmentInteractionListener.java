package com.rebtel.restflags.interfaces;

import com.rebtel.restflags.BaseActivity;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public interface OnFragmentInteractionListener {
    void onFragmentInteraction(String fragment, BaseActivity.InteractionType type, Object... params);
}
