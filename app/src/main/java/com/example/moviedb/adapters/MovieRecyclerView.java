package com.example.moviedb.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.MovieModel;
import java.util.List;

//adapter makes it easy to efficiently display large sets of data (movie list)
public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieModel> movies;
    private OnMovieListener onMovieListener; //onMovieListener is an interface

    //CTOR
    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    // deals with inflation of card layout as an item for the recycler view
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent,false);
                return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    //deals with the setting of different data amd methods related to clicks on particular items of the recyclerview
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder holder, int position) {
        //below the image:title
        ((MovieViewHolder)holder).title.setText(movies.get(position).getTitle());

        //below title:year of release
        ((MovieViewHolder)holder).yearOfRelease.setText(movies.get(position).get_release_date().substring(0,4));
        //use substring method to get only year of release (the 4 first character of the date)

        // image of the movie on the top of the movie cell
                Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500"+movies.get(position).get_poster_path()).
                        into(((MovieViewHolder)holder).image_view);
    }

    @Override
    public int getItemCount() { //return number of movies:length of recyclerview
       if(movies !=null) {
            return movies.size();
        }
         return 0;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged(); //update all observers
    }


    public MovieModel getSelectedMovie(int position) {  //call when we click on a movie cell
        if(movies !=null && movies.size() > 0) {
            return movies.get(position);
        }
        return null;
    }
}
