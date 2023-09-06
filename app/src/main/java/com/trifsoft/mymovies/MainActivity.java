package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.trifsoft.mymovies.adapter.MovieListAdapter;
import com.trifsoft.mymovies.models.Result;
import com.trifsoft.mymovies.viewmodels.MainActivityViewModel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;

    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected){
            mainActivityViewModel.getAllMoviesMutableLiveData().observe(this, results -> createAdapter(results));
        }
        else{
            mainActivityViewModel.getAllResults().observe(this, results -> {
                createAdapter((ArrayList<Result>) results);
            });
        }

    }

    private void createAdapter(ArrayList<Result> results) {
        movieListAdapter = new MovieListAdapter(results, MainActivity.this);

        recyclerView.setAdapter(movieListAdapter);
    }
}