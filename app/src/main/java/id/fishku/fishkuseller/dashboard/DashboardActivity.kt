@file:Suppress("DEPRECATION")

package id.fishku.fishkuseller.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashBinding : ActivityDashboardBinding

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private var isNavigationEnabled = true
    private val navigationDisableDuration = 1000L

    companion object{
        const val SELLER_ID = "seller_id"
        const val ALL_ORDER = "all_order"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashBinding.root)



        supportActionBar?.hide()

        navView = dashBinding.dashboardNavView
        navController = findNavController(R.id.container_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_inventory,
                R.id.navigation_messaging,
                R.id.navigation_transaction,
                R.id.navigation_user
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.setOnNavigationItemSelectedListener { item ->
            if (isNavigationEnabled) {
                // Disable navigation temporarily
                disableNavigation()
                Handler().postDelayed({ enableNavigation() }, navigationDisableDuration)

                // Navigate to the selected destination
                navController.navigate(item.itemId)
                true
            } else {
                false
            }
        }
    }

    private fun disableNavigation() {
        isNavigationEnabled = false
        navView.menu.setGroupEnabled(0, false) // Disable all menu items
    }

    private fun enableNavigation() {
        isNavigationEnabled = true
        navView.menu.setGroupEnabled(0, true) // Enable all menu items
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.CustomAlertDialogStyle))
            .setTitle(R.string.exit_app)
            .setMessage(R.string.exit_validation)
            .setPositiveButton(R.string.btn_yes) { _, _ ->
                finish()
            }
            .setNegativeButton(R.string.btn_no, null)
            .show()
    }
}