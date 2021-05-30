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

class CategoryViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    private val recipes = MutableLiveData<Resource<List<Recipe>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        recipes.postValue(Resource.loading(null))
        compositeDisposable.add(
                recipeRepository.findAllForTag(1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tagList ->
                            recipes.postValue(Resource.success(tagList))
                        }, { throwable ->
                            throwable.printStackTrace();
                            recipes.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getRecipes(): LiveData<Resource<List<Recipe>>> {
        return recipes
    }
}