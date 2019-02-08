package home.view

import home.data.SearchResponse

interface SearchView {

    fun showProgress(show: Boolean)
    fun showSearchResults(searchResponse: SearchResponse)

}
