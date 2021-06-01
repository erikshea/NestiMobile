package com.nesti.nestimobile.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.ui.main.viewmodel.*
import com.nesti.nestimobile.utils.ApiHelper


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(TagRepository(apiHelper))
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(RecipeRepository(apiHelper))
            modelClass.isAssignableFrom(SearchResultsViewModel::class.java) -> SearchResultsViewModel(RecipeRepository(apiHelper))
            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> RecipeViewModel(RecipeRepository(apiHelper))
            //modelClass.isAssignableFrom(ShoppingListViewModel::class.java) ->  ShoppingListViewModel()
            else -> throw IllegalArgumentException("Viewmodel class $modelClass not defined in factory")
        } as T
    }

}