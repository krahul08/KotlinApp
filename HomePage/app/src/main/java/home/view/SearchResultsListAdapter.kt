package home.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.rahultech.homepage.R
import home.data.SearchItems
import kotlinx.android.synthetic.main.search_results_list_layout.view.*

class SearchResultsListAdapter(
    private val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var layoutInflater: LayoutInflater? = null



    init {
        if (context != null) {
            layoutInflater = LayoutInflater.from(this.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsListHolder {

        val view = layoutInflater!!.inflate(R.layout.search_results_list_layout, parent, false)

        return SearchResultsListHolder(view)
    }

    fun setData(listData: MutableList<SearchItems>) {
        searchItems = listData
    }


    fun UpdateData(position: Int, search: SearchItems) {
        searchItems!![position] = search
        notifyItemChanged(position)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val searchResultsListHolder = holder as SearchResultsListHolder
        val searchedListData = searchItems!![position]
        searchResultsListHolder.ownerTextView!!.text = searchedListData.owner!!.login
        searchResultsListHolder.fullNameTextView!!.text = searchedListData.full_name
        searchResultsListHolder.descriptionTextView!!.text = searchedListData.description

        searchResultsListHolder.itemsCard!!.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("itemId", searchItems!![position].id)
            intent.putExtra("fullName", searchItems!![position].full_name)
            intent.putExtra("owner", searchItems!![position].owner!!.login)
            intent.putExtra("description", searchItems!![position].description)
            intent.putExtra("itemPosition", searchResultsListHolder.adapterPosition)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return searchItems!!.size
    }

    inner class SearchResultsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val descriptionTextView = itemView.descriptionTextView
        val fullNameTextView = itemView.fullNameTextView
        val ownerTextView = itemView.ownerTextView
        val itemsCard = itemView.itemsCard



    }

    companion object {
        private var searchItems: MutableList<SearchItems>? = null
    }
}
