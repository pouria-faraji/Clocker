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
    var splashThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar!!.hide()
        prefManager = PrefManager(App.appContext!!)

        splashThread = Thread{
            try {
                var waited = 0
                while (waited < 3000) {
                    Thread.sleep(100)
                    waited += 100
                }
                val mainIntent = Intent(this@SplashActivity,
                        MainActivity::class.java)
                startActivity(mainIntent)
                this.finish()
            }catch (e:InterruptedException){
                Log.e("logger", "Interrupted")
            }
        }

        splashThread!!.start()
//        Handler().postDelayed({
//
//        }, 3000)
    }

    fun startAnimation():Unit {

    }

    public override fun onPause() {
        super.onPause()
        Log.e("logger", "onPause")
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    public override fun onStop() {
        super.onStop()
        Log.e("logger", "onStop")
        if (splashThread!!.isAlive()) {
            splashThread!!.interrupt()
        }
    }
}
