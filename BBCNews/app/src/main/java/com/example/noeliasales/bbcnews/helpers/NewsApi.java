package com.example.noeliasales.bbcnews.helpers;

import com.example.noeliasales.bbcnews.models.News;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NewsApi {
    @GET("news/world/rss.xml")
    Call<News> get();
}
