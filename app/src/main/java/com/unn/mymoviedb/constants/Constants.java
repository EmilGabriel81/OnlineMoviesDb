package com.unn.mymoviedb.constants;

import com.unn.mymoviedb.R;

public class Constants {

    public static String BASE_URL ="https://api.themoviedb.org/3/movie/";
    public static final String API_KEY = "dc06ee63b37749dea84132eb343eb589";

    public static final String[] NAMES={"Now Playing","Top Rated","Upcoming","Popular"};//,"Search by Id","Search by name"};
    public static final String[] DESCRIPTION={"Movies in the theaters","Top rated by critics","Fresh in cinemas","Memorable and popular "};//,"Search a movie by an id","Search a movie by its name"};
    public static final int[] IMAGES = {R.drawable.camera,R.drawable.famous,R.drawable.film,R.drawable.popularity};//,R.drawable.film_roll,R.drawable.video_player};
    public static final String EXTRA_LIST = "listFromMain";
    public static final String BASE_LINK = "https://image.tmdb.org/t/p/w500";

    private Constants(){
    // PRIVATE CONSTRUCTOR
    }
}
