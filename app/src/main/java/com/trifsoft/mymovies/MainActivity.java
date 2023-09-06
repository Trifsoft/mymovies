package com.trifsoft.mymovies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.trifsoft.mymovies.adapter.MovieListAdapter;
import com.trifsoft.mymovies.models.Result;
import com.trifsoft.mymovies.viewmodels.MainActivityViewModel;

import android.net.ConnectivityManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fetchData();

    }

    private void fetchData() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);

        SearchView searchView = (SearchView) menu.getItem(0).getActionView();
        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mainActivityViewModel
                        .getResultsFromSearch(s)
                        .observe(MainActivity.this, results -> {
                            createAdapter((ArrayList<Result>) results);
                        });
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.refresh){
            fetchData();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }
}