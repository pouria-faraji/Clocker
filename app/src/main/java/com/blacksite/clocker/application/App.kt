package com.blacksite.clocker.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.blacksite.clocker.R
import com.blacksite.clocker.model.`object`.Dial
import com.blacksite.clocker.model.`object`.Face
import com.blacksite.clocker.model.`object`.Hand
import com.blacksite.clocker.model.db.*


import java.util.*

/**
 * Created by p.faraji on 4/17/2018.
 */
class App : Application() {
    private var prefManager: PrefManager? = null


    override fun onCreate() {
        super.onCreate()
        changeSystemLocaleToEN()
        App.appContext = applicationContext

        val helper = DaoMaster.DevOpenHelper(this, "clocker_DB")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()

        prefManager = PrefManager(App.appContext!!)

        if (prefManager!!.isFirstTimeLaunch) run {
            val database = Database()
            database.initialize()
            prefManager!!.isFirstTimeLaunch = false
        }
        var hand= Hand()
        var face= Face()
        var dial= Dial()
        Global.faceListasItem = face.loadFacesAsGridItem()
        Global.dialLisasItem = dial.loadDialsAsGridItem()
        Global.handListasItem = hand.loadHandsAsGridItem()
        Global.handList = hand.loadHands()
    }

    fun changeSystemLocaleToEN() {
        val languageToLoad = "en"
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    companion object {
        var daoSession: DaoSession? = null
        var appContext: Context? = null
            private set
    }

}