package com.bassam.movieapp.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final OkHttpClient client;
    private static MovieService sInstance;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
    private static Retrofit getRetrofitInstance(){
        return new  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static MovieService getsInstance(){
        if (sInstance == null)
        {
            sInstance = getRetrofitInstance().create(MovieService.class);
        }
        return sInstance;
    }


}
