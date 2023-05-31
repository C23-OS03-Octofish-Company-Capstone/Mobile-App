package id.fishku.fishkuseller.notification

import androidx.lifecycle.ViewModel


class NotificationViewModel: ViewModel() /**{

    private val _listNews = MutableLiveData<List<ArticlesItem>>()
    val listNews : LiveData<List<ArticlesItem>> = _listNews

    companion object {
        private const val TAG = "UserDetailViewModel"
    }

    fun setNewsData(){
        val client = ApiConfig.getApiService().getNews("Fish","63105b015eca4d5db6c3858e5f396a50")
        client.enqueue(object: Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful){
                    _listNews.value = response.body()?.articles
                }else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

}**/