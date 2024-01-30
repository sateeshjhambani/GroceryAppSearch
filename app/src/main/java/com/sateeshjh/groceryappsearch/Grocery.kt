package com.sateeshjh.groceryappsearch

import androidx.appsearch.annotation.Document
import androidx.appsearch.annotation.Document.DoubleProperty
import androidx.appsearch.annotation.Document.Id
import androidx.appsearch.annotation.Document.Namespace
import androidx.appsearch.annotation.Document.Score
import androidx.appsearch.annotation.Document.StringProperty
import androidx.appsearch.app.AppSearchSchema

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
