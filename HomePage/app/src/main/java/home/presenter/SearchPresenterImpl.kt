package home.presenter

import home.SearchCallBack
import home.data.SearchResponse
import home.provider.SearchProvider
import home.view.SearchView

class SearchPresenterImpl(private val searchView: SearchView, private val searchProvider: SearchProvider) :
    SearchPresenter {

    override fun getSearchResults(searchedKeyword: String) {
        searchView.showProgress(true)
        searchProvider.getSearchResults(searchedKeyword, object : SearchCallBack {
            override fun onSuccess(searchResponse: SearchResponse) {
                if (searchResponse.isIncomplete_results) {
                    searchView.showProgress(false)
                    searchView.showSearchResults(searchResponse)
                } else {
                    searchView.showProgress(false)
                    searchView.showSearchResults(searchResponse)
                }

            }

            override fun onFailure() {


            }
        })

    }
}
