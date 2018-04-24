package com.blacksite.clocker.application

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import com.blacksite.clocker.model.Item
import com.blacksite.clocker.model.`object`.Dial
import com.blacksite.clocker.model.`object`.Face
import com.blacksite.clocker.model.`object`.Hand
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import java.io.*


/**
 * Created by p.faraji on 4/17/2018.
 */
class Global {
    companion object {
        var faceListasItem = ArrayList<Item>()
        var dialLisasItem = ArrayList<Item>()
        var handListasItem = ArrayList<Item>()
        var handList = ArrayList<Hand>()
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

        fun saveToInternalStorage(bitmapImage: Bitmap): String {
            val cw = ContextWrapper(App.appContext)
            // path to /data/data/yourapp/app_data/imageDir
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            // Create imageDir
            val mypath = File(directory, "clock.png")

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return directory.absolutePath
        }

        fun loadImageFromStorage(path: String):Bitmap {
            var b:Bitmap? = null
            try {
                val f = File(path, "clock.png")
                b = BitmapFactory.decodeStream(FileInputStream(f))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                return b!!
            }

        }
    }




}