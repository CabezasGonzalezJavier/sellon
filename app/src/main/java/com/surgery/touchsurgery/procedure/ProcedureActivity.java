package com.surgery.touchsurgery.procedure;

import android.os.Bundle;

import com.surgery.touchsurgery.BaseActivity;
import com.surgery.touchsurgery.R;

import static com.surgery.touchsurgery.util.ActivityUtils.addFragmentToActivity;

public class ProcedureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.procedure_activity);
        initFragment();
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
