package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trifsoft.mymovies.databinding.ActivityMovieDetailsBinding;
import com.trifsoft.mymovies.viewmodels.MovieDetailsActivityViewModel;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding activityMovieDetailsBinding;
    ImageView selectedMoviePoster;

    MovieDetailsActivityViewModel movieDetailsActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        selectedMoviePoster = findViewById(R.id.selectedMoviePoster);

        activityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        movieDetailsActivityViewModel = new ViewModelProvider(this).get(MovieDetailsActivityViewModel.class);

        if(movieDetailsActivityViewModel.intentId == null){
            movieDetailsActivityViewModel.intentId = getIntent().getIntExtra("movieId", -1);
        }
        movieDetailsActivityViewModel.getMovieDataMutableLiveData(movieDetailsActivityViewModel.intentId).observe(this, movieData -> {
            activityMovieDetailsBinding.setMovieData(movieData);
            Glide.with(MovieDetailsActivity.this)
                .load("https://image.tmdb.org/t/p/w500" + movieData.getPosterPath())
                .placeholder(R.drawable.no_image)
                .into(selectedMoviePoster);
        });

    }
}