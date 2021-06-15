package com.nesti.nestimobile.data.datasource

import com.nesti.nestimobile.data.base.NestiDataSource
import com.nesti.nestimobile.data.model.*
import com.nesti.nestimobile.lib.ApplicationConfiguration
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

/**
 * methods call an API and return an observable object updated with a list of results
 * when fetch completes
 * @param configuration from which to get configuration parameters
 */
class NestiApiDataSource(private val configuration: ApplicationConfiguration): NestiDataSource {
    /**
     * Get recipes for a given tag ID
     * @param idTag ID of tag
     * @return List of recipes wrapped in an observable Single
     */
    override fun findAllRecipesByTag(idTag:Int): Single<List<Recipe>> {
        return  buildRequestWithToken("recipesForTag/$idTag")
                .getObjectListSingle(Recipe::class.java)
    }

    /**
     * Get recipes whose name contains a given string
     * @param partialName string to search for in name
     * @return List of recipes wrapped in an observable Single
     */
    override fun findAllRecipesByPartialName(partialName:String): Single<List<Recipe>> {
        return  buildRequestWithToken("recipesForPartialName/$partialName")
                .getObjectListSingle(Recipe::class.java)
    }

    /**
     * Get all tags
     * @return List of tags wrapped in an observable Single
     */
    override fun findAllTags(): Single<List<Tag>> {
        return  buildRequestWithToken("tags")
                .getObjectListSingle(Tag::class.java)
    }

    /**
     * Get all ingredients for a given recipe
     * @param idRecipe ID of recipe
     * @return List of ingredients wrapped in an observable Single
     */
    override fun findIngredientsForRecipe(idRecipe:Int): Single<List<IngredientRecipe>> {
        return  buildRequestWithToken("ingredientRecipes/$idRecipe")
                .getObjectListSingle(IngredientRecipe::class.java)
    }

    /**
     * Get all paragraphs for a given recipe
     * @param idRecipe ID of recipe
     * @return List of paragraphs wrapped in an observable Single
     */
    override fun findParagraphsForRecipe(idRecipe:Int): Single<List<Paragraph>> {
        return  buildRequestWithToken("paragraphs/$idRecipe")
                .getObjectListSingle(Paragraph::class.java)
    }

    /**
     * Build an API request using an API URL and token stored in configuration
     * @param route route to append to API URL
     * @return Request object from which we can fetch results
     */
    private fun buildRequestWithToken(route:String): Rx2ANRequest {
        val baseUrl = configuration.getNode("api/@baseUrl").stringValue + "/$route"
        val apiToken = configuration.getNode("api/@clientToken").stringValue
        return Rx2AndroidNetworking.get(baseUrl)
            .addQueryParameter("token", apiToken)
            .build()
    }
}