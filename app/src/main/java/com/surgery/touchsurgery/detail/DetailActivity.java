package com.surgery.touchsurgery.detail;

import android.os.Bundle;

import com.surgery.touchsurgery.BaseActivity;
import com.surgery.touchsurgery.R;

import static com.surgery.touchsurgery.util.ActivityUtils.addFragmentToActivity;

public class DetailActivity extends BaseActivity {

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
