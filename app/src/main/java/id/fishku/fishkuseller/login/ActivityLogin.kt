package id.fishku.fishkuseller.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {

    private lateinit var loginBinding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.apply{

        }

    }
}