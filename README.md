# Grocery App Search

This is a sample grocery app that demonstrates the usage of Jetpack's AppSearch API for full-text highly performant on device search.

AppSearch uses two types of storage systems, LocalStorage and PlatformStorage. We're using LocalStorage in this example, where the index is app-specific and lives in the app's data directory. Whereas in a PlatformStorage supported app, the data is exposed and search is executed at a system wide level, a perfect example would be address book contacts that are stored on the central storage and could be indexed by several applications.

![Main Board](https://github.com/sateeshjhambani/GroceryAppSearch/assets/60574717/5492fc89-7491-423d-b506-345e4f88db53)

## Usage

Make your class known to AppSearch by annotating it with Document, this is similar to an Entity in a Room DB

```kotlin
@Document
data class Grocery(
    @Namespace
    val namespace: String,
    @Id
    val id: String,
    @Score
    val score: Int,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES
    )
    val itemTitle: String,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES
    )
    val itemDesc: String,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES
    )
    val category: String,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES
    )
    val seller: String,
    @DoubleProperty
    val price: Double
)
```
The score annotation (when used with RANKING_STRATEGY_DOCUMENT_SCORE) is gonna allow us to provide logical biases in our search ranking. In our example, smaller mom and pop stores have a higher score than large supermarket, thus groceries sold by mom and pop stores are going to ranked higher than their counterparts.

Initializing database with Coroutines instead of the built-in Future implementation
```kotlin
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
```

Inserting a few mocked grocery documents
```kotlin
suspend fun insertGroceries(groceries: List<Grocery>): Boolean {
        return withContext(Dispatchers.IO) {
            session?.putAsync(
                PutDocumentsRequest.Builder()
                    .addDocuments(groceries)
                    .build()
            )?.get()?.isSuccess == true
        }
    }
```

Full-text grocery search, in this example we're using score based ranking system, it could be based on creation timestamp, usage count or other factors.
```kotlin
suspend fun searchGroceries(query: String): List<Grocery> {
        return withContext(Dispatchers.IO) {
            val searchSpec = SearchSpec.Builder()
                .addFilterNamespaces("mocked_groceries")
                .setSnippetCount(10) // max search entries
                .setRankingStrategy(SearchSpec.RANKING_STRATEGY_DOCUMENT_SCORE)
                .build()

            val result = session?.search(
                query,
                searchSpec
            ) ?: return@withContext emptyList()

            val page = result.nextPageAsync.get()

            page.mapNotNull {
                if (it.genericDocument.schemaType == Grocery::class.java.simpleName) {
                    it.getDocument(Grocery::class.java)
                } else null
            }
        }
    }
```

[API Documentation](https://developer.android.com/develop/ui/views/search/appsearch)
