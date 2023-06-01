@file:Suppress("DEPRECATION")

package id.fishku.fishkuseller.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityRegisterBinding
import id.fishku.fishkuseller.login.LoginActivity

class RegisterActivity : AppCompatActivity(){

    private lateinit var regBinding: ActivityRegisterBinding
    private lateinit var userName: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var phone: String
    private lateinit var address: String
    private lateinit var roles: String
    private var inputValid: Boolean = false

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(regBinding.root)

        supportActionBar?.hide()

        regBinding.btnToLogin.setOnClickListener {
            val login = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(login)
            finish()
        }

        val adapter = ArrayAdapter.createFromResource(this,
            R.array.spinner_items, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        regBinding.spnRole.adapter = adapter
        regBinding.spnRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                roles = when (position) {
                    0 -> ""
                    else -> parent?.getItemAtPosition(position).toString()
                }
                Log.d("RegisterActivity", "Item Selected : $roles")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                roles = ""
            }
        }

        regBinding.btnRegister.setOnClickListener{
            regBinding.apply {
                userName = etUserName.text.toString().trim()
                email = etEmailRegister.text.toString().trim()
                password = etPasswordRegister.text.toString().trim()
                address = etUserAddress.text.toString().trim()
                phone = etPhoneRegister.text.toString().trim()
                if (phone.startsWith("0")) {
                    phone = "62" + phone.substring(1)
                }else if(phone.startsWith("62")){
                    phone = "62" + phone.substring(2)
                }
                inputValid = etUserName.error == null && etEmailRegister.error == null && etPhoneRegister.error == null &&
                        etPasswordRegister.error == null && etUserAddress.error == null && roles.isNotEmpty() && email.isNotEmpty()
                        && userName.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty()
            }
            Log.d("RegisterActivtiy","name: $userName, email: $email,phone: $phone, password: $password, address: $address, roles: $roles")

            if(inputValid){
                viewModel.postRegister(userName, email, password, phone.toLong(), address, roles)
            }else{
                AlertDialog.Builder(ContextThemeWrapper(this@RegisterActivity,R.style.CustomAlertDialogStyle))
                    .setTitle("Error")
                    .setMessage(R.string.invalid_input)
                    .setPositiveButton(R.string.btn_ok) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        viewModel.registerResponse.observe(this){
            Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
            val backToLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
            backToLogin.putExtra(LoginActivity.REGISTER_SUCCESS, true)
            startActivity(backToLogin)
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        regBinding.apply {

            userName = etUserName.text.toString().trim()
            email = etEmailRegister.text.toString().trim()
            password = etPasswordRegister.text.toString().trim()
            address = etUserAddress.text.toString().trim()
            phone = etPhoneRegister.text.toString().trim()

            etEmailRegister.clearFocus()
            etUserName.clearFocus()
            etPasswordRegister.clearFocus()
            etPhoneRegister.clearFocus()
            etUserAddress.clearFocus()

        }

        if(userName.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || address.isNotEmpty() || address.isNotEmpty() || roles.isNotEmpty()){
            AlertDialog.Builder(ContextThemeWrapper(this@RegisterActivity, R.style.CustomAlertDialogStyle))
                .setTitle(R.string.regis_cancel)
                .setMessage(R.string.regis_cancel_desc)
                .setPositiveButton(R.string.btn_yes) { _, _ ->
                    val backToLogin = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(backToLogin)
                    finish()
                }
                .setNegativeButton(R.string.btn_no, null)
                .show()
        }else{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}