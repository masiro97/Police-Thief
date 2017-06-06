package com.example.user.masiro;

import android.app.Application;

/**
 * Created by User on 2017-06-06.
 */

public class NMapViewerApplication extends Application{

    private static NMapViewerApplication instance;

    public static NMapViewerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;
    }
}
