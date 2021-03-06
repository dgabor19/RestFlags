package com.rebtel.restflags.utils;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public enum RequestType {
    COUNTRIES(Constants.COUNTRIES_ENDPOINT),
    COUNTRY_DETAILS(Constants.COUNTRY_DETAILS_ENDPOINT);

    public String endpoint;

    RequestType(String endpoint) {
        this.endpoint = endpoint;
    }
}
