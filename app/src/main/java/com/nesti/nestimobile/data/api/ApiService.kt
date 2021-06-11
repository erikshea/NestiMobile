package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.*
import com.nesti.nestimobile.lib.ApplicationConfiguration
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService(configuration: ApplicationConfiguration)  {
    val configuration = configuration


    fun getRecipesForTag(idTag:Int): Single<List<Recipe>> {
        return  buildWithToken("recipesForTag/$idTag")
                .getObjectListSingle(Recipe::class.java)
    }
    fun getRecipesForPartialName(partialName:String): Single<List<Recipe>> {
        return  buildWithToken("recipesForPartialName/$partialName")
                .getObjectListSingle(Recipe::class.java)
    }

    fun getTags(): Single<List<Tag>> {
        return  buildWithToken("tags")
                .getObjectListSingle(Tag::class.java)
    }

    fun getIngredientRecipes(idRecipe:Int): Single<List<IngredientRecipe>> {
        return  buildWithToken("ingredientRecipes/$idRecipe")
                .getObjectListSingle(IngredientRecipe::class.java)
    }

    fun getParagraphs(idRecipe:Int): Single<List<Paragraph>> {
        return  buildWithToken("paragraphs/$idRecipe")
                .getObjectListSingle(Paragraph::class.java)
    }

    private fun buildWithToken(action:String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(configuration.getNode("api/@url").stringValue + "/$action")
            .addQueryParameter("token", configuration.getNode("api/@clientToken").stringValue)
            .build();
    }
}