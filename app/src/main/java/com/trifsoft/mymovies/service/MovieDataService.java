package com.trifsoft.mymovies.service;

import com.trifsoft.mymovies.models.AllMoviesData;
import com.trifsoft.mymovies.models.MovieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDataService {

	@GET("movie/popular")
	Call<AllMoviesData> getAllMoviesData(@Query("api_key") String api_key);

	@GET("movie/{movieId}")
	Call<MovieData> getMovieData(@Path("movieId") int movieId, @Query("api_key") String api_key);

}