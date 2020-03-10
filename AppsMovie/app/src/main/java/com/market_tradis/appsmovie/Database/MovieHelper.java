package com.market_tradis.appsmovie.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.market_tradis.appsmovie.Model.Favorite;

import java.util.ArrayList;

public class MovieHelper {
    private static final String DATABASE_TABLE= DatabaseContract.DBColumns.TABLE_MV;
    private Context context;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public MovieHelper(Context context){
        this.context=context;
    }
    public MovieHelper open() throws SQLException {
        helper=new DatabaseHelper(context);
        database=helper.getWritableDatabase();
        return this;
    }
    public void close(){
        helper.close();
    }
    public ArrayList<Favorite> query(){
        ArrayList<Favorite> arrayList=new ArrayList<>();
        Cursor cursor=database.query(DATABASE_TABLE,null,null,null,null,null, DatabaseContract.DBColumns.id+" DESC",null);
        cursor.moveToFirst();
        Favorite favorite;
        if(cursor.getCount()>0){
            do {
                favorite=new Favorite();
                favorite.setFavid(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.id)));
                favorite.setFavBacdrop(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.backdrop)));
                favorite.setFavImage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.image)));
                favorite.setFavOriginalLanguage(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.language)));
                favorite.setFavOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.overview)));
                favorite.setFavRating(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.rating)));
                favorite.setFavReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.realesedate)));
                favorite.setFavTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.title)));
                favorite.setFavPopularity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.DBColumns.popularity)));

                arrayList.add(favorite);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());


        }
        cursor.close();
        return arrayList;
    }
    public long insert(@NonNull Favorite data){
        ContentValues values=new ContentValues();
        values.put(DatabaseContract.DBColumns.id,data.getFavid());
        values.put(DatabaseContract.DBColumns.backdrop,data.getFavBacdrop());
        values.put(DatabaseContract.DBColumns.image,data.getFavImage());
        values.put(DatabaseContract.DBColumns.language,data.getFavOriginalLanguage());
        values.put(DatabaseContract.DBColumns.overview,data.getFavOverview());
        values.put(DatabaseContract.DBColumns.rating,data.getFavRating());
        values.put(DatabaseContract.DBColumns.realesedate,data.getFavReleaseDate());
        values.put(DatabaseContract.DBColumns.title,data.getFavTitle());
        values.put(DatabaseContract.DBColumns.popularity,data.getFavPopularity());
        return database.insert(DATABASE_TABLE,null,values);

    }
    public void  delete(String id){
        database.delete(DatabaseContract.DBColumns.TABLE_MV, DatabaseContract.DBColumns.id+" ='"+id+"'",null);

    }
    public boolean checkObject(String id){
        database=helper.getWritableDatabase();
        String query="SELECT "+ DatabaseContract.DBColumns.id +" FROM "+ DatabaseContract.DBColumns.TABLE_MV+" WHERE "+ DatabaseContract.DBColumns.id+" =?";
        Cursor cursor=database.rawQuery(query,new String[]{id});
        if(cursor.getCount()>0){
            cursor.close();
            return false;
        }
        else {
            return true;
        }

    }

}
