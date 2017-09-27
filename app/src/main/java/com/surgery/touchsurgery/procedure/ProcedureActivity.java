package com.surgery.touchsurgery.procedure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.surgery.touchsurgery.DiscernApplication;
import com.surgery.touchsurgery.R;
import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import static com.surgery.touchsurgery.util.ActivityUtils.addFragmentToActivity;

public class ProcedureActivity extends AppCompatActivity {

    @Inject
    Repository mRepository;

    @Inject
    BaseSchedulerProvider mSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.procedure_activity);
        initializeDagger();
        initFragment();
    }

    private void initializeDagger() {
        DiscernApplication app = (DiscernApplication) getApplication();
        app.getAppComponent().inject(this);
    }

    private void initFragment () {
        ProcedureFragment procedureFragment = (ProcedureFragment) getSupportFragmentManager().findFragmentById(R.id.procedure_activity_contentFrame);

        if (procedureFragment == null) {
            procedureFragment = procedureFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(),
                    procedureFragment, R.id.procedure_activity_contentFrame);
        }

        new ProcedurePresenter(mRepository, procedureFragment, mSchedulerProvider);
    }
}
