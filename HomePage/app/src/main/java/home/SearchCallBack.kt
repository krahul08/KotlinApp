package home

import home.data.SearchResponse

interface SearchCallBack {

    fun onSuccess(searchResponse: SearchResponse)
    fun onFailure()
}
