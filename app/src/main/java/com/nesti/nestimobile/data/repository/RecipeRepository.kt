package com.nesti.nestimobile.data.repository
import com.nesti.nestimobile.data.api.ApiService
import com.nesti.nestimobile.data.model.*
import com.nesti.nestimobile.utils.ApiHelper
import io.reactivex.Single

class RecipeRepository(private val apiHelper: ApiHelper) {
    fun findAllForTag(idTag:Int): Single<List<Recipe>> {
        return apiHelper.getRecipesForTag(idTag);
    }
    fun findAllByName(partialName:String): Single<List<Recipe>> {
        return apiHelper.getRecipesForPartialName(partialName);
    }

    fun findIngredientRecipes(idRecipe:Int): Single<List<IngredientRecipe>>{
        return apiHelper.getIngredientRecipes(idRecipe);
    }

    fun findParagraphs(idRecipe:Int): Single<List<Paragraph>>{
        return apiHelper.getParagraphs(idRecipe);
    }
}