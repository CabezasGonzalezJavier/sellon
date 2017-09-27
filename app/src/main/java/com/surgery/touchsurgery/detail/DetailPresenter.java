package com.surgery.touchsurgery.detail;


import android.support.annotation.NonNull;
import android.util.LruCache;

import com.surgery.touchsurgery.data.ProcedureDetail;
import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.data.ServiceInteractorDetail;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by javierg on 27/09/2017.
 */

public class DetailPresenter implements DetailContract.Presenter {

    @NonNull
    private CompositeSubscription mSubscriptions;

    private Repository mRepository;

    private DetailContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;
    int mPosition;
    final ServiceInteractorDetail mInteractor;


    public DetailPresenter(@NonNull Repository repository, @NonNull DetailContract.View view, @NonNull BaseSchedulerProvider provider, int position) {
        this.mRepository = checkNotNull(repository, "repository cannot be null");
        this.mView = checkNotNull(view, "view cannot be null!");
        this.mSchedulerProvider = checkNotNull(provider, "schedulerProvider cannot be null");
        this.mPosition = position;

        mSubscriptions = new CompositeSubscription();

        mView.setPresenter(this);
        LruCache<String, List<ProcedureDetail>> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB
        mInteractor = new ServiceInteractorDetail(mRepository.getService(), cache);
        this.mView = view;
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();

    }

    @Override
    public void fetch() {
        Subscription subscription = mInteractor.getProcedure()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((List<ProcedureDetail> list) -> {
                            mView.setLoadingIndicator(false);
                            mView.showProcedures(list.get(mPosition));
                        },
                        (Throwable error) -> {
                            try {
                                mView.showError();
                            } catch (Throwable t) {
                                throw new IllegalThreadStateException();
                            }

                        },
                        () -> {
                        });

        mSubscriptions.add(subscription);
    }
}
