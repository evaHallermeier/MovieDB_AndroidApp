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

import android.util.Log;
import android.view.Menu;

import com.example.moviedb.adapters.MovieRecyclerView;
import com.example.moviedb.adapters.OnMovieListener;
import com.example.moviedb.models.MovieModel;
import com.example.moviedb.viewModels.MovieList_VM;

import java.util.List;



public class MainScreenMovieListActivity extends AppCompatActivity implements OnMovieListener {
//contain list of movies

    //fields
    private Toolbar mToolbar;
    private MovieList_VM movieList_vm;
    private RecyclerView recycleView;
    private MovieRecyclerView movieRecyclerViewAdapter;




    private void ConfigureRecycleView() {

        movieRecyclerViewAdapter = new MovieRecyclerView(this);
        recycleView.setAdapter(movieRecyclerViewAdapter);
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));

        //load next pages of popular movies
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)) {
                    movieList_vm.searchNextPage();
                    Log.v("Tag", "nextpage: ");

                    //display next movies
                }
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



       mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        recycleView = findViewById(R.id.recycleView);

        movieList_vm = new ViewModelProvider(this).get(MovieList_VM.class);
        ConfigureRecycleView();
        ObserverAnyChange();
        ObservePopularMovies();
       movieList_vm.searchPop_MovieAPI(1); //get all popular movies

    }

    private void ObservePopularMovies(){
        movieList_vm.getPOp_Movies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModel) { //observe any data change
                if(movieModel !=null) {
                    for(MovieModel model: movieModel) {
                        Log.v("Tag", "ONCHANGED: "+ model.getTitle());
                        movieRecyclerViewAdapter.setMovies(movieModel);
                    }
                }
            }
        });
    }




//observe data change from VM
    private void ObserverAnyChange(){
        movieList_vm.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModel) { //observe any data change
                if(movieModel !=null) {
                    for(MovieModel model: movieModel) {
                        Log.v("Tag", "ONCHANGED: "+ model.getTitle());
                        movieRecyclerViewAdapter.setMovies(movieModel);
                    }
                }
            }
        });
    }





    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("movie", movieRecyclerViewAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}