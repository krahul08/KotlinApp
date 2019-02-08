package home.provider

import com.google.gson.GsonBuilder
import home.SearchCallBack
import home.api.SearchApi
import home.data.SearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class SearchProviderImpl : SearchProvider {

    private val searchApi: SearchApi
    private var searchResponseCall: Call<SearchResponse>? = null

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES).build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        searchApi = retrofit.create(SearchApi::class.java)
    }

    override fun getSearchResults(searchedKeyword: String, searchCallBack: SearchCallBack) {
        searchResponseCall = searchApi.response_call(searchedKeyword)
        searchResponseCall!!.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                searchCallBack.onSuccess(response.body())
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                searchCallBack.onFailure()
                t.stackTrace
            }
        })

    }
}
