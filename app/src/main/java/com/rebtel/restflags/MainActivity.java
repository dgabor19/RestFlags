package com.rebtel.restflags;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.rebtel.restflags.fragments.DetailsFragment;
import com.rebtel.restflags.fragments.MainFragment;
import com.rebtel.restflags.interfaces.ResponseCallback;
import com.rebtel.restflags.models.Country;
import com.rebtel.restflags.models.CountryDetails;
import com.rebtel.restflags.models.ErrorResponse;
import com.rebtel.restflags.utils.RequestType;

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
        mAppBar = (AppBarLayout) findViewById(R.id.appBarLayout);

        mFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
                .commit();
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

            MainFragment mainFragment = (MainFragment) mFragmentManager.findFragmentByTag(MainFragment.TAG);
            if (mainFragment != null) {
                mainFragment.setCountries(countries);
            }
        } else if (requestType == RequestType.COUNTRY_DETAILS) {

            List<CountryDetails> countryDetails = (List<CountryDetails>) response.body();

            DetailsFragment detailsFragment = (DetailsFragment) mFragmentManager.findFragmentByTag(DetailsFragment.TAG);
            if (detailsFragment != null) {
                detailsFragment.setCountryDetails(countryDetails.get(0));
            }

        }
    }

    @Override
    public void onError(int statusCode, String errorResponse, String fragmentTag, RequestType requestType) {
        Log.d(TAG, "ERROR " + statusCode + " " + errorResponse);

        if (requestType == RequestType.COUNTRY_DETAILS) {
            ErrorResponse error = mGson.fromJson(errorResponse, ErrorResponse.class);

            if (error != null) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

            DetailsFragment fragment = (DetailsFragment) mFragmentManager.findFragmentByTag(DetailsFragment.TAG);
            if (fragment != null) {
                fragment.setCountryDetails(null);
            }
        }
    }
}
