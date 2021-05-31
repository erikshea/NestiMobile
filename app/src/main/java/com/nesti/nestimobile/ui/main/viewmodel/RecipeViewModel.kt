package com.nesti.nestimobile.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nesti.nestimobile.data.model.IngredientRecipe
import com.nesti.nestimobile.data.model.Paragraph
import com.nesti.nestimobile.data.model.Recipe
import com.nesti.nestimobile.data.model.Tag
import com.nesti.nestimobile.data.repository.RecipeRepository
import com.nesti.nestimobile.data.repository.TagRepository
import com.nesti.nestimobile.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val ingredients = MutableLiveData<Resource<List<IngredientRecipe>>>()
    private val paragraphs = MutableLiveData<Resource<List<Paragraph>>>()
    lateinit var recipe:Recipe
    // will dispose Single containing tag list when activity changes
    private val compositeDisposable = CompositeDisposable()

    private fun fetchIngredients() {
        ingredients.postValue(Resource.loading(null))
        compositeDisposable.add(
                repository.findIngredientRecipes(recipe.idRecipe)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list ->
                            ingredients.postValue(Resource.success(list))
                        }, { throwable ->
                            throwable.printStackTrace();
                            ingredients.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }

    private fun fetchParagraphs() {
        paragraphs.postValue(Resource.loading(null))
        compositeDisposable.add(
                repository.findParagraphs(recipe.idRecipe)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ list ->
                            paragraphs.postValue(Resource.success(list))
                        }, { throwable ->
                            throwable.printStackTrace();
                            paragraphs.postValue(Resource.error("Something Went Wrong", null))
                        })
        )
    }

    // Called when VM is no longer needed (activity changed...)
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // dispose Single on activity change
    }

    fun getIngredients(): LiveData<Resource<List<IngredientRecipe>>> {
        if ( ingredients.value == null ){
            fetchIngredients();
        }
        return ingredients
    }

    fun getParagraphs(): LiveData<Resource<List<Paragraph>>> {
        if ( paragraphs.value == null ){
            fetchParagraphs();
        }
        return paragraphs
    }
}