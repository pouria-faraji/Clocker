package com.blacksite.clocker.application

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.util.*

/**
 * Created by p.faraji on 4/17/2018.
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        changeSystemLocaleToEN()
        App.appContext = applicationContext

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

        var appContext: Context? = null
            private set
    }

}