package com.unn.mymoviedb;

import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.response.MovieResponse;
import com.unn.mymoviedb.response.MovieSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesApi {

   // String BASE_URL ="https://api.themoviedb.org/3/movie/";
    //public static final String API_KEY = "dc06ee63b37749dea84132eb343eb589";

    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopularMovies(
            @Query("api_key")String api_key,
            @Query("page")int page
    );

    @GET("/3/movie/now_playing")
    Call<MovieSearchResponse> getNowPlayingMovies(
            @Query("api_key")String api_key,
            @Query("page")int page
    );

    @GET("/3/movie/top_rated")
    Call<MovieSearchResponse> getTopRatedMovies(
            @Query("api_key")String api_key,
            @Query("page")int page
    );

    @GET("/3/movie/upcoming?")
    Call<MovieSearchResponse> getUpcomingMovies(
            @Query("api_key")String api_key,
           // @Query("language")String language,
            @Query("page")int page
    );

    // ---------------------------------------------------------------------------------------------

    // Search for a movie name or a category
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key")String api_key,
            @Query("query")String query,
            @Query("page")int page
    );

    // search for a particulary ID
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key")String api_key);


//    // search for a particulary ID
//    @GET("/3/movie/{movie_id}?")
//    Call<MovieResponse> getMovie(
//            @Path("movie_id") int movie_id,
//            @Query("api_key")String api_key);
//
//    @GET("/3/movie/{movie_id}?")
//    Call<MovieResponse> getMovie(@Path("movie_id") int movie_id, @Query("api_key")String api_key);






    // Example API Request https://api.themoviedb.org/3/movie/550?api_key=dc06ee63b37749dea84132eb343eb589
    //Popular movies https://api.themoviedb.org/3/movie/popular?api_key=dc06ee63b37749dea84132eb343eb589
    // Images base Link https://image.tmdb.org/t/p/w500/
    // Images Example link https://image.tmdb.org/t/p/w500/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg

    //-------------------------------------------------------------------------------------------------

    //Top rated https://api.themoviedb.org/3/movie/top_rated?api_key=dc06ee63b37749dea84132eb343eb589&language=en-US&page=1
    //latest Movie https://api.themoviedb.org/3/movie/latest?api_key=dc06ee63b37749dea84132eb343eb589&language=en-US
    //Now playing movies https://api.themoviedb.org/3/movie/now_playing?api_key=dc06ee63b37749dea84132eb343eb589&language=en-US&page=1
    //Upcoming https://api.themoviedb.org/3/movie/upcoming?api_key=dc06ee63b37749dea84132eb343eb589&language=en-US&page=1

}
