package com.replica.mtn.app

import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.replica.mtn.app.databinding.ActivityMainBinding
import com.replica.mtn.app.pref.PreferenceHelper
import com.replica.mtn.app.pref.PreferenceHelper.set

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: NavigationView = binding.navView
        binding.appBarMain.toolbarLayout.imgMenu.setOnClickListener {
            isOpened()
        }
        binding.appBarMain.toolbarLayout.tvTitle.setOnClickListener {
            isOpened()
        }
        animateButtonWidths(binding.appBarMain.toolbarLayout.btnHome)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController!!)
    }

    //open drawer
    private fun isOpened() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)

        }
    }

    override fun onResume() {
        super.onResume()
        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            // Check if the current destination is the first page
            when (destination.id) {
                R.id.setting -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.password -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.transaction_history -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.transaction_history_second -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.incentive_history -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.incentive_history_second -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.my_hierarchy -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }

                R.id.sales_report -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.sales_report_second -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.sales_report_third -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.deposit_history -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.my_hierarchy_second -> {
                    binding.appBarMain.appbar.isVisible = false
                    binding.drawerLayout.closeDrawers()
                }
                R.id.nav_home -> {
                    binding.appBarMain.appbar.isVisible = true
                    binding.appBarMain.toolbarLayout.inside.isVisible = false
                    animateButtonWidths(binding.appBarMain.toolbarLayout.btnHome)
                    setToolbar(0)
                }
                R.id.nav_gallery -> {
                    binding.appBarMain.appbar.isVisible = true
                    binding.appBarMain.toolbarLayout.inside.isVisible = true
                    animateButtonWidths(binding.appBarMain.toolbarLayout.btnCifStatus)
                    animateButtonWidthsInside(binding.appBarMain.toolbarLayout.tvTopUp)
                    setToolbar(1)
                }R.id.nav_slideshow -> {
                    binding.appBarMain.appbar.isVisible = true
                    binding.appBarMain.toolbarLayout.inside.isVisible = false
                    animateButtonWidths(binding.appBarMain.toolbarLayout.btnChange)
                    setToolbar(2)
                }
            }
        }
        //tab inside buttons
        binding.appBarMain.toolbarLayout.tvTopUp.setOnClickListener {
            animateButtonWidthsInside(binding.appBarMain.toolbarLayout.tvTopUp)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.topUp, null, popup)
        }
        binding.appBarMain.toolbarLayout.tvTran.setOnClickListener {
            animateButtonWidthsInside(binding.appBarMain.toolbarLayout.tvTran)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.transfer, null, popup)
        }
        binding.appBarMain.toolbarLayout.tvPur.setOnClickListener {
            animateButtonWidthsInside(binding.appBarMain.toolbarLayout.tvPur)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.purchase, null, popup)
        }
        //tab buttons click event
        binding.appBarMain.toolbarLayout.btnHome.setOnClickListener {
            animateButtonWidths(it)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.nav_home, null, popup)
        }
        binding.appBarMain.toolbarLayout.btnCifStatus.setOnClickListener {
            animateButtonWidths(it)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.nav_gallery, null, popup)
        }
        binding.appBarMain.toolbarLayout.btnChange.setOnClickListener {
            animateButtonWidths(it)
            val popup = NavOptions.Builder().setPopUpTo(navController!!.graph.id, true).build()
            navController?.navigate(R.id.nav_slideshow, null, popup)
        }

        val version=binding.navView.getHeaderView(0).findViewById<TextView>(R.id.version)
        try {
            val pInfo: PackageInfo =
                packageManager.getPackageInfo(packageName, 0)
            val version1: String = pInfo.versionName!!
            version.text = getString(R.string.version_n, version1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.btnSetting).setOnClickListener {
            navController?.navigate(R.id.setting)
        }
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.btnLogOut).setOnClickListener {
            PreferenceHelper.preferences[PreferenceHelper.LOGIN] = false
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            finish()
        }
    }
    private fun setToolbar(pos : Int){
        when(pos){
            0 ->{
                binding.appBarMain.toolbarLayout.btnHome.setImageResource(R.drawable.ic_home)
                binding.appBarMain.toolbarLayout.btnCifStatus.setImageResource(R.drawable.ic_home_data_grey)
                binding.appBarMain.toolbarLayout.btnChange.setImageResource(R.drawable.ic_home_note_grey)
            }
            1 ->{
                binding.appBarMain.toolbarLayout.btnHome.setImageResource(R.drawable.ic_home_grey)
                binding.appBarMain.toolbarLayout.btnCifStatus.setImageResource(R.drawable.ic_home_data)
                binding.appBarMain.toolbarLayout.btnChange.setImageResource(R.drawable.ic_home_note_grey)
            }
            2 ->{
                binding.appBarMain.toolbarLayout.btnHome.setImageResource(R.drawable.ic_home_grey)
                binding.appBarMain.toolbarLayout.btnCifStatus.setImageResource(R.drawable.ic_home_data_grey)
                binding.appBarMain.toolbarLayout.btnChange.setImageResource(R.drawable.ic_home_note)
            }
        }
    }

    private fun animateButtonWidths(selectedButton: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.appBarMain.toolbarLayout.buttonLayout)

        // Move the indicatorView to the center of the selected button
        constraintSet.connect(
            R.id.indicatorView,
            ConstraintSet.START,
            selectedButton.id,
            ConstraintSet.START
        )
        constraintSet.connect(
            R.id.indicatorView,
            ConstraintSet.END,
            selectedButton.id,
            ConstraintSet.END
        )
        constraintSet.connect(
            R.id.indicatorView,
            ConstraintSet.TOP,
            selectedButton.id,
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            R.id.indicatorView,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )


        TransitionManager.beginDelayedTransition(binding.appBarMain.toolbarLayout.buttonLayout)
        constraintSet.applyTo(binding.appBarMain.toolbarLayout.buttonLayout)
        //animateConstraintLayout(binding.toolbarLayout.buttonLayout,constraintSet,50)
    }
    private fun animateButtonWidthsInside(selectedButton: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.appBarMain.toolbarLayout.inside)

        // Move the indicatorView to the center of the selected button
        constraintSet.connect(
            R.id.indicatorView1,
            ConstraintSet.START,
            selectedButton.id,
            ConstraintSet.START
        )
        constraintSet.connect(
            R.id.indicatorView1,
            ConstraintSet.END,
            selectedButton.id,
            ConstraintSet.END
        )
        constraintSet.connect(
            R.id.indicatorView1,
            ConstraintSet.TOP,
            selectedButton.id,
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            R.id.indicatorView1,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )


        TransitionManager.beginDelayedTransition(binding.appBarMain.toolbarLayout.inside)
        constraintSet.applyTo(binding.appBarMain.toolbarLayout.inside)
        //animateConstraintLayout(binding.toolbarLayout.buttonLayout,constraintSet,50)
    }
}