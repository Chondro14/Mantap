package com.market_tradis.appsmovie.Notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.market_tradis.appsmovie.BuildConfig;
import com.market_tradis.appsmovie.DetailActivity.DetailMovieActivity;
import com.market_tradis.appsmovie.Model.Movie;
import com.market_tradis.appsmovie.R;
import com.market_tradis.appsmovie.Response.MovieResponse;
import com.market_tradis.appsmovie.Service.Client;
import com.market_tradis.appsmovie.Service.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReleaseReminder extends BroadcastReceiver {
    public final static int NOTIFICATION_ID = 1;
    private static final String RELEASE_MESSAGE = "release_message";
    public static final String RELEASE_TYPE = "release_type";
    public List<Movie> movieList = new ArrayList<>();

    public ReleaseReminder() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        try {
            Client client=new Client();
            Service service=client.getClient().create(Service.class);
            Call<MovieResponse> call=service.getMovieUpComing(BuildConfig.API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    assert response.body() != null;
                    movieList=response.body().getMovies();
                    for(Movie movie:movieList){
                        String title=movie.getTitle();
                        String message=movie.getOverview();
                        String poster=movie.getPoster();
                        String rate=movie.getVote_avg();
                        String lang=movie.getLanguage();
                        String id=movie.getId();
                        int notifId=1;
                        sendNotification(context,title,message,poster,rate,lang,id,notifId);
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText((context), t.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendNotification(Context context, String title, String description, String poster, String rate, String language, String mid, int id) {
        String CHANNEL_ID = "ID";
        String CHANNEL_NAME = "NAME";

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        Movie movie = new Movie();
        movie.setId(mid);
        movie.setTitle(title);
        movie.setOverview(description);
        movie.setLanguage(language);
        movie.setPoster(poster);
        movie.setVote_avg(String.valueOf(rate));
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.Movie, movie);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_movie_black_24dp)
                .setContentTitle(title)
                .setContentText(description)
                .setColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(uri);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }
        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(id, notification);
        }
    }

    public void setAlarm(Context context, String type, String time, String message) {
        cancelAlarm(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, ReleaseReminder.class);
        intent.putExtra(RELEASE_MESSAGE, message);
        intent.putExtra(RELEASE_TYPE, type);
        String[] timeArray = time.split(":");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt((timeArray[0])));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(context, "Reminder active", Toast.LENGTH_SHORT).show();

    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
        Toast.makeText(context, "Reminder noactive", Toast.LENGTH_SHORT).show();
    }
}
