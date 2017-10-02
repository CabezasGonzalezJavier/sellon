package com.surgery.touchsurgery;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javierg on 27/09/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

}
