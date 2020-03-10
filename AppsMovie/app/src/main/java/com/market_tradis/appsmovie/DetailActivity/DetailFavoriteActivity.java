package com.market_tradis.appsmovie.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.market_tradis.appsmovie.R;

public class DetailFavoriteActivity extends AppCompatActivity {

    public static String FavoriteMT="fav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);
    }
}
