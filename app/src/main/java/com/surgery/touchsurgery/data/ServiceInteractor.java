package com.surgery.touchsurgery.data;

import android.util.LruCache;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by javierg on 27/09/2017.
 */

public class ServiceInteractor {
    private LruCache<String, Example> cache;
    private Service service;

    public ServiceInteractor(Retrofit retrofit, LruCache<String, Example> cache) {
        this.cache = cache;
        this.service = retrofit.create(Service.class);
    }

    public Observable<Example> getProcedure() {
        return Observable.concat(cachedResults(), networkResults()).first();
    }

    private Observable<Example> cachedResults() {
        return Observable.just(cache.get("query"))
                .filter((Example result) ->
                        result != null
                );
    }

    private Observable<Example> networkResults() {
        return service.getProcedure()
                .doOnNext((Example result) ->
                        cache.put("query", result));
    }
}
