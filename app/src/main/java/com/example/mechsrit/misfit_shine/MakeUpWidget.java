package com.example.mechsrit.misfit_shine;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class MakeUpWidget extends AppWidgetProvider {
    public static final String  SPH_KEY="Bunni";
    public static final String PREF_KEY="KEY";
    public static final String DESCRIPTION="description";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        SharedPreferences sharedPreferences=context.getSharedPreferences(SPH_KEY,Context.MODE_PRIVATE);
        String prname=sharedPreferences.getString(PREF_KEY,"APP NOT OPENED");
        String prdesc=sharedPreferences.getString(DESCRIPTION,"NO PRODUCT");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.make_up_widget);
        views.setTextViewText(R.id.appwidget_text, prname+"\n "+prdesc);

        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
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

