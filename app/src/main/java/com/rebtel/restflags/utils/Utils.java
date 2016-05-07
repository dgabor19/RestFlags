package com.rebtel.restflags.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.rebtel.restflags.R;
import com.rebtel.restflags.fragments.BaseFragment;

/**
 * General utilities that includes dialog, network utilities etc.
 */
public class Utils {
    public static final String TAG = Utils.class.getSimpleName();

    /**
     * Checks if a network connection is available - wifi or cellular.
     *
     * @param context
     * @return true if wifi or cellular network is available.
     * false if no network.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Show no network dialog. On pressing ok, it takes user to setting screen to turn on network.
     *
     * @param context context
     */
    public static AlertDialog showNoNetworkDialog(final Context context) {

        return BaseFragment.getAlertDialog(context,
                context.getString(R.string.title_no_network),
                context.getString(R.string.message_no_network),
                true,
                R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                    }
                }, 0, null).show();
    }

    /**
     * Show no network dialog. On pressing ok, it takes user to setting screen to turn on network.
     *
     * @param context context
     */
    public static AlertDialog showNoNetworkDialog(final Context context,
                                                  final String title,
                                                  final String message,
                                                  final DialogInterface.OnClickListener positiveButtonListener) {
        return BaseFragment.getAlertDialog(context, title, message, true, R.string.ok, positiveButtonListener, 0, null).show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}