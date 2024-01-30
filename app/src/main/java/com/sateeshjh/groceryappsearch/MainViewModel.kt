package com.sateeshjh.groceryappsearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val grocerySearchManager: GrocerySearchManager
): ViewModel() {

    var state by mutableStateOf(GroceryListState())
        private set

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            grocerySearchManager.init()

            grocerySearchManager.insertGroceries(MockedGroceries.groceries)
        }
    }

    fun onSearchQueryChange(query: String) {
        state = state.copy(searchQuery = query)

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L) // debounce effect, only executes search if user hasn't typed anything for 500L
            val filteredGroceries = grocerySearchManager.searchGroceries(query)
            state = state.copy(groceries = filteredGroceries)
        }
    }

    override fun onCleared() {
        grocerySearchManager.closeSession()
        super.onCleared()
    }
}