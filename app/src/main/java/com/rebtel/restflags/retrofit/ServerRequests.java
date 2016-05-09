package com.rebtel.restflags.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.rebtel.restflags.models.CountryDetails;
import com.rebtel.restflags.utils.Constants;
import com.rebtel.restflags.utils.RequestType;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */

/**
 * Helper class to send requests
 */
public class ServerRequests {
    public static final String TAG = ServerRequests.class.getSimpleName();


    /**
     * General method for sending requests
     * @param context
     * @param url
     * @param request
     * @param fragmentTag
     * @param requestType
     * @param args
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public static <T> void sendRequest(
            final Context context, final String url, T request, final String fragmentTag,
            final RequestType requestType, final String... args) {

        RetrofitServiceInterfaces retrofitService = RetrofitServiceGenerator.createService(
                RetrofitServiceInterfaces.class, url,
                Constants.HTTP_CONNECTION_TIMEOUT_MILLIS,
                Constants.HTTP_READ_TIMEOUT_MILLIS, false, context);

        if (retrofitService != null) {
            if (requestType == RequestType.COUNTRIES) {

                retrofitService
                        .getCountries()
                        .enqueue(getCallback(JsonObject.class, context, fragmentTag, requestType));

                return;

            } else if (requestType == RequestType.COUNTRY_DETAILS) {

                retrofitService
                        .getCountryDetails(args[0])
                        .enqueue(new Callback<List<CountryDetails>>() {
                            @Override
                            public void onResponse(Call<List<CountryDetails>> call, Response<List<CountryDetails>> response) {
                                ResponseHandler.handleResponse(response, context, fragmentTag, requestType);
                            }

                            @Override
                            public void onFailure(Call<List<CountryDetails>> call, Throwable t) {
                                ResponseHandler.handleFailure(context, t, fragmentTag, requestType);
                            }
                        });

                return;
            }

        } else {
            // TODO No network case

            return;
        }
        throw new RuntimeException("Request type does not match!");
    }

    /**
     * Callback generator
     * @param response
     * @param context
     * @param fragmentTag
     * @param requestType
     * @param <T>
     * @return
     */
    private static <T> Callback<T> getCallback(final Class<T> response,
                                               final Context context,
                                               final String fragmentTag,
                                               final RequestType requestType) {
        Log.i(TAG, "Request " + requestType.endpoint);
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                ResponseHandler.handleResponse(response, context, fragmentTag, requestType);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                ResponseHandler.handleFailure(context, t, fragmentTag, requestType);
            }
        };
    }
}
