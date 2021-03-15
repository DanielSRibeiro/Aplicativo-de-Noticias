package com.example.news.model;

import com.example.news.Network.NoticiasService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static NoticiasService getService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NoticiasService.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(NoticiasService.class);
    }
}
