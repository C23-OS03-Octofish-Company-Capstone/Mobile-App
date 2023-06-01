package id.fishku.fishkuseller.login

import android.util.Log
import androidx.lifecycle.*
import id.fishku.fishkuseller.api.*
import id.fishku.fishkuseller.datastore.LoginPref
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: LoginPref): ViewModel() {

    private val _isLoading= MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginResponse= MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _loginResult = MutableLiveData<List<DataItem>>()
    val loginResult : LiveData<List<DataItem>> = _loginResult


    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun postLogin(email: String, password: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postLogin(email, password)
        client.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _loginResponse.value = response.body()
                    _loginResult.value = response.body()?.data
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

    }

    fun getSellerId(): LiveData<Int>{
        return pref.getSellerId().asLiveData()
    }

    fun saveSellerId(sellerId: Int){
        viewModelScope.launch {
            pref.saveSellerId(sellerId)
        }
    }

    fun removeSellerId(){
        viewModelScope.launch {
            pref.removeSellerId()
        }
    }



}

class ViewModelFactory(private val pref: LoginPref): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(pref) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}