package com.nesti.nestimobile.data.repository
import com.nesti.nestimobile.data.base.NestiDataSource
import com.nesti.nestimobile.data.model.*
import io.reactivex.Single

/**
 * Fetches recipes from data source
 */
class RecipeRepository(private val dataSource: NestiDataSource) {

    /**
     * Get recipes for a given tag ID
     * @param idTag ID of tag
     * @return List of recipes wrapped in an observable Single
     */
    fun findAllForTag(idTag:Int): Single<List<Recipe>> {
        return dataSource.findAllRecipesByTag(idTag);
    }

    /**
     * Get recipes whose name contains a given string
     * @param partialName string to search for in name
     * @return List of recipes wrapped in an observable Single
     */
    fun findAllByPartialName(partialName:String): Single<List<Recipe>> {
        return dataSource.findAllRecipesByPartialName(partialName);
    }

    /**
     * Get all ingredients for a given recipe
     * @param idRecipe ID of recipe
     * @return List of ingredients wrapped in an observable Single
     */
    fun findIngredientsForRecipe(idRecipe:Int): Single<List<IngredientRecipe>>{
        return dataSource.findIngredientsForRecipe(idRecipe);
    }

    /**
     * Get all paragraphs for a given recipe
     * @param idRecipe ID of recipe
     * @return List of paragraphs wrapped in an observable Single
     */
    fun findParagraphsForRecipe(idRecipe:Int): Single<List<Paragraph>>{
        return dataSource.findParagraphsForRecipe(idRecipe);
    }
}