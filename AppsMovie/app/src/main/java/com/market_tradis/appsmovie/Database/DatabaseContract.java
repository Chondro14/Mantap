package com.market_tradis.appsmovie.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final class DBColumns implements BaseColumns{
        public static final String TABLE_MV="MV";
        public static final String TABLE_TV="TV";
        static final String id="id";
        static final String title="title";
        static final String image="poster_path";
        static final String overview="overview";
        static final String rating="rate";
        static final String realesedate="date";
        static final String language="lang";
        static final String backdrop="backdrop_path";
        static final String popularity="popularity";

    }
    public static final String AUTHORITY="com.market_tradis.moviedb";
    public static final Uri URI_CONTENT=new Uri.Builder().scheme("content")
            .authority(AUTHORITY).appendPath(DBColumns.TABLE_MV).appendPath(DBColumns.TABLE_TV).build();

}
