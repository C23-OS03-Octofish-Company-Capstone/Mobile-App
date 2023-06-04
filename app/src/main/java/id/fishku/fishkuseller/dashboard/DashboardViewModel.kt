package id.fishku.fishkuseller.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel: ViewModel() {

    companion object{
        private const val TAG = "DashboardViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _sellerProfile = MutableLiveData<List<ProfileItem>>()
    val sellerProfile : LiveData<List<ProfileItem>> = _sellerProfile

    private val _sellerInventory = MutableLiveData<List<InventoryItem>>()
    val sellerInventory : LiveData<List<InventoryItem>> = _sellerInventory

    private val _deleteResponse = MutableLiveData<String>()
    val deleteResponse : LiveData<String> = _deleteResponse

    fun getSellerData(it: Long) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProfile(it)
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _sellerProfile.value = response.body()?.data
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getInventory(it: Long) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getInventory(it)
        client.enqueue(object : Callback<InventoryResponse>{
            override fun onResponse(
                call: Call<InventoryResponse>,
                response: Response<InventoryResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _sellerInventory.value = response.body()?.data
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun deleteItem(it: Long){
        _isLoading.value = true
        val client = ApiConfig.getApiService().deleteFish(it)
        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _deleteResponse.value = response.body()?.message
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

    fun searchInventory(idSeller: Long, name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchInventory(idSeller, name)
        client.enqueue(object : Callback<InventoryResponse>{
            override fun onResponse(
                call: Call<InventoryResponse>,
                response: Response<InventoryResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _sellerInventory.value = response.body()?.data
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<InventoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }


}