package com.example.ruslan.chucknorris.api;

import com.example.ruslan.chucknorris.api.pojo.Joke;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ruslan on 25.12.2016.
 */

public interface ApiSearch {
    @GET("/jokes/random")
    Call<Joke> getJoke();
}
