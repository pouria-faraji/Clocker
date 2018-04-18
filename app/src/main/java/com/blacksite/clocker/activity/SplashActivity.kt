package com.blacksite.clocker.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.blacksite.clocker.R
import com.blacksite.clocker.application.App
import com.blacksite.clocker.application.PrefManager
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    private var prefManager: PrefManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()
        prefManager = PrefManager(App.appContext!!)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity,
                    MainActivity::class.java)
            prefManager!!.isFirstTimeLaunch = false
            startActivity(mainIntent)
            this.finish()
        }, 5000)
    }

    fun startAnimation():Unit {

    }

    public override fun onPause() {
        super.onPause()
        Log.e("logger", "onPause")
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
