package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseRecipeListViewModel

/**
 * viewmodel for a single category's list of recipes
 */
class CategoryViewModel(private val repository: RecipeRepository)
    : BaseRecipeListViewModel() {
    lateinit var tag:Tag

    /**
     * get view-ready observable recipes
     */
    override fun getRecipes(): LiveData<StatusContainer<List<Recipe>>> {
        if ( recipes.value == null ){
            sendToMutableLiveDataWhenInitialized(repository.findAllForTag(tag.idTag), recipes)
        }
        return recipes
    }
}