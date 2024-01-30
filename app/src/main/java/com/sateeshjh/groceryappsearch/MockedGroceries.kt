package com.sateeshjh.groceryappsearch

import java.util.UUID

object MockedGroceries {

    val groceries = listOf(
        Grocery(
            namespace = "mocked_groceries",
            id = UUID.randomUUID().toString(),
            score = 1, // could be useful for a ranking based sorting logic
            itemTitle = "Apple Fuji",
            itemDesc = "Fresh Apples imported from Fuji",
            category = "Fruit",
            seller = "Mom and Pop Fruit Store",
            price = 10.0
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = UUID.randomUUID().toString(),
            score = 1,
            itemTitle = "Apple India",
            itemDesc = "Fresh Apples imported from India",
            category = "Fruit",
            seller = "Giant Supermarket Chain",
            price = 8.5
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = UUID.randomUUID().toString(),
            score = 1,
            itemTitle = "Apple Royal Gala US",
            itemDesc = "Fresh Apples imported from the US",
            category = "Fruit",
            seller = "Giant Supermarket Chain",
            price = 14.25
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = UUID.randomUUID().toString(),
            score = 1,
            itemTitle = "Potatoes",
            itemDesc = "Best used for French Fries",
            category = "Vegetable",
            seller = "Giant Supermarket Chain",
            price = 18.0
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = UUID.randomUUID().toString(),
            score = 1,
            itemTitle = "Cherry Tomatoes",
            itemDesc = "Best used for Salads",
            category = "Vegetable",
            seller = "Mom and Pop Fruit Store",
            price = 22.13
        ),
    )
}