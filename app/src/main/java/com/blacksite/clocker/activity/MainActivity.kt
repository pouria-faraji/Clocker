package com.blacksite.clocker.activity

import android.app.WallpaperManager
import android.graphics.*
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.blacksite.clocker.R
import com.blacksite.clocker.adapter.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.blacksite.clocker.application.Global
import com.blacksite.clocker.model.Item
import android.graphics.drawable.BitmapDrawable
import android.graphics.RectF
import android.graphics.Bitmap
import android.widget.AdapterView.OnItemClickListener
import com.blacksite.clocker.application.App
import com.blacksite.clocker.application.Constants
import com.blacksite.clocker.application.PrefManager
import com.blacksite.clocker.model.`object`.Face
import android.content.ComponentName
import android.widget.RemoteViews
import android.appwidget.AppWidgetManager
import android.content.DialogInterface
import com.blacksite.clocker.model.`object`.Dial
import com.blacksite.clocker.widget.MyWidgetProvider
import android.graphics.BitmapFactory
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AnalogClock
import android.widget.Toast
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorChangedListener
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter: ItemAdapter? = null
    var itemList = ArrayList<Item>()
    private var prefManager: PrefManager? = null
    var face = Face()
    var dial = Dial()

    var remoteViews:RemoteViews? = null
    var thisWidget:ComponentName? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Clocker"

        prefManager = PrefManager(App.appContext!!)

        prepareDrawer()
        prepareUI()
        prepareAdapter(face)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Your widget has been created.", Snackbar.LENGTH_LONG)
                    .setAction("Widget", null).show()
            updateWidget()
            val appWidgetManager = AppWidgetManager.getInstance(this)
            appWidgetManager.updateAppWidget(thisWidget, remoteViews)
        }

        face_color_btn.setOnClickListener(showColorClickListener)
