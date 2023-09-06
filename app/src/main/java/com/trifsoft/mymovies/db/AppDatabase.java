package com.trifsoft.mymovies.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.trifsoft.mymovies.models.Result;

@Database(entities = {Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

	public abstract ResultDAO resultDAO();

	private static AppDatabase instance;

	public static synchronized AppDatabase getInstance(Context context){
		if (instance == null){
			instance = Room.databaseBuilder(context.getApplicationContext(),
				AppDatabase.class, "database")
				.fallbackToDestructiveMigration()
				.addCallback(roomCallback)
				.build();
		}
		return instance;
	}

	private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
		@Override
		public void onCreate(@NonNull SupportSQLiteDatabase db) {
			super.onCreate(db);

			ResultDAO resultDAO = instance.resultDAO();


			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(new Runnable() {
				@Override
				public void run() {

					//TODO Ubaciti pocetne elemente baze podataka u odgovarajuce tabele

				}
			});
		}
	};
}