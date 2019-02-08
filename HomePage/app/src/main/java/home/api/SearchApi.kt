package home.api

import home.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/repositories")
    fun response_call(
        @Query("q") searchedKeyword: String
    ): Call<SearchResponse>


}
