package com.example.myapplication.view;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.myapplication.R;

public class ExampleWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId: appWidgetIds){
            Intent intent = new Intent(context , SplashActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT
            );
            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            view.setOnClickPendingIntent(R.id.tv_title, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,view);
        }
    }
}
