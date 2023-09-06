package com.trifsoft.mymovies.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.trifsoft.mymovies.models.MovieData;
import com.trifsoft.mymovies.models.Result;

@Database(entities = {Result.class, MovieData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

	public abstract ResultDAO resultDAO();
	public abstract MovieDataDAO movieDataDAO();

	private static AppDatabase instance;

	public static synchronized AppDatabase getInstance(Context context){
		if (instance == null){
			instance = Room.databaseBuilder(context.getApplicationContext(),
				AppDatabase.class, "database")
				.fallbackToDestructiveMigration()
				.build();
		}
		return instance;
	}
}