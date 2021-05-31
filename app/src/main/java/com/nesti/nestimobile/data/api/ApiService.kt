package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.*
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService  {
    fun getRecipesForTag(idTag:Int): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/recipesForTag/${idTag}")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }
    fun getRecipesForPartialName(partialName:String): Single<List<Recipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/recipesForPartialName/${partialName}")
                .build()
                .getObjectListSingle(Recipe::class.java)
    }

    fun getTags(): Single<List<Tag>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/tags")
                .build()
                .getObjectListSingle(Tag::class.java)
    }

    fun getIngredientRecipes(idRecipe:Int): Single<List<IngredientRecipe>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/ingredientRecipes/${idRecipe}")
                .build()
                .getObjectListSingle(IngredientRecipe::class.java)
    }

    fun getParagraphs(idRecipe:Int): Single<List<Paragraph>> {
        return Rx2AndroidNetworking.get("http://10.0.2.2/php/nesti_administration/api/paragraphs/${idRecipe}")
                .build()
                .getObjectListSingle(Paragraph::class.java)
    }
}