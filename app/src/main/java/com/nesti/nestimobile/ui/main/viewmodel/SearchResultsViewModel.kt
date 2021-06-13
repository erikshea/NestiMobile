package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.ui.base.StatusContainer
import com.nesti.nestimobile.ui.main.viewmodel.base.BaseRecipeListViewModel

/**
 * viewmodel for search results showing a list of recipes
 */
class SearchResultsViewModel(private val repository: RecipeRepository)
    : BaseRecipeListViewModel() {
    var searchTerm:String = "";

    /**
     * get view-ready observable recipes
     */
    override fun getRecipes(): LiveData<StatusContainer<List<Recipe>>> {
        if ( recipes.value == null ){
            sendToMutableLiveDataWhenInitialized(repository.findAllByPartialName(searchTerm), recipes)
        }
        return recipes
    }
}