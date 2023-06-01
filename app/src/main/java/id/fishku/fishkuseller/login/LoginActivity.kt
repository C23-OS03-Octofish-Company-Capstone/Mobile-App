package id.fishku.fishkuseller.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.dashboard.DashboardActivity
import id.fishku.fishkuseller.databinding.ActivityLoginBinding
import id.fishku.fishkuseller.datastore.LoginPref
import id.fishku.fishkuseller.register.RegisterActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")
class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var email : String
    private lateinit var password : String

    companion object {
        const val REGISTER_SUCCESS = "register_success"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        supportActionBar?.hide()

        val pref = LoginPref.getInstance(dataStore)
        val loginViewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        val registerSuccess = intent.getBooleanExtra(REGISTER_SUCCESS, false)
        if (registerSuccess) {
            android.app.AlertDialog.Builder(ContextThemeWrapper(this, R.style.CustomAlertDialogStyle))
                .setTitle(R.string.register_success)
                .setMessage(R.string.register_success_desc)
                .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        loginBinding.apply {
            btnLogin.setOnClickListener {
                email = etEmailLogin.text.toString().trim()
                password = etPasswordLogin.text.toString().trim()

                if (etEmailLogin.error == null && etPasswordLogin.error == null && email.isNotEmpty() && password.isNotEmpty()){
                    loginViewModel.postLogin(email, password)
                }else{
                    AlertDialog.Builder(ContextThemeWrapper(this@LoginActivity,R.style.CustomAlertDialogStyle))
                        .setTitle("Error")
                        .setMessage(R.string.invalid_input)
                        .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
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
            dashboard.putExtra(DashboardActivity.SELLER_ID, it[0].id)
            startActivity(dashboard)
            finish()
        }

        loginViewModel.isLoading.observe(this){
            setLoading(it)
        }
    }

    private fun setLoading(it: Boolean) {
        if(it){
            loginBinding.pbLoading.visibility = View.VISIBLE
        }else{
            loginBinding.pbLoading.visibility = View.GONE
        }
    }
}