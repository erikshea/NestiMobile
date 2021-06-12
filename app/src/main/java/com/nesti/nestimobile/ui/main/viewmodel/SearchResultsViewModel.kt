package com.nesti.nestimobile.ui.main.viewmodel

import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchResultsViewModel(private val recipeRepository: RecipeRepository) : BaseRecipeListViewModel(recipeRepository) {
    var searchTerm:String = "";

    override fun fetchRecipes() {
        recipes.postValue(Resource.loading(null))
        compositeDisposable.add(
                recipeRepository.findAllByPartialName(searchTerm)
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
}