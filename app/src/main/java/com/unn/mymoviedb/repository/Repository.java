package com.unn.mymoviedb.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.request.ApiClient;

import java.util.List;

public class Repository {
    // intermediate class that acts like a repository


    private static Repository instance;
    private ApiClient movieApiClient;

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    private Repository(){
        movieApiClient = ApiClient.getInstance();

    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getPopularMovies(){
        return movieApiClient.getPopularMovies();
    }

    public LiveData<List<MovieModel>> getNowPlayingMovies(){
        return movieApiClient.getNowPlayingMovies();
    }

    public LiveData<List<MovieModel>> getTopRatedMovies(){
        return movieApiClient.getTopRatedMovies();
    }// Retrieving a list with top rated movies

    public LiveData<List<MovieModel>> getUpcomingMovies(){
        return movieApiClient.getUpcomingMovies();
    }// Retrieving a list with top rated movies


    public void searchMovieApi(String query, int pageNumber){
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    //----------------------------------------------------------------------------------------------
    public void searchMovieById(int movie_id){
        movieApiClient.searchMovieById(movie_id);
    }

    public void searchPopularMovies(int pageNumber){
        movieApiClient.searchPopularMoviesApi(pageNumber);
    }
    //----------------------------------------------------------------------------------------------

    public void searchNowPlayingMovies(int pageNumber){
        movieApiClient.searchNowPlayingMoviesApi(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
    public void searchTopRatedMovies(int pageNumber){
        movieApiClient.searchTopRatedMoviesApi(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
    public void searchUpcomingMovies(int pageNumber){
        movieApiClient.searchUpcomingMoviesApi(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
}
