package com.nesti.nestimobile.lib

import com.nesti.nestimobile.data.model.Ingredient

class ShoppingList {
    lateinit var ingredients : HashMap<Int, Ingredient>

    fun add(ingredient:Ingredient) {
        ingredients.put(ingredient.idIngredient, ingredient)
    }

    fun clear() {
        ingredients.clear()
    }
}