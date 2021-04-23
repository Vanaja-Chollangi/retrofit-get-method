package com.example.animals.network;

import com.example.animals.model.Animals;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("bins/jmdmr")
    Call<List<Animals>> getAnimals();

}
