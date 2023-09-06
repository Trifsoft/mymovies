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
import android.widget.Toast;

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

        if(mainActivityViewModel.isConnected(cm)){
            mainActivityViewModel.getAllMoviesMutableLiveData().observe(this, this::createAdapter);
        }
        else{
            Toast.makeText(this, "Network unavailable.", Toast.LENGTH_SHORT).show();
            mainActivityViewModel.getAllResults().observe(this, results -> createAdapter((ArrayList<Result>) results));
        }

    }

    private void createAdapter(ArrayList<Result> results) {
        if(results != null){
            movieListAdapter = new MovieListAdapter(results, MainActivity.this);

            recyclerView.setAdapter(movieListAdapter);
        }
    }
}