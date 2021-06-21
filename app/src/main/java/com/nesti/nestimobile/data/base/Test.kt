package com.nesti.nestimobile.data.base

import android.os.Bundle
import com.nesti.nestimobile.data.model.Ingredient

class Test {

    fun test() {
        var ingredientDao = IngredientDao()
        var myNullableIngredient:Ingredient? = null;
        var myIngredient:Ingredient =
            myNullableIngredient ?: Ingredient()
        if ( myNullableIngredient.name == "poivre"){

        }

    }
}

class Counted {
    companion object {
        var ammountCreated: Int = 0
    }

    init {
        ammountCreated++
    }
}

fun main(){
    val counted1 = Counted()
    val counted2 = Counted()
    val counted3 = Counted()
    println(Counted.ammountCreated)
}