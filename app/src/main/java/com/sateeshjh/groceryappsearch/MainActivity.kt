package com.sateeshjh.groceryappsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sateeshjh.groceryappsearch.ui.theme.GroceryAppSearchTheme

class MainActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel(GrocerySearchManager(applicationContext)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceryAppSearchTheme {
                val state = viewModel.state

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TextField(
                        value = state.searchQuery,
                        placeholder = {
                            Text("Search by item name, seller or category")
                        },
                        onValueChange = viewModel::onSearchQueryChange,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(state.groceries) { grocery ->
                            GroceryItem(
                                grocery = grocery,
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun GroceryItem(
        grocery: Grocery,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = grocery.itemTitle,
                    fontSize = 16.sp
                )
                Text(
                    text = "$${grocery.price}",
                    fontSize = 14.sp
                )
            }
            Text(
                text = grocery.itemDesc,
                fontSize = 10.sp
            )
            Text(
                text = "Sold at ${grocery.seller}",
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}