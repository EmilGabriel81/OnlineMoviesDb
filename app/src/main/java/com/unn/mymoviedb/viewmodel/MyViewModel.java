package com.unn.mymoviedb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.repository.Repository;

import java.util.List;

public class MyViewModel extends ViewModel {
    // the ViewModel class

    private Repository movieRepository;


    public MyViewModel() {
        movieRepository = Repository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }
    public LiveData<List<MovieModel>> getPopularMovies(){
        return movieRepository.getPopularMovies();
    }// Retrieving a list with popular movies
    public LiveData<List<MovieModel>> getNowPlayingMovies(){
        return movieRepository.getNowPlayingMovies();
    }// Retrieving a list with now playing movies
    public LiveData<List<MovieModel>> getTopRatedMovies(){
        return movieRepository.getTopRatedMovies();
    }// Retrieving a list with top rated movies
    public LiveData<List<MovieModel>> getUpcomingMovies(){
        return movieRepository.getUpcomingMovies();
    }// Retrieving a list with upcoming movies

    //----------------------------------------------------------------------------------------------
    public void searchMovieApi(String query, int pageNumber) {
        movieRepository.searchMovieApi(query, pageNumber);
    }
    //----------------------------------------------------------------------------------------------
    public void searchMovieById(int movie_id) {
        movieRepository.searchMovieById(movie_id);
    }
    public void searchPopularMovies(int pageNumber){
        movieRepository.searchPopularMovies(pageNumber);
    }
    //----------------------------------------------------------------------------------------------

    public void searchNowPlayingMovies(int pageNumber){
        movieRepository.searchNowPlayingMovies(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
    public void searchTopRatedMovies(int pageNumber){
        movieRepository.searchTopRatedMovies(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
    public void searchUpcomingMovies(int pageNumber){
        movieRepository.searchUpcomingMovies(pageNumber);
    }
    //----------------------------------------------------------------------------------------------
}
