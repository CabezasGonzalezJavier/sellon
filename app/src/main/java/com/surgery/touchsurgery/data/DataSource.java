package com.surgery.touchsurgery.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by javierg on 27/09/2017.
 */

public interface DataSource {

    Retrofit getService();

    OkHttpClient getOkHttpClient();

}
