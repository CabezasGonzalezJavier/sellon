package com.surgery.touchsurgery.procedure;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.surgery.touchsurgery.R;
import com.surgery.touchsurgery.data.Example;
import com.surgery.touchsurgery.detail.DetailActivity;
import com.surgery.touchsurgery.util.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProcedureFragment extends Fragment implements ProcedureContract.View, ProcedureAdapter.MyClickListener {

    @BindView(R.id.procedure_fragment_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.procedure_fragment_retry_button)
    Button mRetry;

    @BindView(R.id.procedure_fragment_container)
    ConstraintLayout mRelativeLayout;

    @BindView(R.id.procedure_fragment_recycler_view)
    RecyclerView mRecyclerView;

    Example mData;

    private ProcedureContract.Presenter mPresenter;


    public ProcedureFragment() {
        // Required empty public constructor
    }

    public static ProcedureFragment newInstance() {
        ProcedureFragment fragment = new ProcedureFragment();

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
        View view = inflater.inflate(R.layout.procedure_fragment, container, false);
        ButterKnife.bind(this, view);

        mPresenter.fetch();
        return view;
    }

    @Override
    public void setPresenter(ProcedureContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.procedure_fragment_retry_button)
    public void retry(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        mRetry.setVisibility(View.GONE);
        mPresenter.fetch();
    }

    @Override
    public void showProcedures(Example list) {
        mData = list;
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProcedureAdapter adapter = new ProcedureAdapter(getActivity(), mData.getProcedures());
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity());
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("object", position);
        startActivity(intent);

    }


}
