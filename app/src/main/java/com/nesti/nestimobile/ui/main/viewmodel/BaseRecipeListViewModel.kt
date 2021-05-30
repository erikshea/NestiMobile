package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseRecipeListViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    protected val recipes = MutableLiveData<Resource<List<Recipe>>>()
    protected val compositeDisposable = CompositeDisposable()

    protected abstract fun fetchRecipes();

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getRecipes(): LiveData<Resource<List<Recipe>>> {
        if ( recipes.value == null ){
            fetchRecipes();
        }

        return recipes
    }
}