package com.unn.mymoviedb.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.unn.mymoviedb.R;
import com.unn.mymoviedb.constants.Constants;
import com.unn.mymoviedb.model.MovieModel;
import com.unn.mymoviedb.viewmodel.MyViewModel;
import com.unn.mymoviedb.viewmodel.RecyclerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;

    private EditText searchBy;
    private Button byNameBtn;
    private Button byIdBtn;
    // private String query;

    // ArrayLists for the RecyclerView;
    private ArrayList<MovieModel> nowPlayingMoviesList;
    private ArrayList<MovieModel> topRatedMoviesList;
    private ArrayList<MovieModel> upcomingMoviesList;
    private ArrayList<MovieModel> popularMoviesList;
    private ArrayList<MovieModel> searchedMoviesList;
    //To start an activity
    // ActivityResultLauncher<Intent> activityResultLauncher;


    // To add SearchView hint text use the following code : search.setQueryHint("Custom Search Hint");
    //VieModel
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //No Title Bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        searchBy = findViewById(R.id.search_edittext);
        byNameBtn = findViewById(R.id.button_search_by_name);
        byIdBtn = findViewById(R.id.button_search_by_id);


        this.nowPlayingMoviesList = new ArrayList<>();
        this.topRatedMoviesList = new ArrayList<>();
        this.upcomingMoviesList = new ArrayList<>();
        this.popularMoviesList = new ArrayList<>();

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // Calling the observer methods
        //Observing the results after searching for a title
        //  observeAnyChange();
        observePopMoviesChange();
        //Now playing movies search and observe
        searchNowPlayingMovies(1);
        observeNowPlayingMoviesChange();
        //Top rated movies search and observe
        searchTopRatedMovies(1);
        observeTopRatedMoviesChange();
        //Upcoming movies search and observe
        searchUpcomingMovies(1);
        observeUpcomingMoviesChange();
        //popular movies search and observe
        searchPopularMovies(1);
        observePopMoviesChange();

        byNameBtn.setOnClickListener(view -> {
            String query = searchBy.getText().toString();
            fetchSearchMovie(query);
        });

        byIdBtn.setOnClickListener(view -> {
            int query = 0;
            try {
                query = Integer.parseInt(searchBy.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
            fetchSearchMovie(query);
        });

        listView = (ListView) findViewById(R.id.listmovies_listview);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onTitleClicked(adapterView, view, i, l);
            }
        });
    }

    private void fetchSearchMovie(String query) {
        if (query.equals("") || query.isEmpty()) {
            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }
        searchMovieApi(query, 1);
        myViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
//                            searchedMoviesList = new ArrayList<>();
//                            //searchedMoviesList.clear();
//                            searchedMoviesList.addAll(movieModels);
                    fetchIntent((ArrayList) movieModels, RecyclerActivity.class);
                }
            }
        });
        searchBy.setText("");
        //Log.d(TAG, "onCreate: search button by name --> size of the list is :"+searchedMoviesList.size());
        //Toast.makeText(this, "onCreate: search button by name --> size of the list is :"+searchedMoviesList.size(), Toast.LENGTH_SHORT).show()
    }

    private void fetchSearchMovie(int query) {
        if (query <= 0) {
            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }
        searchMovieById(query);
        myViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    MovieModel myMovie = movieModels.get(0);
                    String moviePoster = Constants.BASE_LINK + myMovie.getPoster_path();
                    Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                    intent.putExtra("movieTitle", myMovie.getTitle());
                    intent.putExtra("movieReleaseDate", myMovie.getRelease_date());
                    intent.putExtra("movieOverview", myMovie.getOverview());
                    intent.putExtra("movieImage", moviePoster);
                    startActivity(intent);
                }
            }
        });
    }

    private void onTitleClicked(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "onItemClicked: name " + Constants.NAMES[i]);
        //Toast.makeText(MainActivity.this,"You clicked on: "+NAMES[i]+" position "+i,Toast.LENGTH_SHORT).show();
        switch (i) {
            case 0:
                //search now playing movies
                fetchIntent(nowPlayingMoviesList, RecyclerActivity.class);
                for (MovieModel m : nowPlayingMoviesList) {
                    Log.d("Now Playing Movies", m.getTitle());
                }
                break;
            case 1:
                //search top rated movies
                fetchIntent(topRatedMoviesList, RecyclerActivity.class);
                break;
            case 2:
                //search upcoming movies
                fetchIntent(upcomingMoviesList, RecyclerActivity.class);
                break;
            case 3:
                //search popular movies
                fetchIntent(popularMoviesList, RecyclerActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "Work in progress at position " + i, Toast.LENGTH_SHORT).show();
        }

    }


    private void fetchIntent(ArrayList<MovieModel> listForIntent, Class destination) {
        Intent intent = new Intent(this, destination);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.EXTRA_LIST, listForIntent);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }


    //---------------------------------------------------------------------------------------------- fetching requests
    private void searchMovieApi(String query, int pageNumber) {
        myViewModel.searchMovieApi(query, pageNumber);
    }

    private void searchMovieById(int movie_id) {
        myViewModel.searchMovieById(movie_id);
    }

    public void searchPopularMovies(int pageNumber) {
        myViewModel.searchPopularMovies(pageNumber);
    }

    public void searchNowPlayingMovies(int pageNumber) {
        myViewModel.searchNowPlayingMovies(pageNumber);
    }

    public void searchTopRatedMovies(int pageNumber) {
        myViewModel.searchTopRatedMovies(pageNumber);
    }

    public void searchUpcomingMovies(int pageNumber) {
        myViewModel.searchUpcomingMovies(pageNumber);
    }

    //----------------------------------------------------------------------------------------------
    //Observe the data change
    // On any movie by id or by Name
    private void observeAnyChange() {// Observing the livew data stream of searched movies
        myViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing the live changes
                if (movieModels != null) {
                    searchedMoviesList.addAll(movieModels);
                }
            }
        });
    }

    private void observePopMoviesChange() {// Observing the livew data stream of popular movies
        myViewModel.getPopularMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing the live changes
                if (movieModels != null) {
                    popularMoviesList.addAll(movieModels);
                }
            }
        });
    }

    private void observeNowPlayingMoviesChange() {
        myViewModel.getNowPlayingMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing the live changes
                if (movieModels != null) {
                    nowPlayingMoviesList.addAll(movieModels);
                }
            }
        });
    }

    private void observeTopRatedMoviesChange() {
        myViewModel.getTopRatedMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing the live changes
                if (movieModels != null) {
                    topRatedMoviesList.addAll(movieModels);
                }
            }
        });
    }

    private void observeUpcomingMoviesChange() {
        myViewModel.getUpcomingMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing the live changes
                if (movieModels != null) {
                    upcomingMoviesList.addAll(movieModels);
                }
            }
        });
    }


    // the custom adapter class---------------------------------------------------------------------

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Constants.IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            TextView textView_description = (TextView) view.findViewById(R.id.textView_description);
            imageView.setImageResource(Constants.IMAGES[i]);
            textView_name.setText(Constants.NAMES[i]);
            textView_description.setText(Constants.DESCRIPTION[i]);
            return view;
        }
    }
}