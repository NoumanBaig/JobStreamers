package com.awt.jobstreamers.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

//    public static final String BASE_URL = "http://192.168.0.104/Angadi/Jobstreamers/";
    public static final String BASE_URL = "http://192.168.0.108/Angadi/Jobstreamers/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        // setting custom timeouts
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(30, TimeUnit.SECONDS);
        client.readTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);

        if (retrofit==null) {
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }
        return retrofit;
    }
}
