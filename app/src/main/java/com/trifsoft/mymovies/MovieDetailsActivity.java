package com.trifsoft.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trifsoft.mymovies.databinding.ActivityMovieDetailsBinding;
import com.trifsoft.mymovies.models.MovieData;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding activityMovieDetailsBinding;
    ImageView selectedMoviePoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        selectedMoviePoster = findViewById(R.id.selectedMoviePoster);

        activityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/1HOBv1QxSbTwn5VyZ2vAVRhdR8e.jpg")
                .placeholder(R.drawable.no_image)
                .error(R.drawable.ic_launcher_background)
                .into(selectedMoviePoster);

        int id = getIntent().getIntExtra("movieId", -1);
        if(id == -1){
            Toast.makeText(this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
        }
        else{
            AppRepository appRepository = new AppRepository();
            appRepository.getMovieDataMutableLiveData(id).observe(this, new Observer<MovieData>() {
                @Override
                public void onChanged(MovieData movieData) {
                    activityMovieDetailsBinding.setMovieData(movieData);
                    Log.e("TAG", "Glide before");
                    Glide.with(MovieDetailsActivity.this)
                        .load("https://image.tmdb.org/t/p/w500" + movieData.getBackdropPath())
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.ic_launcher_background)
                        .into(selectedMoviePoster);
                    Log.e("TAG", "Glide after");
                }
            });
        }
    }
}