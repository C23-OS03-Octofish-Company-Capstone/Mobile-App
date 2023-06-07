package id.fishku.fishkuseller.freshness

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fishku.fishkuseller.api.ApiConfig
import id.fishku.fishkuseller.api.DetectionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class FishDetectionViewModel: ViewModel() {

    private var fishEyeCall: Call<DetectionResponse>? = null
    private var fishGillCall: Call<DetectionResponse>? = null

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _detectionResult = MutableLiveData<String>()
    val detectionResult: LiveData<String> = _detectionResult


    companion object {
        const val TAG = "FishDetectionViewModel"
    }

    fun postFishEye(photo: MultipartBody.Part){
        _isLoading.value = true
        val client = ApiConfig.getEyeService().postFishEye(photo)
        fishEyeCall = client
        client.enqueue(object: Callback<DetectionResponse> {
            override fun onResponse(
                call: Call<DetectionResponse>,
                response: Response<DetectionResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null){
                    _detectionResult.value = response.body()?.prediction
                    Log.d(TAG, _detectionResult.value.toString())
                    Log.d(TAG, response.toString())
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun postFishGill(photo: MultipartBody.Part){
        _isLoading.value = true
        val client = ApiConfig.getGillService().postFishGills(photo)
        fishGillCall = client
        client.enqueue(object: Callback<DetectionResponse> {
            override fun onResponse(
                call: Call<DetectionResponse>,
                response: Response<DetectionResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null){
                    _detectionResult.value = response.body()?.prediction
                    Log.d(TAG, _detectionResult.value.toString())
                    Log.d(TAG, response.toString())
                }else{
                    Log.e(TAG, "onFailure: ${response.code()} ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<DetectionResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun cancelFishEye() {
        fishEyeCall?.cancel()
        _isLoading.value = false
    }

    fun cancelFishGill() {
        fishGillCall?.cancel()
        _isLoading.value = false
    }
}