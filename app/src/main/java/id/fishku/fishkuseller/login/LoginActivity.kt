package id.fishku.fishkuseller.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fishku.fishkuseller.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    private lateinit var email : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.apply {
            email = etEmailLogin.toString().trim()
            password = etPasswordLogin.toString().trim()

            btnLogin.setOnClickListener {

            }
        }
    }
}