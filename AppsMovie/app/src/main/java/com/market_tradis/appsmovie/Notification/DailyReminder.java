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
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.market_tradis.appsmovie.Activity.MainActivity;
import com.market_tradis.appsmovie.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DailyReminder extends BroadcastReceiver {
    public static final String TYPE_ONE_TIME = "OneTimeAlarm";
    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    private final int ID_ONETIME = 100;
    private final int ID_REPEATING = 101;

    public DailyReminder() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String types=intent.getStringExtra(EXTRA_TYPE);
        String message=intent.getStringExtra(EXTRA_MESSAGE);

        String Title=types.equalsIgnoreCase(TYPE_ONE_TIME)?TYPE_ONE_TIME:TYPE_REPEATING;
        int notifId=types.equalsIgnoreCase(TYPE_ONE_TIME)?ID_ONETIME:ID_REPEATING;
        ShowAlarmNotif(context,Title,message,notifId);




    }
    private void ShowAlarmNotif(Context context,String title,String message,int notifId){
        String CHANNEL_ID="ID";
        String CHANNEL_NAME="NAME";

        Intent intent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,notifId,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmsound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID).setSmallIcon(R.drawable.ic_movie_black_24dp).setContentTitle(title).setContentText(message).setContentIntent(pendingIntent).setColor(ContextCompat.getColor(context,android.R.color.holo_purple)).setSound(alarmsound);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);
            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channel);
            }
        }
        Notification notification=builder.build();
        if(notificationManager!=null){
            notificationManager.notify(notifId,notification);
        }
    }
    public void setRepeatingAlarm(Context context, String type, String time, String message) {

        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Toast.makeText(context, "Daily Reminder Active", Toast.LENGTH_SHORT).show();
    }
    private final static String TIME_FORMAT = "HH:mm";

    public boolean isDateInvalid(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(date);
            return false;
        } catch (ParseException | java.text.ParseException e) {
            return true;
        }
    }


    public void cancelAlarm(Context context, String typeRepeating) {
    }
}
