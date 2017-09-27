package com.surgery.touchsurgery.procedure;

import android.support.annotation.NonNull;
import android.util.LruCache;

import com.surgery.touchsurgery.data.Example;
import com.surgery.touchsurgery.data.ProcedureDetail;
import com.surgery.touchsurgery.data.Repository;
import com.surgery.touchsurgery.data.ServiceInteractor;
import com.surgery.touchsurgery.util.schedulers.BaseSchedulerProvider;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by javierg on 27/09/2017.
 */

public class ProcedurePresenter implements ProcedureContract.Presenter{
    @NonNull
    private CompositeSubscription mSubscriptions;
    private Repository mRepository;

    private ProcedureContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;
    final ServiceInteractor mInteractor;

    public ProcedurePresenter(@NonNull Repository repository, @NonNull ProcedureContract.View view, @NonNull BaseSchedulerProvider provider) {
        this.mRepository = checkNotNull(repository, "repository cannot be null");
        this.mView = checkNotNull(view, "view cannot be null!");
        this.mSchedulerProvider = checkNotNull(provider, "schedulerProvider cannot be null");

        mSubscriptions = new CompositeSubscription();

        mView.setPresenter(this);
        LruCache<String, Example> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB
        mInteractor = new ServiceInteractor(mRepository.getService(), cache);
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
                .subscribe((Example listTopics) -> {
                            mView.setLoadingIndicator(false);
                            mView.showProcedures(listTopics);
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
