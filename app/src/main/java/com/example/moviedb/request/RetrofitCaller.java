package com.example.moviedb.request;

import com.example.moviedb.API.APIparams;
import com.example.moviedb.API.I_API_Movie;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*     using retrofit:
a type-safe REST client for Android, Java.
 The library provides a powerful framework for authenticating and interacting with APIs
  and sending network requests with OkHttp
  his library makes downloading JSON or XML data from a web API fairly straightforward.
  Once the data is downloaded then it is parsed into a Plain Old Java Object (POJO)
  which must be defined for each "resource" in the response.
  API interfaces are turned into callable objects
 */
public class RetrofitCaller {

//singleton fields
    private static Retrofit.Builder retrofitBuiler = new Retrofit.Builder().
            baseUrl(APIparams.basicURL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuiler.build();

    private static I_API_Movie movie_API = retrofit.create(I_API_Movie.class);

    //method
    public static  I_API_Movie getMovieAPI() { //used by MovieAPIclient
        return movie_API;
    }

}
