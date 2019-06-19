package com.api.config;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "b1422e7b80cff3571090be90e6544244";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org",
                    Toast.LENGTH_LONG).show();
            return;
        }

        MovieApiService apiService =
                ApiClient.getClient().create(MovieApiService.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Log.e(TAG, t.toString());
            }

        });

    }
}