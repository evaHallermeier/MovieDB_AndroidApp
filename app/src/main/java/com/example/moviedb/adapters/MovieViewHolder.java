package com.example.moviedb.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;

import org.jetbrains.annotations.NotNull;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView title, extra_info;
    ImageView image_view;
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull @NotNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        title = itemView.findViewById(R.id.movie_title);
        extra_info = itemView.findViewById(R.id.extra_info);
        image_view = itemView.findViewById(R.id.movie_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
