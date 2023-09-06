package com.trifsoft.mymovies;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.trifsoft.mymovies.db.AppDatabase;
import com.trifsoft.mymovies.db.MovieDataDAO;
import com.trifsoft.mymovies.db.ResultDAO;
import com.trifsoft.mymovies.models.AllMoviesData;
import com.trifsoft.mymovies.models.MovieData;
import com.trifsoft.mymovies.models.Result;
import com.trifsoft.mymovies.service.MovieDataService;
import com.trifsoft.mymovies.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class AppRepository {

	private final MutableLiveData<ArrayList<Result>> allMoviesMutableLiveData = new MutableLiveData<>();
	private final MutableLiveData<MovieData> movieDataMutableLiveData = new MutableLiveData<>();

	private final ResultDAO resultDAO;

	private final MovieDataDAO movieDataDAO;

	public AppRepository(Application application) {
		AppDatabase appDatabase = AppDatabase.getInstance(application);
		resultDAO = appDatabase.resultDAO();
		movieDataDAO = appDatabase.movieDataDAO();
	}

	public LiveData<List<Result>> getAllResults() {
		return resultDAO.getResults();
	}

	public LiveData<List<Result>> getResultsFromSearch(String search){
		return resultDAO.getResultsFromSearch(search);
	}

	public void insertResult(Result result){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {
				resultDAO.insert(result);
			}
		});
	}
	public LiveData<Result> getResult(long resultId){
		return resultDAO.getResult(resultId);
	}
	public void deleteResult(Result result){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {
				resultDAO.delete(result);
			}
		});
	}
	public void updateResult(Result result){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {
				resultDAO.update(result);
			}
		});
	}

	public void clearTablesAndInsertResults(ArrayList<Result> results){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {
				resultDAO.clearTable();
				movieDataDAO.clearTable();
				for(Result result: results){
					resultDAO.insert(result);
				}
			}
		});
	}

	public LiveData<List<MovieData>> getAllMovieDatas() {
		return movieDataDAO.getMovieDatas();
	}

	public void insertMovieData(MovieData movieData){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {

				movieDataDAO.insert(movieData);
			}
		});
	}
	public LiveData<MovieData> getMovieData(long movieDataId){
		return movieDataDAO.getMovieData(movieDataId);
	}
	public void deleteMovieData(MovieData movieData){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {

				movieDataDAO.delete(movieData);
			}
		});
	}
	public void updateMovieData(MovieData movieData){
		ExecutorService executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {
			@Override
			public void run() {

				movieDataDAO.update(movieData);
			}
		});
	}

	public MutableLiveData<ArrayList<Result>> getAllMoviesMutableLiveData() {
		MovieDataService movieDataService = RetrofitInstance.getMovieDataService();

		Call<AllMoviesData> call = movieDataService.getAllMoviesData("b7b587bdbe6ac708867c87c7e943e29f");

		call.enqueue(new Callback<AllMoviesData>() {
			@Override
			@EverythingIsNonNull
			public void onResponse(Call<AllMoviesData> call, Response<AllMoviesData> response) {
				AllMoviesData allMoviesData = response.body();
				assert allMoviesData != null;
				ArrayList<Result> allResults = (ArrayList<Result>) allMoviesData.getResults();
				allMoviesMutableLiveData.setValue(allResults);

				clearTablesAndInsertResults(allResults);
			}

			@Override
			@EverythingIsNonNull
			public void onFailure(Call<AllMoviesData> call, Throwable t) {

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

				insertMovieData(movieData);
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
