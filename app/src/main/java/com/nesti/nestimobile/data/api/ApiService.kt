package com.nesti.nestimobile.data.api

import com.nesti.nestimobile.data.model.*
import com.nesti.nestimobile.lib.ApplicationConfiguration
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiService(configuration: ApplicationConfiguration)  {
    val apiUrl = "http://10.0.2.2/php/nesti_administration/api";
    //val apiUrl = "https://temeta.com/nesti/administration/api";
    val configuration = configuration

    fun getRecipesForTag(idTag:Int): Single<List<Recipe>> {
        return  buildWithToken("$apiUrl/recipesForTag/$idTag")
                .getObjectListSingle(Recipe::class.java)
    }
    fun getRecipesForPartialName(partialName:String): Single<List<Recipe>> {
        return  buildWithToken("$apiUrl/recipesForPartialName/$partialName")
                .getObjectListSingle(Recipe::class.java)
    }

    fun getTags(): Single<List<Tag>> {
        return  buildWithToken("$apiUrl/tags")
                .getObjectListSingle(Tag::class.java)
    }

    fun getIngredientRecipes(idRecipe:Int): Single<List<IngredientRecipe>> {
        return  buildWithToken("$apiUrl/ingredientRecipes/$idRecipe")
                .getObjectListSingle(IngredientRecipe::class.java)
    }

    fun getParagraphs(idRecipe:Int): Single<List<Paragraph>> {
        return  buildWithToken("$apiUrl/paragraphs/$idRecipe")
                .getObjectListSingle(Paragraph::class.java)
    }

    private fun buildWithToken(url:String): Rx2ANRequest {
        return Rx2AndroidNetworking.get(url)
            .addQueryParameter("token", configuration.getSingle("api/@clientToken").stringValue)
            .build();
    }
}