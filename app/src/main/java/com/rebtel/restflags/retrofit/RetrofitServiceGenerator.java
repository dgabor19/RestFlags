package com.rebtel.restflags.retrofit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rebtel.restflags.MainActivity;
import com.rebtel.restflags.R;
import com.rebtel.restflags.utils.StatusCodeResolver;
import com.rebtel.restflags.utils.Utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class RetrofitServiceGenerator {
    public static final String TAG = RetrofitServiceGenerator.class.getSimpleName();

    // No need to instantiate this class.
    private RetrofitServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass,
                                      String url,
                                      final int connectionTimeout,
                                      final int readTimeout,
                                      boolean retryOnConnectionFailure,
                                      final Context context) {

        if (Utils.isNetworkAvailable(context)) {

            //Default Conn and Read Timeout values are 10000 millis..and RetryOnConnectionFailure is true by default..
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(retryOnConnectionFailure)
                    .addInterceptor(new LoggingInterceptor(context))
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(serviceClass);
        }

        return null;
    }


    /**
     * Interceptor to replace Retrofit logging
     */
    public static class LoggingInterceptor implements Interceptor {

        private Context mContext = null;

        public LoggingInterceptor(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();

            String requestLog = String.format("%s %s\n%s",
                    request.method().toUpperCase(), request.url(), request.headers());
            //YLog.d(String.format("Sending request %s on %s%n%s",
            //        request.url(), chain.connection(), request.headers()));
            String body = new String();
            if (request.method().compareToIgnoreCase("post") == 0 || request.method().compareToIgnoreCase("put") == 0) {
                body = bodyToString(request);
            }

            Log.d(TAG, "REQUEST " + requestLog);
            if (body != null && !body.isEmpty()) {
                Log.d(TAG, "body " + body);
            }

            Response response = null;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                if (e instanceof SocketTimeoutException) {
                    Log.d(TAG, "SocketTimeoutException..");
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext, mContext.getString(R.string.message_no_network), Toast.LENGTH_LONG).show();
                        }
                    });
                } else if (e instanceof UnknownHostException) {
                    Log.d(TAG, "UnknownHostException..");
                }
            }
            long t2 = System.nanoTime();

            if (response != null) {
                String responseLog = String.format("Received response for %s in %.1fms\nStatusCodeResolver: HTTP/%s %s\n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.code(), StatusCodeResolver.getStatus(response.code()), response.headers());

                String bodyString = response.body().string();

                Log.d(TAG, "response\n" + responseLog + "\n" + bodyString);

                Response resp = response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), bodyString))
                        .build();

                response.body().close();

                return resp;
            }

            return chain.proceed(request);
        }
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);

            final String body = buffer.readUtf8();

            return body;
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
