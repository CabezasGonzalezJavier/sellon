package com.surgery.touchsurgery;

import com.surgery.touchsurgery.detail.DetailActivity;
import com.surgery.touchsurgery.procedure.ProcedureActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by javierg on 27/09/2017.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(ProcedureActivity themeActivity);
    void inject(DetailActivity detailActivity);

}
