package com.mesba.worksheet;

import android.app.Application;

import timber.log.Timber;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class WorkSheetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        AppCenter.start(this, "ef53cd51-29d6-4a4f-ae0a-9113c057efad",
                Analytics.class, Crashes.class);
    }
}
