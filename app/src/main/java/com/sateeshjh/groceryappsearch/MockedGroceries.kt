package com.sateeshjh.groceryappsearch

object MockedGroceries {

    val groceries = listOf(
        Grocery(
            namespace = "mocked_groceries",
            id = "1",
            score = 2, // useful for a ranking based sorting logic
            itemTitle = "Apple Fuji",
            itemDesc = "Fresh Apples imported from Fuji",
            category = "Fruit",
            seller = "Mom and Pop Fruit Store",
            price = 10.0
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = "2",
            score = 1,
            itemTitle = "Apple India",
            itemDesc = "Fresh Apples imported from India",
            category = "Fruit",
            seller = "Giant Supermarket Chain",
            price = 8.5
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = "3",
            score = 1,
            itemTitle = "Apple Royal Gala US",
            itemDesc = "Fresh Apples imported from the US",
            category = "Fruit",
            seller = "Giant Supermarket Chain",
            price = 14.25
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = "4",
            score = 1,
            itemTitle = "Potatoes",
            itemDesc = "Best used for French Fries",
            category = "Vegetable",
            seller = "Giant Supermarket Chain",
            price = 18.0
        ),
        Grocery(
            namespace = "mocked_groceries",
            id = "5",
            score = 2,
            itemTitle = "Cherry Tomatoes",
            itemDesc = "Best used for Salads",
            category = "Vegetable",
            seller = "Mom and Pop Fruit Store",
            price = 22.13
        ),
    )
}