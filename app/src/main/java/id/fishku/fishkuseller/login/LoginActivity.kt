package id.fishku.fishkuseller.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.fishku.fishkuseller.dashboard.DashboardActivity
import id.fishku.fishkuseller.databinding.ActivityLoginBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.register.RegisterActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")
class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var email : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        supportActionBar?.hide()

        val pref = LoginPref.getInstance(dataStore)
        val loginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        loginBinding.apply {
            btnLogin.setOnClickListener {
                email = etEmailLogin.text.toString().trim()
                password = etPasswordLogin.text.toString().trim()

                if (etEmailLogin.error == null && etPasswordLogin.error == null){
                    loginViewModel.postLogin(email, password)
                }else{
                    Toast.makeText(this@LoginActivity,"Failed Login", Toast.LENGTH_SHORT).show()
                }

            }

            btnToRegister.setOnClickListener {
                val register = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(register)
                finish()
            }
        }

        loginViewModel.loginResult.observe(this){
            loginViewModel.saveSellerId(it[0].id)
            val dashboard = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(dashboard)
            finish()
        }
    }
}