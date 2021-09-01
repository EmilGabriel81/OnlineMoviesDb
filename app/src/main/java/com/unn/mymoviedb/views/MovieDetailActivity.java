package com.unn.mymoviedb.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.unn.mymoviedb.R;
import com.unn.mymoviedb.viewmodel.RecyclerActivity;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView movieDetailTitle;
    private TextView movieDetailReleaseDate;
    private TextView movieDetailOverview;
    private ImageView movieDetailImage;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_movie_detail);

         movieDetailTitle = findViewById(R.id.movie_title_detail);
         movieDetailReleaseDate = findViewById(R.id.movie_releasedate_detail);
         movieDetailOverview = findViewById(R.id.movie_overview_detail);
         movieDetailImage = findViewById(R.id.movie_image_detail);
        ratingBar=(RatingBar)findViewById(R.id.rating_bar);
         getData();



        // Floating Action Button ------------------------------------------------------------------
        FloatingActionButton fab2 = findViewById(R.id.floatingActionButton2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab2.setImageDrawable(getResources().getDrawable(R.drawable.home_icon2, this.getTheme()));
        } else {
            fab2.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        }
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailActivity.this, MainActivity.class);
                Snackbar.make(view, "Back to Home Page", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                startActivity(intent);

            }
        });
        // Floating Action Button ends--------------------------------------------------------------
    }

    private void getData() {
        if(getIntent().hasExtra("movieImage")&&getIntent().hasExtra("movieTitle")&&
                getIntent().hasExtra("movieReleaseDate")&& getIntent().hasExtra("movieOverview")&& getIntent().hasExtra("movieRating")){
            Intent intent = getIntent();
           String title = intent.getStringExtra("movieTitle");
           String releaseDate = intent.getStringExtra("movieReleaseDate");
           String movieImage = intent.getStringExtra("movieImage");
           String movieOverview = intent.getStringExtra("movieOverview");
           String movieRating = intent.getStringExtra("movieRating");

            setData(title,releaseDate,movieOverview,movieImage, movieRating);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }

    }

    private void setData(String title, String releaseDate, String movieOverview, String movieImage, String movieRating) {
        movieDetailTitle.setText(title);
        movieDetailReleaseDate.setText(releaseDate);
        movieDetailOverview.setText(movieOverview);
        Float rating = (Float.parseFloat(movieRating)/2);
        ratingBar.setRating(rating);
        Picasso.get().load(movieImage).fit().centerCrop().into(this.movieDetailImage);
        Log.d("MovieDetailActivity", "setData method link is ---->: "+movieImage);

    }
    @Override
    public void onBackPressed() {
        if(this.getIntent().getClass().equals(MainActivity.class)){
            startActivity(new Intent(this,MainActivity.class));
        }
        this.finish();
    }

}