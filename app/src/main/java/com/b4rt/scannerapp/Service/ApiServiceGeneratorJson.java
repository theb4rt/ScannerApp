package com.b4rt.scannerapp.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiServiceGeneratorJson {
    private static OkHttpClient.Builder httpClient  = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiServices.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    private ApiServiceGeneratorJson() {

    }

    public static <S> S createService(Class<S> serviceClass) {

        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);



            retrofit = builder.client(httpClient.build()).build();
        }

        return retrofit.create(serviceClass);
    }




}
