package com.example.moviedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.example.moviedb.adapters.MovieRecyclerView;
import com.example.moviedb.adapters.OnMovieListener;
import com.example.moviedb.model.MovieModel;
import com.example.moviedb.viewModel.MovieList_VM;

import java.util.List;

//in MVVM this activity is a part of the view : User Interface and observe the view model
public class MainScreenMovieListActivity extends AppCompatActivity implements OnMovieListener {
//main activity and display list of movies

    //fields
    private Toolbar mToolbar;
    private MovieList_VM movieList_vm;
    private RecyclerView recycleView;
    private MovieRecyclerView movieRecyclerViewAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //fore creation of the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar: for top navigation bar to show name of the app
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        movieList_vm = new ViewModelProvider(this).get(MovieList_VM.class); //view model

        SetRecycleView(); //recycleview
        ObserverAnyChange();
        movieList_vm.searchPop_MovieAPI(1); //get first page of popular movies :ask view model
    }

    @Override
    public void onMovieClick(int position) { //called in situation of click on a movie cell
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("movie", movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);
    }


//observe data change from VM
    private void ObserverAnyChange(){ //update list of film from view model
        movieList_vm.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModel) { //observe any data change
                if(movieModel !=null) {
                    for(MovieModel model: movieModel) {
                        movieRecyclerViewAdapter.setMovies(movieModel);
                    }
                }
            }
        });
    }

    private void SetRecycleView() { //for recyclerview
        recycleView = findViewById(R.id.recycleView);
        movieRecyclerViewAdapter = new MovieRecyclerView(this);
        recycleView.setAdapter(movieRecyclerViewAdapter);
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));

        //load next pages of popular movies
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)) {
                    movieList_vm.searchNextPage();
                    //display next movies
                }
            }


        });
    }
}