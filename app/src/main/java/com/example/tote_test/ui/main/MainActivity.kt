package com.example.tote_test.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tote_fifa_2022.utilits.AppPreferences
import com.example.tote_test.R
import com.example.tote_test.databinding.ActivityMainBinding
import com.example.tote_test.utils.*
import com.google.android.material.navigation.NavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var vmMain: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmMain = ViewModelProvider(this)[MainViewModel::class.java]

        APP_ACTIVITY = this

        AppPreferences.getPreference(this)
        AppPreferences.setAuth(false)

        initialization()
    }

    private fun initialization() {
        drawerLayout = binding.drawerLayout

        navController = findNavController(R.id.mainContent)

        initAppBar()

        navView = binding.navView
        navView.setupWithNavController(navController)

        vmMain.getGambler()

        observeGambler()

        setCopyright()

        setStartFragment()
    }

    private fun observeGambler() = vmMain.currentGambler.observe(this) {
        GAMBLER = it
        setHeader()
    }

    private fun setStartFragment() {
        //?????? ?????????????? ?????????????????????? ?????????????????? ???????????????????? ??????????????????
        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)

        navGraph.setStartDestination(START_FRAGMENT)
        navController.graph = navGraph

        if (START_FRAGMENT == R.id.navProfile) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)

            AppPreferences.setAuth(true)

            showToast("?????? ?????????????? ?????? ???????? ?????????????? ???????????? ???????? ??????????????????")
        } else if (START_FRAGMENT == R.id.navGamblers)
            AppPreferences.setAuth(true)
    }

    private fun initAppBar() {
        setSupportActionBar(binding.appBarMain.toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navGamblers,
                R.id.navRating,
                R.id.navPrognosis,
                //R.id.navProfile,
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navLogin
                || (destination.id == R.id.navProfile && !checkProfile(GAMBLER))
            ) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setHomeButtonEnabled(false)
            }
        }
    }

    private fun setHeader() {
        val header = navView.getHeaderView(0)

        header.setOnClickListener {
           navController.navigate(R.id.navProfile)
           drawerLayout.closeDrawer(GravityCompat.START)
       }

        val image = header.findViewById<ImageView>(R.id.headerPhoto)
        image.setImageResource(R.drawable.user_white)

        val nik = header.findViewById<TextView>(R.id.headerNik)
        nik.text = GAMBLER.nickname

        val name = header.findViewById<TextView>(R.id.headerName)
        name.text = "${GAMBLER.family} ${GAMBLER.name}"
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

/*private fun setStartFragment() {
    *//*if (UID == "null")
            navController.navigate(R.id.navLogin)
        else
            navController.navigate(R.id.navGamblers)*//*

        //?????? ?????????????? ?????????????????????? ?????????????????? ???????????????????? ??????????????????
        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)

        if (UID == "null") {
            navGraph.setStartDestination(R.id.navLogin)
            navController.graph = navGraph
            //navController.navigate(R.id.navLogin)
        } else if (!checkProfile()) {
            navGraph.setStartDestination(R.id.navProfile)
            navController.graph = navGraph

            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)

            AppPreferences.setAuth(true)

            showToast("?????? ?????????????? ?????? ???????? ?????????????? ???????????? ???????? ??????????????????")
        } else {
            navGraph.setStartDestination(R.id.navGamblers)
            navController.graph = navGraph

            AppPreferences.setAuth(true)
        }*/

/*REF_DB_ROOT.child(NODE_GAMBLERS).addValueEventListener(object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        *//*val value = snapshot.getValue(GamblerModel::class.java) ?: GamblerModel()
                    showToast(value.nickname)*//*
                    Log.i("qwerty", snapshot.value.toString())

                    val gambler: List<GamblerModel> = snapshot.children.map { it ->
                        it.getValue(GamblerModel::class.java)!!
                    }

                    Log.i("qwerty", gambler.toString())
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })*/
/*REF_DB_ROOT.child(NODE_GAMBLERS).child(UID).addListenerForSingleValueEvent(object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        setHeader(snapshot.getValue(GamblerModel::class.java) ?: GamblerModel())
    }

    override fun onCancelled(error: DatabaseError) {

    }
})*/
/*REF_DB_ROOT.child(NODE_GAMBLERS).child(UID).addValueEventListener(object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        setHeader(snapshot.getValue(GamblerModel::class.java) ?: GamblerModel())
    }

    override fun onCancelled(error: DatabaseError) {

    }
})*/

/*else {
        navGraph.setStartDestination(R.id.navGamblers)
        navController.graph = navGraph
        //navController.navigate(R.id.navGamblers)

        AppPreferences.setAuth(true)
    }*/
//}

/*private fun checkProfile(): Boolean =
    !(GAMBLER.nickname.isEmpty()
            || GAMBLER.name.isEmpty()
            || GAMBLER.family.isEmpty()
            || GAMBLER.gender.isEmpty()
            || GAMBLER.photoUrl.isEmpty()
            || GAMBLER.photoUrl == "empty"
            )*/

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
            R.id.action_exit -> {
                vmMain.signOut()
                AppPreferences.setAuth(false)
                finish()
            }
            android.R.id.home -> result = super.onOptionsItemSelected(item)
        }
        return result
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}