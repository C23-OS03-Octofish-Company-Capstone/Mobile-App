package id.fishku.fishkuseller.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.DataItem
import id.fishku.fishkuseller.api.LoginResponse

class LoginViewModel: ViewModel() {

    private val _isLoading= MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginResponse= MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _loginResult = MutableLiveData<DataItem>()
    val loginResult : LiveData<DataItem> = _loginResult

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun getSellerData(){

    }

}