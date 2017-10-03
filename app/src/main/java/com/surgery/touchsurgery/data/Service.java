package com.surgery.touchsurgery.data;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by javierg on 27/09/2017.
 */

public interface Service {
    String URL_BASE = "http://192.168.2.28:3000/";

    @GET("db/")
    Observable<Example> getProcedure();

    @GET("procedure_details/")
    Observable<List<ProcedureDetail>> getProcedureDetails();

}
