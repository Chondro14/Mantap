package com.market_tradis.appsmovie.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.market_tradis.appsmovie.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    private static final String TOAST_ACTION="com.market_tradis.TOAST_ACTION";
    public static final String EXTRA_ITEM="com.market_tradis.EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent intent=new Intent(context,StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
       views.setRemoteAdapter(R.id.stack_view,intent);
       views.setEmptyView(R.id.stack_view,R.id.empty);

       Intent toastIntent=new Intent(context,NewAppWidget.class);
       toastIntent.setAction(NewAppWidget.TOAST_ACTION);

        PendingIntent toastPendingIntent=PendingIntent.getBroadcast(context,0,toastIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view,toastPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,views);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction()!=null){
            if(intent.getAction().equals(TOAST_ACTION)){
                int viewIndex=intent.getIntExtra(EXTRA_ITEM,0);
                Toast.makeText(context,"Toached View"+viewIndex,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

