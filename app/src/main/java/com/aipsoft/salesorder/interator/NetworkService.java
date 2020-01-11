package com.aipsoft.salesorder.interator;

import com.aipsoft.salesorder.BuildConfig;
import com.aipsoft.salesorder.Utils.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetworkService {

    public static APIService service;
    public static Retrofit retrofit;

    public static APIService getService(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(APIService.class);

        return service;
    }
}
