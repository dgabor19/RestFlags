package com.rebtel.restflags;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.StrictMode;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();

    protected static DisplayImageOptions sDisplayImageLoaderOptions;

    @Override
    public void onCreate() {

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate();

        initImageLoader(this);
    }

    public static DisplayImageOptions getDisplayImageLoaderOptions(Context context) {
        if (sDisplayImageLoaderOptions == null) {
            initImageLoader(context.getApplicationContext());
        }

        return sDisplayImageLoaderOptions;
    }

    private static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.MAX_PRIORITY);
        config.threadPoolSize(10);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCacheSizePercentage(20); // % of available app memory..
        config.tasksProcessingOrder(QueueProcessingType.FIFO);
//        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());

        sDisplayImageLoaderOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
}

