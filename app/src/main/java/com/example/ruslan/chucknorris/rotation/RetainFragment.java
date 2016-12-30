package com.example.ruslan.chucknorris.rotation;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Ruslan on 27.12.2016.
 */

public class RetainFragment extends Fragment {
    private FragmentCallback fragmentCallback;
    RetrofitAsyncTask retrofitAsyncTask;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallback = (FragmentCallback) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentCallback = (FragmentCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startAsync(){
        retrofitAsyncTask = new RetrofitAsyncTask();
        retrofitAsyncTask.setFragmentCallback(fragmentCallback);
        retrofitAsyncTask.execute();
    }
}
