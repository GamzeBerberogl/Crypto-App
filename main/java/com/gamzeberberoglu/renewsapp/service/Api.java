package com.gamzeberberoglu.renewsapp.service;

import com.gamzeberberoglu.renewsapp.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    // https://newsapi.org/v2/everything?q=renewable&from&sortBy=publishedAt&apiKey=643d70c6292d45ab8a6abadf5dd0415f
    //

    @GET("/api/v3/ticker/price")
    Call<List<Model>> getData();

}
