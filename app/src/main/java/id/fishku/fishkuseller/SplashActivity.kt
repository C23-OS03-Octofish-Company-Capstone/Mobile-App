package id.fishku.fishkuseller

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.fishku.fishkuseller.dashboard.DashboardActivity
import id.fishku.fishkuseller.databinding.ActivitySplashBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.login.LoginActivity
import id.fishku.fishkuseller.login.LoginViewModel
import id.fishku.fishkuseller.login.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding
    private  var isLogged: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(splashBinding.root)

        supportActionBar?.hide()

        val pref = LoginPref.getInstance(dataStore)
        val loginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        val dashboard = Intent(this@SplashActivity, DashboardActivity::class.java)
        val login = Intent(this@SplashActivity, LoginActivity::class.java)

        loginViewModel.getSellerId().observe(this) {
            isLogged = it != -1L
            if(isLogged){
                dashboard.putExtra(DashboardActivity.SELLER_ID, it)
            }
            Log.d("SplashActivity", it.toString())
        }



        splashBinding.imageView.alpha = 0f
        splashBinding.fisku.alpha = 0f
        splashBinding.sellerapp.alpha = 0f
        splashBinding.fisku.animate().setDuration(2000).alpha(1f)
        splashBinding.sellerapp.animate().setDuration(2000).alpha(1f)
        splashBinding.imageView.animate().setDuration(2000).alpha(1f).withEndAction {
            // Check if the user is logged in and start the appropriate activity
            if(isLogged){
                startActivity(dashboard)
            }else {
                startActivity(login)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}