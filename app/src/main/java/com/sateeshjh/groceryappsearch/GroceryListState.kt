package com.sateeshjh.groceryappsearch

data class GroceryListState(
    val groceries: List<Grocery> = emptyList(),
    val searchQuery: String = ""
)
