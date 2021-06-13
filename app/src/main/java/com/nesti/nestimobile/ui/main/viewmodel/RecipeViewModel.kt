package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseViewModel

/**
 * viewmodel for recipe section showing tabs with ingredients and steps
 */
class RecipeViewModel(private val repository: RecipeRepository) : BaseViewModel() {
    private val ingredients = MutableLiveData<StatusContainer<List<IngredientRecipe>>>()
    private val paragraphs = MutableLiveData<StatusContainer<List<Paragraph>>>()
    lateinit var recipe:Recipe

    /**
     * get view-ready observable ingredientRecipes
     */
    fun getIngredients(): LiveData<StatusContainer<List<IngredientRecipe>>> {
        if ( ingredients.value == null ){
            sendToMutableLiveDataWhenInitialized(repository.findIngredientsForRecipe(recipe.idRecipe), ingredients)
        }
        return ingredients
    }

    /**
     * get view-ready observable paragraphs
     */
    fun getParagraphs(): LiveData<StatusContainer<List<Paragraph>>> {
        if ( paragraphs.value == null ){
            sendToMutableLiveDataWhenInitialized(repository.findParagraphsForRecipe(recipe.idRecipe), paragraphs)
        }
        return paragraphs
    }
}