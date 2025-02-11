package com.replica.mtn.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.replica.mtn.app.adapter.CountryAdapter
import com.replica.mtn.app.databinding.CountryScreenBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set
import com.replica.mtn.app.utils.Loader

class CountrySelectActivity : AppCompatActivity(),OnItemClickListener {
    private val flags = intArrayOf(
        R.drawable.flag_ind,
        R.drawable.flag_france,
        R.drawable.flag_amr,
        R.drawable.flag_sun,
        R.drawable.flag_ind,
        R.drawable.flag_france,
        R.drawable.flag_amr,
        R.drawable.flag_sun,
        R.drawable.flag_ind,
        R.drawable.flag_france,
        R.drawable.flag_amr,
        R.drawable.flag_sun
        )
    private val symbol = intArrayOf(
        R.drawable.sym_ind,
        R.drawable.sym_france,
        R.drawable.sym_amr,
        R.drawable.img,
        R.drawable.sym_ind,
        R.drawable.sym_france,
        R.drawable.sym_amr,
        R.drawable.img,
        R.drawable.sym_ind,
        R.drawable.sym_france,
        R.drawable.sym_amr,
        R.drawable.img
        )
    private var loader: Loader? = null

    private var countries: Array<String>?= null
    private lateinit var binding: CountryScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CountryScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countries = resources.getStringArray(R.array.country_names)
        loader = Loader(this)


        binding.tvGetCountry.text = countries!![0]
        binding.imgImage.setImageResource(symbol[0])
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = CountryAdapter(countries!!, flags,this)
        binding.tvGetCountry.setOnClickListener {
            binding.rvCountries.isVisible = true

        }
        binding.tvProceed.setOnClickListener {
            loader?.start()
            Handler(Looper.getMainLooper()).postDelayed({
                loader?.stop()
            PreferenceHelper.preferences[PreferenceHelper.COUNTRY] = true
            PreferenceHelper.preferences[PreferenceHelper.COUNTRY_NAME] = binding.tvGetCountry.text.toString()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()},1500)
        }

    }

    override fun onItemClick(pos: Int) {
        binding.rvCountries.isVisible = false
        binding.tvGetCountry.text = countries!![pos]
        binding.imgFlag.setImageResource(flags[pos])
        binding.imgImage.setImageResource(symbol[pos])

    }


}