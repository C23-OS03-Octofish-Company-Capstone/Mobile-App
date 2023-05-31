@file:Suppress("DEPRECATION")

package id.fishku.fishkuseller.dashboard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityDashboardBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.login.LoginViewModel
import id.fishku.fishkuseller.login.ViewModelFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashBinding : ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashBinding.root)



        supportActionBar?.hide()

        val navView: BottomNavigationView = dashBinding.dashboardNavView
        val navController = findNavController(R.id.container_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_inventory,
                R.id.navigation_messaging,
                R.id.navigation_transaction,
                R.id.navigation_user
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
}