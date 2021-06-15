package com.nesti.nestimobile.ui.main.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.ui.base.StatusContainer

/**
 * base class for viewmodels that contain a list of recipes
 */
abstract class BaseRecipeListViewModel() : BaseViewModel() {
    protected val recipes = MutableLiveData<StatusContainer<List<Recipe>>>()

    /**
     * get view-ready observable recipes
     */
    abstract fun getRecipes(): LiveData<StatusContainer<List<Recipe>>>
}