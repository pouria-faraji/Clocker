package com.blacksite.clocker.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
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
        }
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