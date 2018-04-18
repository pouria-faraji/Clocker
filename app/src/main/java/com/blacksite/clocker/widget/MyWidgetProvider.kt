package com.blacksite.clocker.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import com.blacksite.clocker.R


/**
 * Created by p.faraji on 4/18/2018.
 */
class MyWidgetProvider : AppWidgetProvider() {
    var views: RemoteViews? = null
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        //find out the action
        val action = intent.action
        //is it time to update
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE == action) {
            views = RemoteViews(context.packageName,
                    R.layout.widget)
            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views)
        }
    }
}