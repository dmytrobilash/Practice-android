package com.hfad.techtask1.network;

import com.hfad.techtask1.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {

    @GET("v3/articles")
    Call<List<Data>> getData();


}
