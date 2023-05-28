package com.jones.busfinder.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jones.busfinder.App
import com.jones.busfinder.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Toast.makeText(this,"Connecting to Database", Toast.LENGTH_LONG).show()

//        try {
//            Thread.sleep(2000)
//        }catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//        App.instance.isDataAvailable.observe(this) {
//            if (it!!) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                this.finish()
//            }
//        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }, 2000)
    }
}