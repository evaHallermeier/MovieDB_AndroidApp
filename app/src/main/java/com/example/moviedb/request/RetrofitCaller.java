package com.example.moviedb.request;
import com.example.moviedb.utils.APIparams;
import com.example.moviedb.utils.I_API_Movie;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCaller {

//singleton fields
    private static Retrofit.Builder retrofitBuiler = new Retrofit.Builder().
            baseUrl(APIparams.basicURL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = retrofitBuiler.build();
    private static I_API_Movie movie_API = retrofit.create(I_API_Movie.class);

    //methods
    public static  I_API_Movie getMovieAPI() {
        return movie_API;

    }

}
