package com.trifsoft.mymovies;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.trifsoft.mymovies.models.AllMoviesData;
import com.trifsoft.mymovies.models.MovieData;
import com.trifsoft.mymovies.models.Result;
import com.trifsoft.mymovies.service.MovieDataService;
import com.trifsoft.mymovies.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AppRepository {

	private final MutableLiveData<ArrayList<Result>> allMoviesMutableLiveData = new MutableLiveData<>();
	private final MutableLiveData<MovieData> movieDataMutableLiveData = new MutableLiveData<>();

	public MutableLiveData<ArrayList<Result>> getAllMoviesMutableLiveData(int page) {
		MovieDataService movieDataService = RetrofitInstance.getMovieDataService();

		Call<AllMoviesData> call = movieDataService.getAllMoviesData("b7b587bdbe6ac708867c87c7e943e29f", page);

		call.enqueue(new Callback<AllMoviesData>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<AllMoviesData> call, Response<AllMoviesData> response) {
				AllMoviesData allMoviesData = response.body();
				assert allMoviesData != null;
				allMoviesMutableLiveData.setValue((ArrayList<Result>) allMoviesData.getResults());
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<AllMoviesData> call, Throwable t) {
				Log.e("TAG","Failed: " + t.getMessage());
			}
		});

		return allMoviesMutableLiveData;
	}

	public MutableLiveData<MovieData> getMovieDataMutableLiveData(int id) {
		MovieDataService movieDataService = RetrofitInstance.getMovieDataService();

		Call<MovieData> call = movieDataService.getMovieData(id, "b7b587bdbe6ac708867c87c7e943e29f");

		call.enqueue(new Callback<MovieData>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<MovieData> call, Response<MovieData> response) {
				MovieData movieData = response.body();
				assert movieData != null;
				movieDataMutableLiveData.setValue(movieData);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<MovieData> call, Throwable t) {
				Log.e("TAG","Failed: " + t.getMessage());
			}
		});

		return movieDataMutableLiveData;
	}
}
