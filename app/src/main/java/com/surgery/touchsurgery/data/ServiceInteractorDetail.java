package com.surgery.touchsurgery.data;

import android.util.LruCache;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by javierg on 27/09/2017.
 */

public class ServiceInteractorDetail {

    private LruCache<String, List<ProcedureDetail>> cache;
    private Service service;

    public ServiceInteractorDetail(Retrofit retrofit, LruCache<String, List<ProcedureDetail>> cache) {
        this.cache = cache;
        this.service = retrofit.create(Service.class);
    }

    public Observable<List<ProcedureDetail>> getProcedure() {
        return Observable.concat(cachedResults(), networkResults()).first();
    }

    private Observable<List<ProcedureDetail>> cachedResults() {
        return Observable.just(cache.get("query"))
                .filter((List<ProcedureDetail> result) ->
                        result != null
                );
    }

    private Observable<List<ProcedureDetail>> networkResults() {
        return service.getProcedureDetails()
                .doOnNext((List<ProcedureDetail> result) ->
                        cache.put("query", result));
    }
}
