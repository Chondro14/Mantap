package com.market_tradis.appsmovie.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.market_tradis.appsmovie.Database.MovieHelper;
import com.market_tradis.appsmovie.Model.Favorite;
import com.market_tradis.appsmovie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.provider.CalendarContract.Reminders.CONTENT_URI;
import static com.market_tradis.appsmovie.Database.DatabaseContract.URI_CONTENT;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    List<Favorite> favoriteList;
    private  Context context;
    Cursor cursor;
    String IMG_URL="https://image.tmdb.org/t/p/original";

    public StackRemoteViewsFactory(Context context) {
        this.context=context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if(cursor!=null){
            cursor.close();
        }
        final long identitytoken= Binder.clearCallingIdentity();
            cursor=context.getContentResolver().query(URI_CONTENT,null,null,null,null);
        Binder.restoreCallingIdentity(identitytoken);
        MovieHelper movieHelper=new MovieHelper(context);
        movieHelper.open();
        favoriteList=new ArrayList<>();
        favoriteList.addAll(movieHelper.query());

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return favoriteList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.image_banner_info);
        Bitmap bitmap=null;
        try {
            bitmap= Glide.with(context).asBitmap().load(IMG_URL+favoriteList.get(position).getFavImage()).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        remoteViews.setImageViewBitmap(R.id.imageView2,bitmap);
        Bundle extra=new Bundle();
        extra.putInt(NewAppWidget.EXTRA_ITEM,position);
        Intent filintent=new Intent();
        filintent.putExtras(extra);
        remoteViews.setOnClickFillInIntent(R.id.imageView2,filintent);


        return remoteViews;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;

    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
