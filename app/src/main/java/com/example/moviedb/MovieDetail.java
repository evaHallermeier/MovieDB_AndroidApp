package com.example.moviedb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.moviedb.model.MovieModel;

//in MVVM this activity is a part of the view : User Interface and observe the view model
public class MovieDetail extends AppCompatActivity {
    private ImageView image;
    private TextView title, overview, date;
    private String title_string;
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
        getDataMovie(); //get data of the movie clicked
        setToolBar(); //set  the toolbar: top navigation bar with back button and title of the movie clicked
    }

    private void getDataMovie() { //get fata of the movie from movie model
        if(getIntent().hasExtra("movie")) {
            MovieModel movieMOdel = getIntent().getParcelableExtra("movie");
            title_string = movieMOdel.getTitle();
            title.setText(title_string);
            overview.setText(movieMOdel.get_overview());
            ratingBar.setRating(movieMOdel.getVote_average()/2);
            String realeaseDate = "Release Date: " + movieMOdel.get_release_date();
            date.setText(realeaseDate);
            //for image of the movie
            Glide.with(this).load("https://image.tmdb.org/t/p/w500" +
                    movieMOdel.get_poster_path()).into(image);
        }
    }

    private void setToolBar() {
        //toolbar with name of the film and back button
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbard);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.findViewById(R.id.toolBar_title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //display back button in order to come back to the main screen with the movie list
        textView.setText(title_string); //need first to get title of the movie in getdatamovie
        actionBar.setDisplayShowTitleEnabled(false);
    }
}