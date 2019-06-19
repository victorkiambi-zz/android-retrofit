package com.api.config;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;

    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    private Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull

    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder myViewHolder, int i) {
        myViewHolder.movieTitle.setText(movies.get(i).getTitle());
        myViewHolder.data.setText(movies.get(i).getReleaseDate());
        myViewHolder.movieDescription.setText(movies.get(i).getOverview());
        myViewHolder.rating.setText(movies.get(i).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView movieImage;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            moviesLayout = itemView.findViewById(R.id.movies_layout);
//            movieImage = itemView.findViewById(R.id.m);
            movieTitle = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.subtitle);
            movieDescription = itemView.findViewById(R.id.description);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}

