package com.example.moviedb.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moviedb.R;
import org.jetbrains.annotations.NotNull;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //elements of movie sell in main screen
    TextView title, yearOfRelease;
    ImageView image_view;
    OnMovieListener onMovieListener; //interface

    public MovieViewHolder(@NonNull @NotNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        title = itemView.findViewById(R.id.movie_title);
        yearOfRelease = itemView.findViewById(R.id.extra_info);
        image_view = itemView.findViewById(R.id.movie_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //when we click on a movie cell
        onMovieListener.onMovieClick(getAdapterPosition());
    } //onMovieClick is a method of the interface OnMovieListener
}
