package com.fuentes.julio.zotgoalsandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Julio on 3/21/2018.
 */

public interface IFoodAPI {
    @GET("search")
    Call<List<myFoodNDBResponse>> search(@Query("Q") String q);
    String BASE_URL = "http://zotgoals.azurewebsites.net/api/ndb/";
    @POST("reports")
    Call<myBooleanResponse> signIn(@Body user user);
    @GET("reports")
    Call<myBooleanResponse> update(@Body user user, @Header("Authorization") String authHeader);
}
