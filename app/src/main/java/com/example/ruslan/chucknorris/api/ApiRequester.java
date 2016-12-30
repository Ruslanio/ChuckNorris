package com.example.ruslan.chucknorris.api;

import com.example.ruslan.chucknorris.api.pojo.Joke;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruslan on 25.12.2016.
 */

public class ApiRequester {
    private final String URL = "http://api.icndb.com";

    private ApiSearch apiSearch;
    private static ApiRequester apiRequester;

    private ApiRequester(){
    }

    public static synchronized ApiRequester getInstance(){
        if(apiRequester == null){
            apiRequester = new ApiRequester();
            apiRequester.init();
        }
        return apiRequester;
    }

    private void init(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiSearch = retrofit.create(ApiSearch.class);
    }

    public Response<Joke> getRandomJoke() throws IOException {
        Call<Joke> jokeCall = apiSearch.getJoke();
        Response<Joke> jokeResponce = jokeCall.execute();
        return jokeResponce;
    }
}
