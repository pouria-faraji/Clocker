package com.blacksite.clocker.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.blacksite.clocker.R
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()

        Handler().postDelayed({
            val intent_welcome = Intent(this@SplashActivity,
                    MainActivity::class.java)
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
