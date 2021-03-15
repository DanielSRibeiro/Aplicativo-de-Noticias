package com.example.news.Network;

import com.example.news.model.Noticias;
import com.example.news.model.NoticiasModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasService {

    @GET("top-headlines")
    Call<NoticiasModel> getNews(@Query("apiKey") String key, @Query("country") String pais);

    @GET("everything")
    Call<NoticiasModel> getNewsPortuguese(@Query("q") String q, @Query("apiKey") String key, @Query("language") String pt);
}