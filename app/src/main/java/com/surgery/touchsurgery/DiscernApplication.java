package com.surgery.touchsurgery;

import android.app.Application;

/**
 * Created by javierg on 27/09/2017.
 */

public class DiscernApplication extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
