package com.b4rt.scannerapp.Service;

import com.b4rt.scannerapp.Models.LoginModel;
import com.b4rt.scannerapp.Models.Persona;
import com.b4rt.scannerapp.Models.PersonaByIdModel;
import com.b4rt.scannerapp.Models.ResponseOkModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {

    String API_BASE_URL = "http://10.30.51.197:8000/";

    @GET("pruebas/persona/")
    Call<List<Persona>> getPersonas();


    @GET("/plataforma/persona/{persona_id}")
    Call<List<Persona>> getPersonasById(@Path("persona_id") Integer persona_id);

    @FormUrlEncoded
    @POST("/api/v1/auth/login/")
    Call<LoginModel> user_login(
            @Field("username") String username,
            @Field("password") String password

    );

    @Headers("Accept: application/json")
    @GET("plataforma/persona_by_user/{user_id}/")
    Call<List<PersonaByIdModel>> send_user_id(@Path("user_id") Integer user_id);


    @FormUrlEncoded
    @POST("lauch_scan/")
    Call<ResponseOkModel> launch_scan(
            @Field("tipo") Integer tipo,
            @Field("url") String url,
            @Field("token") String token,
            @Field("user") Integer user

    );


}
