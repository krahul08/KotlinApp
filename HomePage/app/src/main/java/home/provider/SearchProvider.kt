package home.provider

import home.SearchCallBack

interface SearchProvider {

    fun getSearchResults(searchedKeyword: String, searchCallBack: SearchCallBack)

}
