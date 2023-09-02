package com.trifsoft.mymovies.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

	private static Retrofit retrofit = null;

	public static MovieDataService getMovieDataService(){
		if (retrofit == null){
			retrofit = new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/3/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		}

		return retrofit.create(MovieDataService.class);

	}




}
