package com.trifsoft.mymovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trifsoft.mymovies.MovieDetailsActivity;
import com.trifsoft.mymovies.R;
import com.trifsoft.mymovies.databinding.RecyclerviewItemBinding;
import com.trifsoft.mymovies.models.Result;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

	private final ArrayList<Result> listData;

	private final Context context;

	public MovieListAdapter(ArrayList<Result> listData, Context context){
		this.listData = listData;
		this.context = context;
	}

	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View listItem = inflater.inflate(R.layout.recyclerview_item, parent, false);
		return new MyViewHolder(listItem);
	}

	@Override
	public int getItemCount() {
		return listData.size();
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
		final Result myListData = listData.get(position);
		RecyclerviewItemBinding recyclerviewItemBinding = DataBindingUtil.bind(holder.itemView);
		assert recyclerviewItemBinding != null;
		recyclerviewItemBinding.setAllMoviesData(myListData);

		Glide
				.with(context)
				.load("https://image.tmdb.org/t/p/w500" + myListData.getPosterPath())
				.centerCrop()
				.placeholder(R.drawable.no_image)
				.into(holder.moviePoster);
	}

	public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		public View itemView;
		public ImageView moviePoster;

		public MyViewHolder(@NonNull View itemView) {
			super(itemView);

			this.itemView = itemView;
			this.moviePoster = itemView.findViewById(R.id.moviePoster);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			//TODO napraviti itemClickListener
			Result result = listData.get(getAdapterPosition());

			Intent intent = new Intent(context, MovieDetailsActivity.class);
			intent.putExtra("movieId", result.getId());
			context.startActivity(intent);
		}
	}
}
