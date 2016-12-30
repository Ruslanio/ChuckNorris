package com.example.ruslan.chucknorris.rotation;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ruslan.chucknorris.api.ApiRequester;
import com.example.ruslan.chucknorris.api.pojo.Joke;
import com.example.ruslan.chucknorris.rotation.FragmentCallback;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Ruslan on 29.12.2016.
 */

public class RetrofitAsyncTask extends AsyncTask<Void,Void,String> {

    private FragmentCallback fragmentCallback;
    private static final String RETROFIT_TAG = "retrofit";


    public void setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        fragmentCallback.progressBarSetVisible();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String joke = null;
        try {
            ApiRequester requester = ApiRequester.getInstance();
            Response<Joke> jokeResponse = requester.getRandomJoke();
            if(!jokeResponse.isSuccessful()){
                Log.v(RETROFIT_TAG,"Not successful");
                Log.v(RETROFIT_TAG,jokeResponse.errorBody().toString());
                Log.v(RETROFIT_TAG,String.valueOf(jokeResponse.code()));
            }
            joke = ((Joke) jokeResponse.body()).getValue().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            Log.v(RETROFIT_TAG,"No Internet");

        }
        return joke;
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        fragmentCallback.returnFromApi(joke);
        fragmentCallback.progressBarSetInvisible();
    }
}
