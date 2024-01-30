package com.sateeshjh.groceryappsearch

import android.content.Context
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GrocerySearchManager(
    private val appContext: Context
) {

    private var session: AppSearchSession? = null

    suspend fun init() {
        withContext(Dispatchers.IO) {
            val sessionFuture = LocalStorage.createSearchSessionAsync(
                LocalStorage.SearchContext.Builder(
                    appContext,
                    "groceries"
                ).build()
            )
            val setSchemaRequest = SetSchemaRequest.Builder()
                .addDocumentClasses(Grocery::class.java)
                .build()

            session = sessionFuture.get()

            session?.setSchemaAsync(setSchemaRequest)
        }
    }
}