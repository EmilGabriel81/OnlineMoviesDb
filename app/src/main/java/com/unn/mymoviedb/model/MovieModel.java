package com.unn.mymoviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private boolean adult;
    private String backdrop_path;
    private int[] genre_ids;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public MovieModel(boolean adult, String backdrop_path, int[] genre_ids, int id, String original_language, String original_title, String overview, double popularity, String poster_path, String release_date, String title, boolean video, double vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    protected MovieModel(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        genre_ids = in.createIntArray();
        id = in.readInt();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        vote_count = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public boolean isAdult() {
        return adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public int getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(backdrop_path);
        parcel.writeIntArray(genre_ids);
        parcel.writeInt(id);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeDouble(popularity);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeString(title);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(vote_average);
        parcel.writeInt(vote_count);
    }

    /**
     *   {
     *             "adult": false,   // --> boolean
     *             "backdrop_path": "/7WJjFviFBffEJvkAms4uWwbcVUk.jpg", // --> String or null
     *             "genre_ids": [ // -- > Array of integers
     *                 12,
     *                 14,
     *                 35,
     *                 28
     *             ],  // -- > Array of integers
     *             "id": 451048,// Integer
     *             "original_language": "en", // String
     *             "original_title": "Jungle Cruise", // String
     *             "overview": "Dr. Lily Houghton enlists the aid of wisecracking skipper Frank Wolff to take her down the Amazon in his dilapidated boat. Together, they search for an ancient tree that holds the power to heal – a discovery that will change the future of medicine.", --> String
     *             "popularity": 4918.356,// Double?
     *             "poster_path": "/9dKCd55IuTT5QRs989m9Qlb7d2B.jpg", // String or null
     *             "release_date": "2021-07-28", // --> String
     *             "title": "Jungle Cruise", // String
     *             "video": false, // Boolean
     *             "vote_average": 8.1, // Double
     *             "vote_count": 1048// Integer
     *         },
     */

}
