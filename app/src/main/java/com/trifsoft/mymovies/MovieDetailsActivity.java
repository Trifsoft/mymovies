package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trifsoft.mymovies.databinding.ActivityMovieDetailsBinding;
import com.trifsoft.mymovies.models.MovieData;
import com.trifsoft.mymovies.viewmodels.MovieDetailsActivityViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding activityMovieDetailsBinding;

    MovieDetailsActivityViewModel movieDetailsActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        activityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        movieDetailsActivityViewModel = new ViewModelProvider(this).get(MovieDetailsActivityViewModel.class);

        if(movieDetailsActivityViewModel.intentId == null){
            movieDetailsActivityViewModel.intentId = getIntent().getIntExtra("movieId", -1);
        }

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(movieDetailsActivityViewModel.isConnected(cm)){
            movieDetailsActivityViewModel.getMovieDataMutableLiveData(movieDetailsActivityViewModel.intentId).observe(this, this::bindMovieData);
        }
        else{
            Toast.makeText(this, "Network unavailable.", Toast.LENGTH_SHORT).show();
            movieDetailsActivityViewModel.getMovieData(movieDetailsActivityViewModel.intentId).observe(this, this::bindMovieData);
        }
    }

    private void bindMovieData(MovieData movieData) {
        if(movieData != null){
            activityMovieDetailsBinding.setMovieData(movieData);
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500" + movieData.getPosterPath())
                    .placeholder(R.drawable.no_image)
                    .centerCrop()
                    .dontAnimate()
                    .into((ImageView) findViewById(R.id.selectedMoviePoster));

        }
    }
}