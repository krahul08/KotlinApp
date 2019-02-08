package home.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import com.rahultech.homepage.R
import home.data.SearchItems
import home.data.SearchResponse
import home.presenter.SearchPresenter
import home.presenter.SearchPresenterImpl
import home.provider.SearchProviderImpl

class SearchActivity : AppCompatActivity(), SearchView {

    private var searchPresenter: SearchPresenter? = null

    internal var searchResultsList: RecyclerView? = null
    internal var searchBar: EditText? = null
    internal var searchImage: ImageView? = null
    internal var searchProgress: ProgressBar? = null
    private var context: Context? = null
    private var resultsListAdapter: SearchResultsListAdapter? = null
    private var searchResponse: SearchResponse? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        resultsListAdapter = SearchResultsListAdapter(this)
        searchPresenter = SearchPresenterImpl(this, SearchProviderImpl())
        context = this
        searchImage = findViewById(R.id.searchImage)
        searchBar = findViewById(R.id.searchBar)
        searchProgress = findViewById(R.id.searchProgress)
        searchResultsList = findViewById(R.id.searchResultsList)
        searchImage!!.setOnClickListener {
            val searchQuery = searchBar!!.text.toString()
            searchPresenter!!.getSearchResults(searchQuery)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            searchProgress!!.visibility = View.VISIBLE
        } else {
            searchProgress!!.visibility = View.GONE
        }
    }

    override fun showSearchResults(searchResponse: SearchResponse) {
        this.searchResponse = searchResponse
        //        Toast.makeText(this, ""+searchResponse.getItems().get(0).getOwner(), Toast.LENGTH_SHORT).show();
        resultsListAdapter!!.setData(searchResponse.items as MutableList<SearchItems>)
        val layoutManager = LinearLayoutManager(this)
        searchResultsList!!.layoutManager = layoutManager
        searchResultsList!!.setHasFixedSize(true)
        searchResultsList!!.adapter = resultsListAdapter
        resultsListAdapter!!.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        resultsListAdapter!!.notifyDataSetChanged()
    }



}
