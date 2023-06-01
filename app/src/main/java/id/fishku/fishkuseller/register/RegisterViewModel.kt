package id.fishku.fishkuseller.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.ApiConfig
import id.fishku.fishkuseller.api.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _registerResponse = MutableLiveData<String>()
    val registerResponse: LiveData<String> = _registerResponse

    companion object{
        private const val TAG = "RegisterViewModel"
    }

    fun postRegister(name: String, email: String, password: String, phone: Long, location: String, roles: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postRegister(name, email, password, phone, location, roles)
        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _registerResponse.value = response.body()?.message
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

}