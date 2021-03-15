package com.example.news.Network;

import com.example.news.model.NoticiasModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasService {

    String URL_BASE = "https://newsapi.org/v2/";

    @GET("everything")
    Call<NoticiasModel> getNews(@Query("q") String q, @Query("apiKey") String key);
}