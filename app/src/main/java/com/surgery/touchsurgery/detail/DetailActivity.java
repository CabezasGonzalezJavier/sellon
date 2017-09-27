package com.surgery.touchsurgery.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.surgery.touchsurgery.R;
import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import static com.surgery.touchsurgery.util.ActivityUtils.addFragmentToActivity;

public class DetailActivity extends AppCompatActivity {

    @Inject
    Repository mRepository;

    @Inject
    BaseSchedulerProvider mSchedulerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        int positon = getIntent().getExtras().getInt("object");
        initFragment(positon);
    }

    private void initFragment(int position) {
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_activity_contentFrame);

        if (detailFragment == null) {
            detailFragment = detailFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(),
                    detailFragment, R.id.detail_activity_contentFrame);
        }

        new DetailPresenter(mRepository, detailFragment, mSchedulerProvider, position);
    }
}
