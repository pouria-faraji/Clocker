package com.blacksite.clocker.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import android.util.Log
import com.blacksite.clocker.R
import com.blacksite.clocker.activity.MainActivity
import android.app.PendingIntent
import android.view.View
import com.blacksite.clocker.R.id.clock_canvas
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.application.PrefManager
import com.blacksite.clocker.model.`object`.Hand
import kotlinx.android.synthetic.main.content_main.*


/**
 * Created by p.faraji on 4/18/2018.
 */
class MyWidgetProvider : AppWidgetProvider() {
    var views: RemoteViews? = null
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.e("logger", "widget updated")
    }

    override fun onReceive(context: Context, intent: Intent) {
        var hand = Hand()
        var prefManager = PrefManager(context)
        //find out the action
        val action = intent.action
        //is it time to update
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE == action) {
            views = RemoteViews(context.packageName,
                    R.layout.widget)

//            if(prefManager!!.cachedBitmap != "null") {
//                views!!.setImageViewBitmap(R.id.clock_face_imageview_widget, Global.loadImageFromStorage(prefManager!!.cachedBitmap))
//            }
//
//            hand.makeAllGoneWidget(hand.loadHands()[prefManager!!.handPosition].analogClockWidget!!, views!!)


            val choiceIntent = Intent(context, MainActivity::class.java)
            val clickPendIntent = PendingIntent.getActivity(context, 0, choiceIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views!!.setOnClickPendingIntent(R.id.widget_layout, clickPendIntent)
            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views)
        }
    }
}