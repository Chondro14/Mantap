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
import com.market_tradis.appsmovie.DetailActivity.DetailFavoriteActivity;
import com.market_tradis.appsmovie.Model.Favorite;
import com.market_tradis.appsmovie.R;

import java.util.LinkedList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewFavoriteHolder> {
    Context context;
    LinkedList<Favorite> favoriteList;
    public FavoriteAdapter(Context context){
        this.context=context;


    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(LinkedList<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewFavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);
        return new ViewFavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.ViewFavoriteHolder holder, int position) {
        holder.bind();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailFavoriteActivity.class);
                intent.putExtra(DetailFavoriteActivity.FavoriteMT,favoriteList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getFavoriteList().size();
    }

    public class ViewFavoriteHolder extends RecyclerView.ViewHolder {
        TextView Title,Rating;
        ImageView imageView;
        CardView cardView;
        public ViewFavoriteHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.title_mv);
            Rating=itemView.findViewById(R.id.rating_mv);
            imageView=itemView.findViewById(R.id.image_movie);
            cardView=itemView.findViewById(R.id.card_mv);
        }
        void bind(){
            Title.setText(favoriteList.get(getAdapterPosition()).getFavTitle());
            Rating.setText(favoriteList.get(getAdapterPosition()).getFavRating());
            String imageUrl="https://image.tmdb.org/t/p/w500";
            Glide.with(context).load(imageUrl+favoriteList.get(getAdapterPosition()).getFavImage()).into(imageView);
        }
    }
}
