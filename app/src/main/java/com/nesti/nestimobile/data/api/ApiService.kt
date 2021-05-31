package com.nesti.nestimobile.data.api

import android.content.res.Resources
import com.nesti.nestimobile.data.model.*
import com.nesti.nestimobile.ui.main.view.MainActivity
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService  {
    val apiUrl = "http://10.0.2.2/php/nesti_administration/api";
    //val apiUrl = "https://temeta.com/nesti/administration/api";

    fun getRecipesForTag(idTag:Int): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("$apiUrl/recipesForTag/$idTag")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }
    fun getRecipesForPartialName(partialName:String): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("$apiUrl/recipesForPartialName/$partialName")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }

    fun getTags(): Single<List<Tag>> {
        return Rx2AndroidNetworking.get("$apiUrl/tags")
                .build()
                .getObjectListSingle(Tag::class.java)
    }

    fun getIngredientRecipes(idRecipe:Int): Single<List<IngredientRecipe>> {
        return Rx2AndroidNetworking.get("$apiUrl/ingredientRecipes/$idRecipe")
                .build()
                .getObjectListSingle(IngredientRecipe::class.java)
    }

    fun getParagraphs(idRecipe:Int): Single<List<Paragraph>> {
        return Rx2AndroidNetworking.get("$apiUrl/paragraphs/$idRecipe")
                .build()
                .getObjectListSingle(Paragraph::class.java)
    }
}