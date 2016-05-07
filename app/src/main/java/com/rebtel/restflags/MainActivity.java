package com.rebtel.restflags;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.rebtel.restflags.fragments.MainFragment;
import com.rebtel.restflags.interfaces.ResponseCallback;
import com.rebtel.restflags.models.Country;
import com.rebtel.restflags.utils.RequestType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Response;

public class MainActivity extends BaseActivity implements ResponseCallback {
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);


    }

    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {

    }

    @Override
    public void onSuccess(Response response, String fragmentTag, RequestType requestType) {
        if (requestType == RequestType.COUNTRIES) {

            List<Country> countries = new ArrayList<>();

            JsonObject object = (JsonObject) response.body();

            Set set = object.entrySet();
            Iterator keys = set.iterator();

            while(keys.hasNext()) {
                // loop to get the dynamic key
                Map.Entry entry = (Map.Entry) keys.next();

                String key = String.valueOf(entry.getKey());
                String value = String.valueOf(entry.getValue()).replace("\"", "");

                countries.add(new Country(key, value));
            }

            Log.d(TAG, "countries " + countries);

            MainFragment mainFragment = (MainFragment) mFragmentManager.findFragmentById(R.id.mainFragment);
            if (mainFragment != null) {
                mainFragment.setCountries(countries);
            }
        }
    }

    @Override
    public void onError(int statusCode, String errorResponse, String fragmentTag, RequestType requestType) {
        Log.d(TAG, "ERROR " + statusCode + " " + errorResponse);
    }
}
