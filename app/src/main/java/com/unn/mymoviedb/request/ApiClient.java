package com.unn.mymoviedb.request;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unn.mymoviedb.constants.Constants;
import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.repository.Repository;
import com.unn.mymoviedb.response.MovieSearchResponse;
import com.unn.mymoviedb.viewmodel.AppExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class ApiClient {
    // The Live data
    private MutableLiveData<List<MovieModel>> mutableListMovies;
    private MutableLiveData<List<MovieModel>> mutableListPopularMovies;
    private MutableLiveData<List<MovieModel>> mutableListNowPlayingMovies;
    private MutableLiveData<List<MovieModel>> mutableListTopRatedMovies;
    private MutableLiveData<List<MovieModel>> mutableListUpcomingMovies;


    private static ApiClient instance;
    // Making Global Runnable request
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    //----------------------------------------------------------------------------------------------
    private RetrieveMovieByIdRunnable retrieveMovieByIdRunnable;
    private RetrievePopularMoviesRunnable retrievePopularMoviesRunnable;
    private RetrieveNowPlayingMoviesRunnable retrieveNowPlayingMoviesRunnable;
    private RetrieveTopRatedMoviesRunnable retrieveTopRatedMoviesRunnable;
    private RetrieveUpcomingMoviesRunnable retrieveUpcomingMoviesRunnable;

    //----------------------------------------------------------------------------------------------

    private ApiClient(){

        mutableListMovies = new MutableLiveData<>();// live data with searched movies
        mutableListPopularMovies = new MutableLiveData<>();//live data with popular movies
        mutableListNowPlayingMovies = new MutableLiveData<>();//live data with now playing movies
        mutableListTopRatedMovies = new MutableLiveData<>();//live data with top rated movies
        mutableListUpcomingMovies = new MutableLiveData<>();//live data with upcoming movies
    }


    public static ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mutableListMovies;
    }// returning a list of searched movies(by id or By Name)
    public LiveData<List<MovieModel>> getPopularMovies(){
        return mutableListPopularMovies;
    }// Retrieving a list with popular movies
    public LiveData<List<MovieModel>> getNowPlayingMovies(){
        return mutableListNowPlayingMovies;
    }// Retrieving a list with now playing movies
    public LiveData<List<MovieModel>> getTopRatedMovies(){
        return mutableListTopRatedMovies;
    }// Retrieving a list with top rated movies
    public LiveData<List<MovieModel>> getUpcomingMovies(){
        return mutableListUpcomingMovies;
    }// Retrieving a list with upcoming movies

    // search movies method
    public void searchMovieApi(String query, int pageNumber){// begin
        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends

    //----------------------------------------------------------------------------------------------
    // Searching a movie by a certain ID
    public void searchMovieById(int movie_id){// starts here
        if(retrieveMovieByIdRunnable != null){
            retrieveMovieByIdRunnable = null;
        }
        retrieveMovieByIdRunnable = new RetrieveMovieByIdRunnable(movie_id);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMovieByIdRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends here
    //----------------------------------------------------------------------------------------------

    // search popular movies movies method
    public void searchPopularMoviesApi(int pageNumber){// begin
        if(retrievePopularMoviesRunnable != null){
            retrievePopularMoviesRunnable = null;
        }
        retrievePopularMoviesRunnable = new RetrievePopularMoviesRunnable(pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrievePopularMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends
    //----------------------------------------------------------------------------------------------

    // search now playing movies method
    public void searchNowPlayingMoviesApi(int pageNumber){// begin
        if(retrieveNowPlayingMoviesRunnable != null){
            retrieveNowPlayingMoviesRunnable = null;
        }
        retrieveNowPlayingMoviesRunnable = new RetrieveNowPlayingMoviesRunnable(pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveNowPlayingMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends
    //----------------------------------------------------------------------------------------------

    // search top rated movies method
    public void searchTopRatedMoviesApi(int pageNumber){// begin
        if(retrieveTopRatedMoviesRunnable != null){
            retrieveTopRatedMoviesRunnable = null;
        }
        retrieveTopRatedMoviesRunnable = new RetrieveTopRatedMoviesRunnable(pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveTopRatedMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends
    //----------------------------------------------------------------------------------------------
    // search top rated movies method
    public void searchUpcomingMoviesApi(int pageNumber){// begin
        if(retrieveUpcomingMoviesRunnable != null){
            retrieveUpcomingMoviesRunnable = null;
        }
        retrieveUpcomingMoviesRunnable = new RetrieveUpcomingMoviesRunnable(pageNumber);
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveUpcomingMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // for cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }// ends
    //----------------------------------------------------------------------------------------------

    // We retrieve the data fro the REstAPI with a runnable class
    private class RetrieveMoviesRunnable implements Runnable{// our private runnable class

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber){
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;

        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getMovies(query, pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        // sending data to live data
                        // Post Value is used for background thread
                        // set value: not for background thread
                        mutableListMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mutableListMovies.getValue();
                        currentMovies.addAll(list);
                        mutableListMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getMovies(String query, int pageNumber){
            return MovieService.getMovieApi().searchMovie(Constants.API_KEY,query,pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    private class RetrieveMovieByIdRunnable implements Runnable{// our private runnable class

        private int movie_id;
        boolean cancelRequest;

        public RetrieveMovieByIdRunnable(int movie_id){
            this.movie_id = movie_id;
            cancelRequest = false;
        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getMovieById(movie_id).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    MovieModel moviebyId = (MovieModel) response.body();
                    List<MovieModel> list = new ArrayList<>();// A single movie but in an arraylist
                    list.add(moviebyId);
                        mutableListMovies.postValue(list);
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListMovies.postValue(null);
            }


        }
//
//        private Call<MovieModel> getMovieById(int movie_id){
//            return MovieService.getMovieApi().getMovie(movie_id,Constants.API_KEY);
//        }

        private Call<MovieModel> getMovieById(int movie_id){
            return MovieService.getMovieApi().getMovie(movie_id,Constants.API_KEY);
        }
        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------

    // We retrieve the data fro the REstAPI with a runnable class
    private class RetrievePopularMoviesRunnable implements Runnable{// our private runnable class
        private int pageNumber;
        boolean cancelRequest;

        public RetrievePopularMoviesRunnable(int pageNumber){
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getPopularMovies(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        // sending data to live data
                        // Post Value is used for background thread
                        // set value: not for background thread
                        mutableListPopularMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mutableListPopularMovies.getValue();
                        currentMovies.addAll(list);
                        mutableListPopularMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListPopularMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListPopularMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getPopularMovies(int pageNumber){
            return MovieService.getMovieApi().getPopularMovies(Constants.API_KEY,pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    // We retrieve the data fro the REstAPI with a runnable class
    private class RetrieveNowPlayingMoviesRunnable implements Runnable{// our private runnable class
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveNowPlayingMoviesRunnable (int pageNumber){
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getNowPlayingMovies(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        // sending data to live data
                        // Post Value is used for background thread
                        // set value: not for background thread
                        mutableListNowPlayingMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mutableListNowPlayingMovies.getValue();
                        currentMovies.addAll(list);
                        mutableListNowPlayingMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListNowPlayingMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListNowPlayingMovies.postValue(null);
            }
        }
        private Call<MovieSearchResponse> getNowPlayingMovies(int pageNumber){
            return MovieService.getMovieApi().getNowPlayingMovies(Constants.API_KEY,pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    // We retrieve the data fro the REstAPI with a runnable class
    private class RetrieveTopRatedMoviesRunnable implements Runnable{// our private runnable class
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveTopRatedMoviesRunnable (int pageNumber){
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getTopRatedMovies(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        // sending data to live data
                        // Post Value is used for background thread
                        // set value: not for background thread
                        mutableListTopRatedMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mutableListTopRatedMovies.getValue();
                        currentMovies.addAll(list);
                        mutableListTopRatedMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListTopRatedMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListTopRatedMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getTopRatedMovies(int pageNumber){
            return MovieService.getMovieApi().getTopRatedMovies(Constants.API_KEY,pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    // We retrieve the data fro the REstAPI with a runnable class
    private class RetrieveUpcomingMoviesRunnable implements Runnable{// our private runnable class
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveUpcomingMoviesRunnable (int pageNumber){
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }
        @Override
        public void run() {
            // getting the response object
            try{
                Response response = getUpcomingMovies(pageNumber).execute();
                if(cancelRequest){
                    return;
                }
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        // sending data to live data
                        // Post Value is used for background thread
                        // set value: not for background thread
                        mutableListUpcomingMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mutableListUpcomingMovies.getValue();
                        currentMovies.addAll(list);
                        mutableListUpcomingMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error -- > "+error);
                    mutableListUpcomingMovies.postValue(null);
                }
            }catch (IOException e){
                e.printStackTrace();
                mutableListUpcomingMovies.postValue(null);
            }


        }

        private Call<MovieSearchResponse> getUpcomingMovies(int pageNumber){
            return MovieService.getMovieApi().getUpcomingMovies(Constants.API_KEY,pageNumber);
        }

        private void setCancelRequest(){
            Log.v("Tag", "Cancelling request");
            cancelRequest = true;
        }
    }
}
