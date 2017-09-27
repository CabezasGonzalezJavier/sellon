package com.surgery.touchsurgery;

import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;
import com.surgery.touchsurgery.util.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javierg on 27/09/2017.
 */
@Module
public class AppModule {

    DiscernApplication mDiscernApplication;

    public AppModule(DiscernApplication discernApplication) {
        mDiscernApplication = discernApplication;
    }

    @Singleton
    @Provides
    Repository provideRepository() {
        return new Repository();
    }

    @Singleton
    @Provides
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

}
