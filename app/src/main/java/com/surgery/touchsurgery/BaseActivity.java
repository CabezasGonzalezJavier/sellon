package com.surgery.touchsurgery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by javierg on 02/10/2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    protected Repository mRepository;

    @Inject
    protected BaseSchedulerProvider mSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
    }

    private void initializeDagger() {
        DiscernApplication app = (DiscernApplication) getApplication();
        app.getAppComponent().inject(this);
    }


}
