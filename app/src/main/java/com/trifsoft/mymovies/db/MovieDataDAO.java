package com.trifsoft.mymovies.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.trifsoft.mymovies.models.MovieData;

import java.util.List;

@Dao
public interface MovieDataDAO {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	public long insert(MovieData movieData);

	@Update
	public void update(MovieData movieData);

	@Delete
	public void delete(MovieData movieData);

	@Query("select * from movieData")
	public LiveData<List<MovieData>> getMovieDatas();

	@Query("select * from movieData where movieData_id ==:movieDataId")
	public LiveData<MovieData> getMovieData(long movieDataId);

	@Query("DELETE FROM movieData")
	public void clearTable();

}