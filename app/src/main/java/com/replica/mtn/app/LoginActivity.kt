package com.replica.mtn.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.replica.mtn.app.databinding.ActivityLoginBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.get
import com.replica.mtn.app.pref.PreferenceHelper.set
import com.replica.mtn.app.utils.Loader

class LoginActivity : AppCompatActivity() {
    private var loader: Loader? = null

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loader = Loader(this)

        binding.btnLogin.setOnClickListener {
            if(binding.tvGetCountry.text.isNotEmpty() && binding.tvGetLang.text.isNotEmpty()) {
                if (binding.tvGetCountry.text.length == 10) {
                    if (binding.tvGetLang.text.toString().length == 4) {
                        loader?.start()
                        Handler(Looper.getMainLooper()).postDelayed({
                            loader?.stop()
                            PreferenceHelper.preferences[PreferenceHelper.LOGIN] = true
                            if (PreferenceHelper.preferences[PreferenceHelper.SECOND, false])
                                startActivity(Intent(this, MainActivity::class.java))
                            else
                                startActivity(Intent(this, FirstActivity::class.java))
                            finish()
                        },2000)
                    } else
                        Toast.makeText(this, "Pin length should be 4", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "MSISDN no length should be 10", Toast.LENGTH_SHORT).show()
                }

            }else
                Toast.makeText(this, "Fields are not empty", Toast.LENGTH_SHORT).show()

        }
    }

}