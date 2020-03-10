package com.market_tradis.appsmovie.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbfav";
    private static final int DATABASE_VERSION = 4;
    private static final String CREATE_TABLE_FAVORITE_MOVIES = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL)",
            DatabaseContract.DBColumns.TABLE_MV,
            DatabaseContract.DBColumns._ID,
            DatabaseContract.DBColumns.id,
           DatabaseContract.DBColumns.title,
            DatabaseContract.DBColumns.overview,
            DatabaseContract.DBColumns.rating,
           DatabaseContract.DBColumns.language,
            DatabaseContract.DBColumns.image,
            DatabaseContract.DBColumns.backdrop,
            DatabaseContract.DBColumns.popularity,
            DatabaseContract.DBColumns.realesedate
    );

    private static final String CREATE_TABLE_FAVORITE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.DBColumns.TABLE_TV,
            DatabaseContract.DBColumns._ID,
            DatabaseContract.DBColumns.id,
            DatabaseContract.DBColumns.title,
            DatabaseContract.DBColumns.overview,
            DatabaseContract.DBColumns.rating,
            DatabaseContract.DBColumns.language,
            DatabaseContract.DBColumns.image,
            DatabaseContract.DBColumns.backdrop,
            DatabaseContract.DBColumns.popularity,
            DatabaseContract.DBColumns.realesedate
    );


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITE_MOVIES);
        db.execSQL(CREATE_TABLE_FAVORITE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DBColumns.TABLE_MV);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.DBColumns.TABLE_TV);
        onCreate(db);
    }
}
