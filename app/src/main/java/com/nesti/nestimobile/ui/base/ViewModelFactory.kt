package com.nesti.nestimobile.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.ui.main.viewmodel.CategoryViewModel
import com.nesti.nestimobile.ui.main.viewmodel.MainViewModel
import com.nesti.nestimobile.ui.main.viewmodel.RecipeViewModel
import com.nesti.nestimobile.ui.main.viewmodel.SearchResultsViewModel
import com.nesti.nestimobile.utils.ApiHelper


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var viewModel:T? = null

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            viewModel =  MainViewModel(TagRepository(apiHelper)) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            viewModel =  CategoryViewModel(RecipeRepository(apiHelper)) as T
        }else if (modelClass.isAssignableFrom(SearchResultsViewModel::class.java)) {
            viewModel =  SearchResultsViewModel(RecipeRepository(apiHelper)) as T
        }else if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            viewModel =  RecipeViewModel(RecipeRepository(apiHelper)) as T
        }

        if ( viewModel == null){
            throw IllegalArgumentException("Viewmodel class $modelClass not defined in factory")
        }

        return viewModel;
    }

}