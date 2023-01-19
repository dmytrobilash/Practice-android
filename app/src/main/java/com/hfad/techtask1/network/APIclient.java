package com.hfad.techtask1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {
    public final static String base_url = "https://api.spaceflightnewsapi.net/";

    public static Retrofit getClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static APIinterface apIinterface (){
        return getClient().create(APIinterface.class);

    }


}
