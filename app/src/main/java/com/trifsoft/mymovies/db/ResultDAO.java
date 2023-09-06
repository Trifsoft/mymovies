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
	void insert(Result result);

	@Update
	void update(Result result);

	@Delete
	void delete(Result result);

	@Query("select * from result")
	LiveData<List<Result>> getResults();

	@Query("select * from result where result_id ==:resultId")
	LiveData<Result> getResult(long resultId);

	@Query("DELETE FROM result")
	void clearTable();

	@Query("select * from result where title like :search || '%'")
	LiveData<List<Result>> getResultsFromSearch(String search);

}