package com.fuentes.julio.zotgoalsandroid;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Julio on 3/19/2018.
 */

public interface IUserAPI {
    @POST("signUp")
    Call<myBooleanResponse> signUp(@Body user user);
    String BASE_URL = "http://zotgoals.azurewebsites.net/api/users/";
    @POST("login")
    Call<myBooleanResponse> signIn(@Body user user);
    @PUT("update")
    Call<myBooleanResponse> update(@Body user user, @Header("Authorization") String authHeader);
}
