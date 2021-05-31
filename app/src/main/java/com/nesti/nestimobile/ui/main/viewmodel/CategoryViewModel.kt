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

class CategoryViewModel(private val recipeRepository: RecipeRepository) : BaseRecipeListViewModel(recipeRepository) {
    lateinit var tag:Tag;

    override fun fetchRecipes() {
        recipes.postValue(Resource.loading(null))
        compositeDisposable.add(
                recipeRepository.findAllForTag(tag.idTag)
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