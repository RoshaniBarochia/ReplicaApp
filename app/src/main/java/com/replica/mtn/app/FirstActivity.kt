package com.replica.mtn.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.replica.mtn.app.databinding.ActivityFirstBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            PreferenceHelper.preferences[PreferenceHelper.FIRST] = true
            startActivity(Intent(this, SecondActivity::class.java))
            finish()
        }
        binding.btnSkip.setOnClickListener {
            PreferenceHelper.preferences[PreferenceHelper.SECOND] = true
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}