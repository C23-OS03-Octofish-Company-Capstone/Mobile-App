package id.fishku.fishkuseller.inventory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.ApiConfig
import id.fishku.fishkuseller.api.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InventoryViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _fishInputResponse = MutableLiveData<String>()
    val fishInputResponse : LiveData<String> = _fishInputResponse

    fun postFish(sellerId: Long, name: String, price: Long, weight: Long, description: String, photo: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postFish(sellerId, name, weight, description, price, photo)
        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _fishInputResponse.value = response.body()?.message
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

    fun postEditFish(idFish: Long, name: String, price: Long, weight: Long, description: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().postEditFish(idFish, name, weight, description, price)
        client.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _fishInputResponse.value = response.body()?.message
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


    companion object{
        const val TAG = "InventoryViewModel"
    }
}