package id.fishku.fishkuseller.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import id.fishku.fishkuseller.R
import id.fishku.fishkuseller.databinding.ActivityRegisterBinding

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
                    phone = "+62" + phone.substring(1)
                }else if(phone.startsWith("62")){
                    phone = "+62" + phone.substring(2)
                }
                inputValid = etUserName.error == null && etEmailRegister.error == null && etPhoneRegister.error == null &&
                        etPasswordRegister.error == null && etUserAddress.error == null && roles.isNotEmpty()
            }
            Log.d("RegisterActivtiy","name: $userName, email: $email,phone: $phone, password: $password, address: $address, roles: $roles")

            if(inputValid){
                viewModel.postRegister(userName, email, password, 12345678, address, roles)
            }else{
                Log.d("RegisterActivtiy","not OK")
            }
        }
        viewModel.registerResponse.observe(this){
            Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
        }
    }
}