package com.rebtel.restflags.retrofit;

import com.google.gson.JsonObject;
import com.rebtel.restflags.models.CountryDetails;
import com.rebtel.restflags.utils.Constants;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public interface RetrofitServiceInterfaces {

    @GET(Constants.COUNTRIES_ENDPOINT)
    Call<JsonObject> getCountries();

    @GET(Constants.COUNTRY_DETAILS_ENDPOINT + "/{name}")
    Call<List<CountryDetails>> getCountryDetails(
            @Path("name") String name
    );
}
