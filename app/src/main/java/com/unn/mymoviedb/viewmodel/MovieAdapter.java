package com.unn.mymoviedb.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.unn.mymoviedb.R;
import com.unn.mymoviedb.constants.Constants;
import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.views.MovieDetailActivity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{


    Context context;
    List<MovieModel>movieListRecycler;

    public MovieAdapter(Context context, List<MovieModel>mMoviesList){

        this.context = context;
        movieListRecycler = mMoviesList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.my_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String movieTitle = movieListRecycler.get(position).getTitle();
        String movieInfo = movieListRecycler.get(position).getOverview();
        String moviePoster = Constants.BASE_LINK+movieListRecycler.get(position).getPoster_path();
        String releaseDate = movieListRecycler.get(position).getRelease_date();
        Double movieRating = movieListRecycler.get(position).getVote_average();
        Picasso.get().load(moviePoster).fit().centerCrop().into(holder.myImage);
       // container.addView(imageView);

       // Log.v("The Base link is ", movieListRecycler.get(position).getPoster_path());// -> throws an error
        holder.movieTitle.setText(movieTitle);
        holder.movieInfo.setText(movieInfo);

        holder.mainLyout.setOnClickListener((view -> {

            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movieTitle", movieTitle);
            intent.putExtra("movieReleaseDate", releaseDate);
            intent.putExtra("movieOverview", movieInfo);
            intent.putExtra("movieImage", moviePoster);
            intent.putExtra("movieRating", String.valueOf(movieRating));

            context.startActivity(intent);
        }));


    }

    @Override
    public int getItemCount() {
        //Log.v("MovieAdapter", "Total number of movies in list is : "+String.valueOf(movieListRecycler.size()));
        return ((movieListRecycler != null) &&(movieListRecycler.size()!=0)?movieListRecycler.size():0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        TextView movieInfo;
        ImageView myImage;
        ConstraintLayout mainLyout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieInfo = itemView.findViewById(R.id.movie_info);
            myImage = itemView.findViewById(R.id.image_movie);
            mainLyout = itemView.findViewById(R.id.main_layout);

        }
    }

}
