package com.blacksite.clocker.application

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

/**
 * Created by p.faraji on 4/17/2018.
 */
class Global {
    companion object {
        fun getStatusBarHeight(): Int {
            var result = 0
            val resourceId = App.appContext!!.getResources().getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = App.appContext!!.getResources().getDimensionPixelSize(resourceId)
            }
            return result
        }
        fun getAppHeight(): Int {
            val metrics = DisplayMetrics()
            (App.appContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(metrics)
            //        App.getAppContext().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            val statusBarHeight = getStatusBarHeight()

            return metrics.heightPixels - statusBarHeight
        }
        fun getAppWidth(): Int {
            val metrics = DisplayMetrics()
            (App.appContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels
        }

        fun dp_to_px(dp: Int): Int {
            val r = App.appContext!!.resources
            val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
            return px.toInt()
        }
        fun reduceImage(resource:Int):Drawable{
            var bitmap = (ContextCompat.getDrawable(App.appContext, resource) as BitmapDrawable).bitmap
            var reducedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width/5, bitmap.height/5, true)
            return BitmapDrawable(App.appContext!!.resources, reducedBitmap)
        }
    }




}