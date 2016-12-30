package com.example.ruslan.chucknorris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ruslan.chucknorris.rotation.FragmentCallback;
import com.example.ruslan.chucknorris.rotation.RetainFragment;

public class MainActivity extends AppCompatActivity implements FragmentCallback {

    private TextView tvJoke;
    private ProgressBar pgProgBar;
    private Button btnRefresh;
    private String joke;
    private RetainFragment fragment;
    private static final String RETAIN_FRAGMENT_TAG = RetainFragment.class.getName();
    private static final String ERROR_MESSAGE = "No Internet connection!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTheElements();
        if (savedInstanceState != null && savedInstanceState.getString("joke") != null) {
            joke = savedInstanceState.getString("joke");
            tvJoke.setText(joke);
        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = getFragment();
                fragment.startAsync();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v("ffa","onSaveInstanceState");
        outState.putString("joke", joke);
    }

    private RetainFragment getFragment() {
        if (getFragmentManager().findFragmentByTag(RETAIN_FRAGMENT_TAG) == null) {
            System.out.println("here");
            getFragmentManager().
                    beginTransaction().
                    add(new RetainFragment(), RETAIN_FRAGMENT_TAG).
                    commit();
            getFragmentManager().executePendingTransactions();
        }
        return (RetainFragment) getFragmentManager().findFragmentByTag(RETAIN_FRAGMENT_TAG);
    }

    private void setUpTheElements() {
        tvJoke = (TextView) findViewById(R.id.tv_joke);
        pgProgBar = (ProgressBar) findViewById(R.id.pb_prog_bar);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
    }

    @Override
    public void returnFromApi(String joke) {
        if (joke != null) {
            tvJoke.setText(joke);
        } else {
            tvJoke.setText(ERROR_MESSAGE);
        }
        this.joke = joke;
    }

    @Override
    public void progressBarSetVisible() {
        pgProgBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressBarSetInvisible() {
        pgProgBar.setVisibility(View.INVISIBLE);
    }
}
