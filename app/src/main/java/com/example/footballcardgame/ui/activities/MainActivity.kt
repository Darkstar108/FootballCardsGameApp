package com.example.footballcardgame.ui.activities

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.footballcardgame.R
import com.example.footballcardgame.common.Constants
import com.example.footballcardgame.databinding.ActivityMainBinding
import com.example.footballcardgame.ui.fragments.PlayerListFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_player_list, R.id.nav_profile, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            val currentFragment = this.supportFragmentManager.currentNavigationFragment
            Log.d(Constants.LOG_TAG, "Current Fragment: ${currentFragment}")
            if(currentFragment is PlayerListFragment) {
                val alertDialogBuilder: AlertDialog.Builder = this.let {
                    AlertDialog.Builder(it)
                }

                alertDialogBuilder.setCancelable(false)
                alertDialogBuilder.setMessage("Do you want to Exit?")
                alertDialogBuilder.apply {
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            finish()
                        })
                    setNegativeButton("No",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                }
                val alert: AlertDialog = alertDialogBuilder.create()
                alert.show()
            }
            else {
                super.onBackPressed()
            }
        }
    }

    // Use extension to define a new property
    val FragmentManager.currentNavigationFragment: Fragment?
        get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()
}