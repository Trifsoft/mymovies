package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.trifsoft.mymovies.adapter.MovieListAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppRepository appRepository = new AppRepository();
        appRepository.getAllMoviesMutableLiveData(1).observe(this, results -> {
            Toast.makeText(this, "Data fetched.", Toast.LENGTH_SHORT).show();
            movieListAdapter = new MovieListAdapter(results, MainActivity.this);

            recyclerView.setAdapter(movieListAdapter);

        });

    }
}