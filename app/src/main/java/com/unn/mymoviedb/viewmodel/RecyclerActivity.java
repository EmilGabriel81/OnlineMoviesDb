package com.unn.mymoviedb.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.unn.mymoviedb.R;
import com.unn.mymoviedb.constants.Constants;
import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
   // List<MovieModel> mMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No Title Bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recycler);

        Bundle bundle = getIntent().getExtras();
        List<MovieModel> mMoviesFromMain = bundle.getParcelableArrayList(Constants.EXTRA_LIST);
        recyclerView = findViewById(R.id.recycler_view);

        MovieAdapter myMoviesAdapter = new MovieAdapter(this,mMoviesFromMain);
        recyclerView.setAdapter(myMoviesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Floating Action Button ------------------------------------------------------------------
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.home_icon2, this.getTheme()));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerActivity.this, MainActivity.class);
                Snackbar.make(view, "Back to Home Page", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                startActivity(intent);

            }
        });
        // Floating Action Button ends--------------------------------------------------------------

    }
// On back Button Pressed method
    @Override
    public void onBackPressed() {

        startActivity(new Intent(this,MainActivity.class));
        this.finish();
    }

}