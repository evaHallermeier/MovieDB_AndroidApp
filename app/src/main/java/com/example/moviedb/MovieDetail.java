package com.example.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.models.MovieModel;

public class MovieDetail extends AppCompatActivity {

    private ImageView image;
    private TextView title, overview, date;
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        //create element of detail window

        image = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        overview = findViewById(R.id.detail_overview);
        ratingBar = findViewById(R.id.ratingBar);
        date = findViewById(R.id.date);
        getDataMovie();
    }

    private void getDataMovie() {
        if(getIntent().hasExtra("movie")) {
            MovieModel movieMOdel = getIntent().getParcelableExtra("movie");
            Log.v("Tag", "incoming intent" + movieMOdel.getMovie_id());
            title.setText(movieMOdel.getTitle());
            overview.setText(movieMOdel.get_overview());
            ratingBar.setRating(movieMOdel.getVote_average()/2);
            String realeaseDate = "Release Date: " + movieMOdel.get_release_date();
            date.setText(realeaseDate);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" +
                    movieMOdel.get_poster_path()).into(image);

        }
    }
}