package com.surgery.touchsurgery.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surgery.touchsurgery.R;
import com.surgery.touchsurgery.data.ProcedureDetail;
import com.surgery.touchsurgery.procedure.ProcedureAdapter;
import com.surgery.touchsurgery.util.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailFragment extends Fragment implements DetailContract.View{

    @BindView(R.id.detail_fragment_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.detail_fragment_imageView)
    ImageView mImageView;

    @BindView(R.id.detail_fragment_textView)
    TextView mTitle;

    @BindView(R.id.detail_fragment_retry_button)
    Button mRetry;

    @BindView(R.id.detail_fragment_progress)
    ProgressBar mProgressBar;


    DetailContract.Presenter mPresenter;
    private static final String ARG_PARAM1 = "param1";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);
        mPresenter.fetch();
        return view;
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.detail_fragment_retry_button)
    public void retry(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        mRetry.setVisibility(View.GONE);
        mPresenter.fetch();
    }

    @Override
    public void showProcedures(ProcedureDetail detail) {
        mTitle.setText(detail.getName());

        Glide.with(getActivity()).load(detail.getIcon()).into(mImageView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DetailAdapter adapter = new DetailAdapter(getActivity(), detail.getPhases());
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity());
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {

        mProgressBar.setVisibility(View.GONE);
        mRetry.setVisibility(View.VISIBLE);
        mRetry.setText(getString(R.string.retry));
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (!active) {
            mRetry.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
