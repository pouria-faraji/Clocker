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
import com.blacksite.clocker.widget.MyWidgetProvider


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var adapter: ItemAdapter? = null
    var faceList = ArrayList<Item>()
    private var prefManager: PrefManager? = null
    var face = Face()

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
        prepareAdapter()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Your widget has been created.", Snackbar.LENGTH_LONG)
                    .setAction("Widget", null).show()
            val appWidgetManager = AppWidgetManager.getInstance(this)
            appWidgetManager.updateAppWidget(thisWidget, remoteViews)

        }
    }
    override fun onResume() {
        super.onResume()
        prepareData()
    }

    fun prepareData(){
        var currentFacePosition = prefManager!!.facePosition
        adapter!!.makeAllUnselect(currentFacePosition)
        adapter!!.notifyDataSetChanged()

        clock_face_imageview.setImageResource(faceList[currentFacePosition].image!!)
        updateWidget(currentFacePosition)

    }
    fun prepareAdapter(){
        faceList = face.loadFacesAsGridItem()

        adapter = ItemAdapter(this, faceList)

        main_grid.numColumns = Constants.NUMBER_ITEMS_EACH_ROW
        main_grid.adapter = adapter

        main_grid.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            adapter!!.makeAllUnselect(position)
            adapter!!.notifyDataSetChanged()

            clock_face_imageview.setImageResource(faceList[position].image!!)
            prefManager!!.facePosition = position

            updateWidget(position)
        }
    }
    fun updateWidget(facePosition:Int){
        remoteViews = RemoteViews(this.packageName, R.layout.widget)
        thisWidget = ComponentName(this, MyWidgetProvider::class.java)
        remoteViews!!.setImageViewResource(R.id.clock_face_imageview_widget, faceList[facePosition].image!!)
    }
    fun prepareUI(){
        clock_face_imageview.layoutParams.height = Global.getAppWidth()/2
        clock_face_imageview.layoutParams.width = Global.getAppWidth()/2

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

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
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
            }
            R.id.nav_slideshow -> {
            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
