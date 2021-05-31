package com.nesti.nestimobile.utils

import com.nesti.nestimobile.data.api.ApiService

class ApiHelper(private val apiService: ApiService) {

    fun getRecipesForPartialName(partialName:String) = apiService.getRecipesForPartialName(partialName);
    fun getRecipesForTag(idTag:Int) = apiService.getRecipesForTag(idTag);
    fun getTags() = apiService.getTags();

    fun getIngredientRecipes(idRecipe:Int) = apiService.getIngredientRecipes(idRecipe);
    fun getParagraphs(idRecipe:Int) = apiService.getParagraphs(idRecipe);

}