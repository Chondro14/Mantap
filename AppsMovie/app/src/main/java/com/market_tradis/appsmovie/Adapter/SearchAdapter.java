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
import com.market_tradis.appsmovie.DetailActivity.DetailSearchActivity;
import com.market_tradis.appsmovie.Model.Search;
import com.market_tradis.appsmovie.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewSearchHolder> {
    private Context context;
    private List<Search> searchList;
    public SearchAdapter(Context context,List<Search> searchList){
        this.context=context;
        this.searchList=searchList;
    }

    public List<Search> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Search> searchList) {
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public ViewSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);
        return new ViewSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewSearchHolder holder, int position) {
        holder.bind(searchList.get(holder.getAdapterPosition()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailSearchActivity.class);
                intent.putExtra(DetailSearchActivity.Search,searchList.get(holder.getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewSearchHolder extends RecyclerView.ViewHolder {
        TextView title,rating;
        ImageView imageView;
        CardView cardView;
        public ViewSearchHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_mv);
            rating=itemView.findViewById(R.id.rating_mv);
            imageView=itemView.findViewById(R.id.image_movie);
            cardView=itemView.findViewById(R.id.card_mv);
        }
        void bind(Search search){
            String imageUrl="https://image.tmdb.org/t/p/w500";
            title.setText(search.getSearch_title());
            rating.setText(String.valueOf(search.getSearch_rating()));
            Glide.with(context).load(imageUrl+search.getSearch_poster()).into(imageView);
        }
    }
}
