package com.replica.mtn.app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.replica.mtn.app.pref.PreferenceHelper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Navigate to MainActivity after a delay
        val splashScreenDuration = 3000L // 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            if(PreferenceHelper.preferences.getBoolean(PreferenceHelper.SECOND,false))
                startActivity(Intent(this, MainActivity::class.java))
            else if(PreferenceHelper.preferences.getBoolean(PreferenceHelper.FIRST,false))
                startActivity(Intent(this, SecondActivity::class.java))
            else if(PreferenceHelper.preferences.getBoolean(PreferenceHelper.LOGIN,false))
                startActivity(Intent(this, FirstActivity::class.java))
            else if(PreferenceHelper.preferences.getBoolean(PreferenceHelper.COUNTRY,false))
                startActivity(Intent(this, LoginActivity::class.java))
            else
                startActivity(Intent(this, CountrySelectActivity::class.java))
            finish()
        }, splashScreenDuration)
    }
}