package com.surgery.touchsurgery.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by javierg on 27/09/2017.
 */

public class Repository implements DataSource {

    public static final int TIMER_HTTP_OK = 5;

    @Override
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .readTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .writeTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Retrofit getService() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Service.URL_BASE)
                .client(getOkHttpClient())
                .build();
    }
}