//		color_picker.addOnColorChangedListener { selectedColor ->
//            // Handle on color change
//            clock_face_imageview.colorFilter = LightingColorFilter(Color.WHITE, Color.parseColor("#" + Integer.toHexString(selectedColor)))
//            Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor))
//        }
    }

       override fun onResume() {
        super.onResume()
    }

    val showColorClickListener = View.OnClickListener { view ->
        showColorDialog()
    }
    fun showColorDialog(){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose Color")
                .initialColor(Color.parseColor(prefManager!!.faceColorDialog))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setOnColorChangedListener { selectedColor ->

                }
                .setOnColorSelectedListener { selectedColor ->

                }
                .setPositiveButton("Ok", ColorPickerClickListener(){
                    dialog, selectedColor, allColors ->
                    prefManager!!.faceColor = "#" + Integer.toHexString(selectedColor)
                    prefManager!!.faceColorDialog = "#" + Integer.toHexString(selectedColor)
                    clock_face_imageview.colorFilter = LightingColorFilter(Color.WHITE, Color.parseColor("#" + Integer.toHexString(selectedColor)))
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener(){
                    dialog, which ->

                })
                .showAlphaSlider(false)
                .showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright))
                .build()
                .show()
    }
    fun prepareData(model:Any){
        var currentFacePosition = prefManager!!.facePosition
        var currentDialPosition = prefManager!!.dialPosition
        if(model is Face){
            adapter!!.makeAllUnselect(currentFacePosition)
        }else if(model is Dial){
            adapter!!.makeAllUnselect(currentDialPosition)
        }
        adapter!!.notifyDataSetChanged()

        clock_face_imageview.setImageResource(face.loadFacesAsGridItem()[currentFacePosition].image!!)
        clock_dial_imageview.setImageResource(dial.loadDialsAsGridItem()[currentDialPosition].image!!)
        clock_face_imageview.colorFilter = LightingColorFilter(Color.WHITE, Color.parseColor(prefManager!!.faceColor))


//        clock_face_imageview.setColorFilter(ContextCompat.getColor(this, R.color.red), PorterDuff.Mode.SRC_IN)
//        clock_face_imageview.setColorFilter(Color.parseColor("#cc3535"))


//        clock_face_imageview.colorFilter = LightingColorFilter(Color.WHITE, ContextCompat.getColor(this, R.color.red))

        updateWidget()

    }
    fun prepareAdapter(model:Any){
        if(model is Face){
            itemList = face.loadFacesAsGridItem()
        }else if(model is Dial){
            itemList = dial.loadDialsAsGridItem()
        }

        adapter = ItemAdapter(this, itemList)

        main_grid.numColumns = Constants.NUMBER_ITEMS_EACH_ROW
        main_grid.adapter = adapter

        main_grid.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            adapter!!.makeAllUnselect(position)
            adapter!!.notifyDataSetChanged()

            if(model is Face){
//                clock_face_imageview.setImageResource(itemList[position].image!!)
                prefManager!!.facePosition = position
            }else if(model is Dial){
                prefManager!!.dialPosition = position
            }
            prepareData(model)

//            updateWidget(position)
        }

        prepareData(model)
    }
    fun updateWidget(){
        remoteViews = RemoteViews(this.packageName, R.layout.widget)
        thisWidget = ComponentName(this, MyWidgetProvider::class.java)

        clock_canvas.destroyDrawingCache()
        clock_canvas.buildDrawingCache()
        remoteViews!!.setImageViewBitmap(R.id.clock_face_imageview_widget, clock_canvas.drawingCache)

//        val intent = Intent(this, MyWidgetProvider::class.java)
//        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
//        val ids = AppWidgetManager.getInstance(application).getAppWidgetIds(thisWidget)
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
//        sendBroadcast(intent)

    }
    fun prepareUI(){
        clock_face_imageview.layoutParams.height = Global.getAppWidth()/2
        clock_face_imageview.layoutParams.width = Global.getAppWidth()/2
        val padding = clock_face_imageview.layoutParams.width/5
        clock_face_imageview.setPadding(padding, padding, padding, padding)


        clock_dial_imageview.layoutParams.height = Global.getAppWidth()/2
        clock_dial_imageview.layoutParams.width = Global.getAppWidth()/2

        clock_main_layout.layoutParams.height = Global.getAppWidth()/2
        clock_wallpaper.layoutParams.height = Global.getAppWidth()/2

        var wallpaperManager: WallpaperManager = WallpaperManager.getInstance(this)
        var wallpaperDrawable = wallpaperManager.drawable

        val mbitmap = (wallpaperDrawable as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
//        val imageRounded = Bitmap.createBitmap(200, 200, mbitmap.config)
        val canvas = Canvas(imageRounded)
        val mpaint = Paint()
        mpaint.isAntiAlias = true
        mpaint.shader = BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        canvas.drawRoundRect(RectF(0f, 0f, mbitmap.width.toFloat(), mbitmap.height.toFloat()), 70f, 70F, mpaint)// Round Image Corner 100 100 100 100
        canvas.drawRoundRect(RectF(0f, 0f, (Global.getAppWidth()-(2*Global.dp_to_px(8))).toFloat(), clock_wallpaper.layoutParams.height.toFloat()), Global.dp_to_px(8).toFloat(), Global.dp_to_px(8).toFloat()
                , mpaint)// Round Image Corner 100 100 100 100

        clock_wallpaper.setImageBitmap(imageRounded)
    }
    fun prepareDrawer(){
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_face -> {
                main_grid.visibility = View.VISIBLE
                color_btn_layout.visibility = View.GONE
                prepareAdapter(face)
            }
            R.id.nav_dial -> {
                main_grid.visibility = View.VISIBLE
                color_btn_layout.visibility = View.GONE
                prepareAdapter(dial)
            }
            R.id.nav_hand -> {
                main_grid.visibility = View.VISIBLE
                color_btn_layout.visibility = View.GONE
            }
            R.id.nav_color -> {
                main_grid.visibility = View.GONE
                color_btn_layout.visibility = View.VISIBLE
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
