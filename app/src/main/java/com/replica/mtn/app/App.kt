package com.replica.mtn.app

import android.app.Application
import com.replica.mtn.app.pref.PreferenceHelper

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)

    }
}