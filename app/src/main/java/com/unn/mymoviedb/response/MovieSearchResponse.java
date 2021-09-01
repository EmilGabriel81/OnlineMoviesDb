package com.unn.mymoviedb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.unn.mymoviedb.model.MovieModel;

import java.util.List;

// This class is for multiple movies - popular movies

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public int getTotalCount(){
        return this.total_count;
    }
    public List<MovieModel> getMovies(){
        return movies;
    }


    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}