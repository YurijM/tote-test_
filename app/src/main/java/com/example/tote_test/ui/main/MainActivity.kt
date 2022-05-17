package com.example.tote_test.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tote_fifa_2022.utilits.AppPreferences
import com.example.tote_test.R
import com.example.tote_test.database.FirebaseRepository
import com.example.tote_test.database.REPOSITORY
import com.example.tote_test.database.UID
import com.example.tote_test.databinding.ActivityMainBinding
import com.example.tote_test.utils.APP_ACTIVITY
import com.example.tote_test.utils.START_YEAR
import com.google.android.material.navigation.NavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        AppPreferences.getPreference(this)
        AppPreferences.setAuth(false)

        initialization()

        setStartFragment()
    }

    private fun initialization() {
        initFirebase()

        drawerLayout = binding.drawerLayout

        navController = findNavController(R.id.mainContent)

        initAppBar()

        navView = binding.navView
        navView.setupWithNavController(navController)

        setHeader()
        setCopyright()

    }

    private fun initFirebase() {
        REPOSITORY = FirebaseRepository()
        UID = "null"
    }

    private fun initAppBar() {
        setSupportActionBar(binding.appBarMain.toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navGamblers,
                R.id.navRating,
                R.id.navPrognosis,
                R.id.navProfile,
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navLogin) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
        }
    }

    private fun setHeader() {
        val header = navView.getHeaderView(0)

        /*header.setOnClickListener {
           navController.navigate(R.id.navProfile)
           drawerLayout.closeDrawer(GravityCompat.START)
       }*/

        val image = header.findViewById<ImageView>(R.id.headerPhoto)
        image.setImageResource(R.drawable.mu)
        val nik = header.findViewById<TextView>(R.id.headerNik)
        nik.text = "MU"
        val name = header.findViewById<TextView>(R.id.headerName)
        name.text = "Мягков Юрий"
    }

    private fun setStartFragment() {
        if (UID == "null") {
            navController.navigate(R.id.navLogin)
        } else {
            navController.navigate(R.id.navGamblers)
        }
    }

    private fun setCopyright() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val copyright = binding.appBarMain.mainFooter.copyrightYear

        if (year != START_YEAR) {
            val strYear = "$START_YEAR-$year"
            copyright.text = strYear
        } else {
            copyright.text = START_YEAR.toString()
        }
    }

/*override fun onBackPressed() {
    val name = navController.currentDestination?.displayName ?: ""
    val idx = name.lastIndexOf(":id/").plus(4)

    if ((name.substring(idx).isNotEmpty()) and (name.substring(idx) != "navPrognosis")) {
        super.onBackPressed()
    } else {
        Toast.makeText(this, "12345", Toast.LENGTH_LONG).show()

        findNavController(R.id.splashContainer).popBackStack()
        finish()
    }
}*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        menu.findItem(R.id.action_admin).isVisible = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var result = true

        when (item.itemId) {
            R.id.action_exit -> finish()
            android.R.id.home -> result = super.onOptionsItemSelected(item)
        }
        return result
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}