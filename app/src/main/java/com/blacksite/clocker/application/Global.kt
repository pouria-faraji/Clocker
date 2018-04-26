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
import com.blacksite.clocker.R
import java.io.*


/**
 * Created by p.faraji on 4/17/2018.
 */
class Global {
    companion object {
        var reducedBitmaps = HashMap<Int,Bitmap>()
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
            var reducedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width/35, bitmap.height/35, true)
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
        fun reduceImageAsBitmap(resource:Int):Bitmap{
            var bitmap = (ContextCompat.getDrawable(App.appContext, resource) as BitmapDrawable).bitmap
            var reducedBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width/5, bitmap.height/5, true)
            return reducedBitmap
        }
        fun generateReducedBitmaps(){
            for(item in faceListasItem){
                reducedBitmaps.put(item.image!!, reduceImageAsBitmap(item.image!!))
            }
            for(item in dialLisasItem){
                reducedBitmaps.put(item.image!!, reduceImageAsBitmap(item.image!!))
            }
            for(item in handListasItem){
                reducedBitmaps.put(item.image!!, reduceImageAsBitmap(item.image!!))
            }
        }
        fun getColorNameByCode(code:Int):String{
            when(code){
                1 -> return "grey"
                2 -> return "blue"
                3 -> return "red"
                4 -> return "green"
                else -> return "grey"
            }
        }
        fun getCircleResourceByColorCode(code:Int):Int{
            when(code){
                1 -> return R.drawable.grey_circle
                2 -> return R.drawable.blue_circle
                3 -> return R.drawable.red_circle
                4 -> return R.drawable.green_circle
                else -> return R.drawable.grey_circle
            }
        }
        fun getSelectedCircleResourceByColorCode(code:Int):Int{
            when(code){
                1 -> return R.drawable.grey_circle_selected
                2 -> return R.drawable.blue_circle_selected
                3 -> return R.drawable.red_circle_selected
                4 -> return R.drawable.green_circle_selected
                else -> return R.drawable.grey_circle_selected
            }
        }
    }




}