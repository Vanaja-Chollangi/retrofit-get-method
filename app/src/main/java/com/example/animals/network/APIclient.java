package com.example.animals.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {
    public static String base_url = "https://api.myjson.com/";

    public static Retrofit getclient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
    public static APIinterface apIinterface(){
        return getclient().create(APIinterface.class);

    }
}
