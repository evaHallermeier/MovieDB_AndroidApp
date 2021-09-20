package com.example.moviedb.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.moviedb.model.MovieModel;
import com.example.moviedb.repository.MovieRepository;

import java.util.List;

//VIEW MODEL : link netween model and view and responsible to transfer data
public class MovieList_VM extends ViewModel {

    private MovieRepository movieRepository;

    //CTOR
    public MovieList_VM() {
        movieRepository =MovieRepository.getInstance();
    }

    //return movie list received
    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }

    //ask Repository to ask API to get the movie list
    public void searchPop_MovieAPI(int pageNB) {
        movieRepository.searchPopular_MovieAPI(pageNB);
    }

    //ask repository to get next movie in the next page of the request
    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
