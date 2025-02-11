package com.replica.mtn.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.replica.mtn.app.databinding.ActivitySecondBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            binding.btnNext.isVisible = false
            binding.btnSkip.isVisible = false
            binding.tvProceed.isVisible = true
        }
        binding.tvProceed.setOnClickListener {
            PreferenceHelper.preferences[PreferenceHelper.SECOND] = true
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.btnSkip.setOnClickListener {
            PreferenceHelper.preferences[PreferenceHelper.SECOND] = true
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}