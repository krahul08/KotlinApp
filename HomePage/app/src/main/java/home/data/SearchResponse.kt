package home.data

class SearchResponse(val total_count: Int,
                     val isIncomplete_results: Boolean,
                     val items: List<SearchItems>)
