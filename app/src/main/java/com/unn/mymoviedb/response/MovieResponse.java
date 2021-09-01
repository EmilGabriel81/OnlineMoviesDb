package com.unn.mymoviedb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unn.mymoviedb.model.MovieModel;

// This class is for a single movie request
public class MovieResponse {

    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie(){return movie;}


    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
