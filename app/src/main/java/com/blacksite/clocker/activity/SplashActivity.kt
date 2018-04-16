package com.blacksite.clocker.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blacksite.clocker.R
import java.util.*
import java.util.concurrent.Executors
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()

    }

    fun startAnimation():Unit {

    }

    inline fun Timer.schedule(
            delay: Long,
            crossinline action: TimerTask.() -> Unit
    ): TimerTask {
        val task = timerTask(action)
        schedule(task, delay)
        return task
    }
    public override fun onPause() {
        super.onPause()
        Log.e("logger", "onPause")
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
