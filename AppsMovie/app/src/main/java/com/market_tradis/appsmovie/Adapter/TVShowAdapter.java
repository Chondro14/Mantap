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
import com.market_tradis.appsmovie.DetailActivity.DetailTVShowActivity;
import com.market_tradis.appsmovie.Model.Movie;
import com.market_tradis.appsmovie.Model.TVShow;
import com.market_tradis.appsmovie.R;

import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ViewTVHolder> {
    private Context context;
    private List<TVShow> tvShowList;

    public TVShowAdapter(Context context,List<TVShow> tvShowList){
        this.context=context;
        this.tvShowList=tvShowList;
    }

    public List<TVShow> getTvShowList() {
        return tvShowList;
    }

    public void setTvShowList(List<TVShow> tvShowList) {
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public ViewTVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);
        return new ViewTVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewTVHolder holder, int position) {
        holder.bind(tvShowList.get(holder.getAdapterPosition()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailTVShowActivity.class);
                intent.putExtra(DetailTVShowActivity.TVShowp,tvShowList.get(holder.getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class ViewTVHolder extends RecyclerView.ViewHolder {
        TextView Title,Rating;
        ImageView imageView;
        CardView cardView;
        ViewTVHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.title_mv);
            Rating=itemView.findViewById(R.id.rating_mv);
            imageView=itemView.findViewById(R.id.image_movie);
            cardView=itemView.findViewById(R.id.card_mv);
        }
        void bind(TVShow tvShow){
            String imageUrl="https://image.tmdb.org/t/p/w500";
            Title.setText(tvShow.getTitle());
            Rating.setText(tvShow.getVote_avg());
            Glide.with(context).load(imageUrl+tvShow.getPoster()).into(imageView);
        }
    }
}
