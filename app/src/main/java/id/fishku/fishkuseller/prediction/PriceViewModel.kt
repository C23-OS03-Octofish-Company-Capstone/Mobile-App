package id.fishku.fishkuseller.prediction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.ApiConfig
import id.fishku.fishkuseller.api.PriceResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceViewModel: ViewModel() {

    private var priceEceranCall: Call<PriceResponse>? = null
    private var priceGrosirCall: Call<PriceResponse>? = null

    private val _priceEceran = MutableLiveData<List<Double>>()
    val priceEceran : LiveData<List<Double>> = _priceEceran

    private val _priceAvail = MutableLiveData<Boolean>()
    val priceAvail : LiveData<Boolean> = _priceAvail

    private val _priceGrosir = MutableLiveData<List<Double>>()
    val priceGrosir : LiveData<List<Double>> = _priceGrosir

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getPriceEceran(request: RequestBody) {
        _isLoading.value = true
        val client = ApiConfig.getPriceService().getPricePredict(request)
        priceEceranCall = client
        client.enqueue(object: Callback<PriceResponse>{
            override fun onResponse(call: Call<PriceResponse>, response: Response<PriceResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _priceEceran.value = response.body()?.predictedPrice
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<PriceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getPriceGrosir(request: RequestBody) {
        _isLoading.value = true
        val client = ApiConfig.getPriceService().getPricePredict(request)
        priceGrosirCall = client
        client.enqueue(object: Callback<PriceResponse>{
            override fun onResponse(call: Call<PriceResponse>, response: Response<PriceResponse>) {
                _isLoading.value = false
                val responseBody = response.body()
                if(response.isSuccessful && responseBody != null) {
                    _priceGrosir.value = response.body()?.predictedPrice
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<PriceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun isPriceCollected(grosir: Boolean = false, eceran: Boolean = false){
        _priceAvail.value = grosir && eceran
    }

    companion object{
        private const val TAG = "PriceViewModel"
    }


}