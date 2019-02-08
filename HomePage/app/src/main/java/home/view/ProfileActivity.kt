package home.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.rahultech.homepage.R
import home.data.OwnerData
import home.data.SearchItems
import home.data.SearchResponse

class ProfileActivity : AppCompatActivity() {


//    @BindView(R.id.fullNameEditText)
    internal var fullNameEditText: EditText? = null
//    @BindView(R.id.ownerEditText)
    internal var ownerEditText: EditText? = null
//    @BindView(R.id.descriptionEditText)
    internal var descriptionEditText: EditText? = null
//    @BindView(R.id.button)
    internal var button: Button? = null


    private var itemId: Int = 0
    private var fullName: String? = null
    private var owner: String? = null
    private var description: String? = null
    private var itemPosition: Int = 0
    private val searchResponse: SearchResponse? = null
    private var resultsListAdapter: SearchResultsListAdapter? = null
    internal lateinit var searchItems: SearchItems
    private var searchActivity: SearchActivity? = null
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        context = applicationContext
        searchActivity = SearchActivity()

        fullNameEditText = findViewById(R.id.fullNameEditText)
        ownerEditText = findViewById(R.id.ownerEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        button = findViewById(R.id.button)

        resultsListAdapter = SearchResultsListAdapter(this)
        itemId = intent.getIntExtra("itemId", 0)
        fullName = intent.getStringExtra("fullName")
        owner = intent.getStringExtra("owner")
        description = intent.getStringExtra("description")
        itemPosition = intent.getIntExtra("itemPosition", 0)

        fullNameEditText!!.setText(fullName)
        ownerEditText!!.setText(owner)
        descriptionEditText!!.setText(description)


        button!!.setOnClickListener {
            val fullName = fullNameEditText!!.text.toString()
            val owner = ownerEditText!!.text.toString()
            val itemDescription = descriptionEditText!!.text.toString()

            val ownerData = OwnerData(owner)
            searchItems = SearchItems(
                itemId,
                fullName,
                ownerData,
                itemDescription
            )
            resultsListAdapter!!.UpdateData(itemPosition, searchItems)
            resultsListAdapter!!.notifyDataSetChanged()
            onBackPressed()
        }


    }
}
