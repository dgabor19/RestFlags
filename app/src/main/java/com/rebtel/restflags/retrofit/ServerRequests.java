package com.rebtel.restflags.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
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
public class ServerRequests {
    public static final String TAG = ServerRequests.class.getSimpleName();


    @SuppressWarnings("unchecked")
    public static <T> void sendRequest(final Context context, final String url, T request, final String fragmentTag) {

        RetrofitServiceInterfaces retrofitService = RetrofitServiceGenerator.createService(
                RetrofitServiceInterfaces.class, url,
                Constants.HTTP_CONNECTION_TIMEOUT_MILLIS,
                Constants.HTTP_READ_TIMEOUT_MILLIS, false, context);

        if (retrofitService != null) {
            if (request instanceof String) {

                retrofitService
                        .getCountries()
                        .enqueue(getCallback(JsonObject.class, context, fragmentTag, RequestType.COUNTRIES));

                return;
            }

        } else {
            // TODO No network case

            return;
        }
        throw new RuntimeException("Request type does not match!");
    }

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
