package com.rebtel.restflags.interfaces;

import com.rebtel.restflags.utils.RequestType;

import retrofit2.Response;

/**
 * Interface that Activity can implement so any code who has activity (context) can
 * communicate to a fragment.
 */
public interface ResponseCallback {

    /**
     * Called when any response received from PFO server. Main activity implements this to pass the
     * status to corresponding fragment based on tag.
     *
     * @param response    response for Register, Login, Facebook Login, Logout etc.
     * @param fragmentTag Fragment tag to which the response has to be passed.
     */
    void onSuccess(Response response, String fragmentTag, RequestType requestType);

    void onError(int statusCode, String errorResponse, String fragmentTag, RequestType requestType);
}