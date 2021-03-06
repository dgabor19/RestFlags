package com.rebtel.restflags.retrofit;

import android.content.Context;
import android.util.Log;

import com.rebtel.restflags.interfaces.ResponseCallback;
import com.rebtel.restflags.utils.Constants;
import com.rebtel.restflags.utils.RequestType;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */

/**
 * Helper class to handle responses
 */
public class ResponseHandler {

    public static final String TAG = ResponseHandler.class.getSimpleName();

    /**
     * Handles any responses
     * @param response
     * @param context
     * @param fragmentTag
     * @param requestType
     * @param <T>
     */
    public static <T> void handleResponse(final Response<T> response, final Context context,
                                          final String fragmentTag, RequestType requestType) {
        //isSuccess() returns true if status code is in the range [200..300].
        if (response != null && response.isSuccessful()) {
            ResponseHandler.handleSuccessResponse(response, context, fragmentTag, requestType);
        } else {
            ResponseHandler.handleErrorResponse(response, context, fragmentTag, requestType);
        }
    }

    /**
     * Handles the success responses only
     * @param response
     * @param context
     * @param fragmentTag
     * @param requestType
     * @param <T>
     */
    public static <T> void handleSuccessResponse(final Response<T> response, final Context context,
                                                 final String fragmentTag, RequestType requestType) {
        Log.d(TAG, "handleSuccessResponse.");


        if (context != null && context instanceof ResponseCallback) {
            ResponseCallback responseCallback = (ResponseCallback) context;
            Log.d(TAG, response.getClass().getSimpleName() + " successful..");
            responseCallback.onSuccess(response, fragmentTag, requestType);
        }
    }

    /**
     * Handles the error responses only, response was HTTP/200 but it failed
     * @param response
     * @param context
     * @param fragmentTag
     * @param requestType
     * @param <T>
     */
    public static <T> void handleErrorResponse(final Response<T> response, final Context context,
                                               final String fragmentTag, RequestType requestType) {

        ResponseCallback responseCallback = null;
        if (context != null) {
            responseCallback = (ResponseCallback) context;
        }

        if (response.body() == null && context != null) {
            Log.d(TAG, "Request failed. Something fishy.");
//            GMBaseActivity.showToast(context, Constants.TOAST_UNKNOWN_TRY_AGAIN, Toast.LENGTH_SHORT, true);
        }

        if (responseCallback != null) {
            try {
                responseCallback.onError(response.code(), response.errorBody().string(), fragmentTag, requestType);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Handles the error response when the app didn't receive HTTP/200
     * @param context
     * @param throwable
     * @param fragmentTag
     * @param requestType
     */
    public static void handleFailure(Context context, final Throwable throwable,
                                     final String fragmentTag, RequestType requestType) {
        Log.d(TAG, "response failure for request type : " + requestType + ". Message : " + throwable.getMessage());
        Log.d(TAG, "response failure. Cause : " + throwable.getCause());

        throwable.printStackTrace();

        ResponseCallback responseCallback = null;
        if (context != null) {
            responseCallback = (ResponseCallback) context;
        }

        if (responseCallback != null) {
            responseCallback.onError(Constants.RESPONSE_CODE_EXCEPTION, null, fragmentTag, requestType);
        }
    }
}
