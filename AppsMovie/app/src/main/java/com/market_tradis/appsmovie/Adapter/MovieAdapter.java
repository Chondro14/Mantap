package com.market_tradis.appsmovie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.market_tradis.appsmovie.DetailActivity.DetailMovieActivity;
import com.market_tradis.appsmovie.Model.Movie;
import com.market_tradis.appsmovie.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewMovieHolder> {
    private Context context;
    private List<Movie> movies;


    public MovieAdapter(Context context,List<Movie> movies){
        this.context=context;
        this.movies=movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_movies,parent,false);
        return new ViewMovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewMovieHolder holder, int position) {
        holder.bind(movies.get(holder.getAdapterPosition()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.Movie,movies.get(holder.getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewMovieHolder extends RecyclerView.ViewHolder {
        TextView title,rating;
        ImageView image;
        CardView cardView;
        ViewMovieHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_mv);
            rating=itemView.findViewById(R.id.rating_mv);
            image=itemView.findViewById(R.id.image_movie);
            cardView=itemView.findViewById(R.id.card_mv);
        }
        void bind(Movie movie){
            String imageUrl="https://image.tmdb.org/t/p/w500";
            title.setText(movie.getTitle());
            rating.setText(movie.getVote_avg());
            Glide.with(itemView.getContext()).load(imageUrl+movie.getPoster()).into(image);
        }
    }
}
