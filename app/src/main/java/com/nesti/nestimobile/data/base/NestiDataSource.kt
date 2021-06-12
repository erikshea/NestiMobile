package com.nesti.nestimobile.data.base

import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import io.reactivex.Single

interface NestiDataSource {
    fun findAllRecipesByPartialName(partialName:String): Single<List<Recipe>>;
    fun findAllRecipesByTag(idTag:Int): Single<List<Recipe>>;
    fun findAllTags(): Single<List<Tag>>;
    fun findIngredientsForRecipe(idRecipe:Int): Single<List<IngredientRecipe>>;
    fun findParagraphsForRecipe(idRecipe:Int): Single<List<Paragraph>>;
}