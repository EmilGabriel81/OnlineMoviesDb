package com.unn.mymoviedb.request;

import com.unn.mymoviedb.IMoviesApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {
    private static final String BASE_URL ="https://api.themoviedb.org/3/movie/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static IMoviesApi api = retrofit.create(IMoviesApi.class);

    public static IMoviesApi getMovieApi(){
        return api;
    }


}
