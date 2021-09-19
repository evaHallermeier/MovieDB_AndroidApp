package com.example.moviedb.adapters;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.models.MovieModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieModel> movies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent,false);
                return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).title.setText(movies.get(position).getTitle());
        ((MovieViewHolder)holder).extra_info.setText(movies.get(position).get_release_date());
        //for the moment extra info is release date

        //image
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+movies.get(position).get_poster_path()).
                into(((MovieViewHolder)holder).image_view);
    }

    @Override
    public int getItemCount() {
        if(movies !=null) {
            return movies.size();
        }
        return 0;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    public MovieModel getSelectedMovie(int position) {
        if(movies !=null && movies.size() > 0) {
            return movies.get(position);
        }
        return null;
    }


}
