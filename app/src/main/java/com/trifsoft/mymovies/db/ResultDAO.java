package com.trifsoft.mymovies.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trifsoft.mymovies.models.Result;

import java.util.List;

@Dao
public interface ResultDAO {

	@Insert
	public long insert(Result result);

	@Update
	public void update(Result result);

	@Delete
	public void delete(Result result);

	@Query("select * from result")
	public LiveData<List<Result>> getResults();

	@Query("select * from result where result_id ==:resultId")
	public LiveData<Result> getResult(long resultId);

	@Query("DELETE FROM result")
	public void clearTable();

}