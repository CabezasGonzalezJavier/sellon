package com.surgery.touchsurgery;

import android.util.LruCache;

import com.google.gson.Gson;
import com.surgery.touchsurgery.data.Example;
import com.surgery.touchsurgery.data.ProcedureDetail;
import com.surgery.touchsurgery.data.ServiceInteractor;
import com.surgery.touchsurgery.data.ServiceInteractorDetail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by javierg on 27/09/2017.
 */

public class ServiceInteractorDetailTest {
    @Mock
    private LruCache<String, List<ProcedureDetail>> mCache;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        when(mCache.get(anyString())).thenReturn(null);
    }

    @Test
    public void mockService() {
        List<ProcedureDetail> result = new ArrayList<>();


        MockWebServer mockService = new MockWebServer();
        mockService.enqueue(new MockResponse().setBody(new Gson().toJson(result)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockService.url("dfdf/"))
                .build();
        TestSubscriber<List<ProcedureDetail>> subscriber = new TestSubscriber<>();
        ServiceInteractorDetail serviceInteractor = new ServiceInteractorDetail(retrofit, mCache);
        serviceInteractor.getProcedure().subscribe(subscriber);


        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void callServiceTest() {

        List<ProcedureDetail> result = new ArrayList<>();


        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(result)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url("http://localhost:3000/procedure_details/"))
                .build();
        TestSubscriber<List<ProcedureDetail>> subscriber = new TestSubscriber<>();
        ServiceInteractorDetail serviceInteractor = new ServiceInteractorDetail(retrofit, mCache);
        serviceInteractor.getProcedure().subscribe(subscriber);


        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

}
