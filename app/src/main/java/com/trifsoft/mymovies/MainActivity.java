package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.trifsoft.mymovies.adapter.MovieListAdapter;
import com.trifsoft.mymovies.viewmodels.MainActivityViewModel;

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

        mainActivityViewModel.getAllMoviesMutableLiveData().observe(this, results -> {
            movieListAdapter = new MovieListAdapter(results, MainActivity.this);

            recyclerView.setAdapter(movieListAdapter);

        });

    }
}